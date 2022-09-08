package convert;

import convert.predicate.PredicateFieldFromJSON;
import org.json.JSONObject;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;

public class ConvertJSONToObject {

    public static <T> T converter(JSONObject jsonObject, Class<T> klass) {
        try {
            Set<? extends PredicateFieldFromJSON> classesQueImplementamInterfacePredicateFieldFromJSON =
                buscarClassesQueImplementemInterfacePredicateFieldFromJSON();

            T instanceKlass = klass.newInstance();

            for (Field field : instanceKlass.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String nomeAtributoDaClasse = field.getName();
                Object fieldObjectJSON = jsonObject.opt(nomeAtributoDaClasse);

                if (Objects.nonNull(fieldObjectJSON)) {
                    Optional<? extends PredicateFieldFromJSON> predicateFieldFromJSONOptional = buscarClasseCorrespondenteAoTipoDoField(
                            field, classesQueImplementamInterfacePredicateFieldFromJSON);

                    if (predicateFieldFromJSONOptional.isPresent()) {
                        ConvertToTypeField convertToTypeField = predicateFieldFromJSONOptional.get().getClassToConvertToTypeField();
                        fieldObjectJSON = convertToTypeField.convert(field, fieldObjectJSON);
                    }
                    else if (!field.getType().equals(fieldObjectJSON.getClass())) {
                            throw new IllegalArgumentException("Tipo de objeto inválido! Nome do atributo: '" +
                                    nomeAtributoDaClasse + "' Classe esperada -> " + field.getClass() +
                                    " - Classe recebida -> " + fieldObjectJSON.getClass());
                    }

                    field.set(instanceKlass, fieldObjectJSON);
                }
            }

            return instanceKlass;
        }
        catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<? extends PredicateFieldFromJSON> buscarClassesQueImplementemInterfacePredicateFieldFromJSON() throws InstantiationException, IllegalAccessException {
        final String CAMINHO_PROJETO = "";
        Reflections reflections = new Reflections(CAMINHO_PROJETO);
        Set<PredicateFieldFromJSON> classesQueImplementamInterfacePredicateFieldFromJSON = new HashSet<>();

        for (Class<? extends PredicateFieldFromJSON> klass : reflections.getSubTypesOf(PredicateFieldFromJSON.class))
            classesQueImplementamInterfacePredicateFieldFromJSON.add(klass.newInstance());

        return classesQueImplementamInterfacePredicateFieldFromJSON;
    }

    private static Optional<? extends PredicateFieldFromJSON> buscarClasseCorrespondenteAoTipoDoField(Field field, Set<? extends PredicateFieldFromJSON> classesQueImplementamInterfacePredicateFieldFromJSON) {
        return classesQueImplementamInterfacePredicateFieldFromJSON
            .stream()
            .filter(klass -> klass.getPredicate().test(field))
            .findFirst();
    }
}
