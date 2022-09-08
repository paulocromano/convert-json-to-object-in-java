package convert.collection;

import convert.ConvertToTypeField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CollectionToTypeField implements ConvertToTypeField {

    @Override
    public Object convert(Field field, Object objectFromJSON) {
        verificarSeTiposDosAtributosDoFieldCorrespondemAosDoJSON(field, objectFromJSON);
        ObjectToCollection<?> convertObjectToCollection = null;

        if (field.getType().equals(List.class)) {
            convertObjectToCollection = new ConvertObjectToList();
        }
        else if (field.getType().equals(Set.class)) {
            convertObjectToCollection = new ConvertObjectToSet();
        }

        if (Objects.isNull(convertObjectToCollection))
            throw new NullPointerException("Conversão para Collection não implementada!");

        return convertObjectToCollection.convert(objectFromJSON);
    }

    private void verificarSeTiposDosAtributosDoFieldCorrespondemAosDoJSON(Field field, Object objectFromJSON) {
        ParameterizedType parameterizedTypeField = (ParameterizedType) field.getGenericType();
        Type genericTypeList = parameterizedTypeField.getActualTypeArguments()[0];
        JSONArray jsonArray = ((JSONArray) objectFromJSON);

        try {
            Class<?> classGenericTypeList = Class.forName(genericTypeList.getTypeName());

            for (Field fieldOfList : classGenericTypeList.getDeclaredFields()) {
                if (fieldOfList.getType().getSuperclass().equals(Number.class)) {
                    for (int i = 0; Objects.nonNull(jsonArray.optJSONObject(i)); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (Objects.nonNull(jsonObject.opt(fieldOfList.getName()))) {
                            String valorJSONObjectCorrespondeAoField = jsonObject.get(fieldOfList.getName()).toString();
                            Method method = fieldOfList.getType().getDeclaredMethod("valueOf", String.class);
                            method.invoke(null, valorJSONObjectCorrespondeAoField);
                        }
                    }
                }
            }
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
