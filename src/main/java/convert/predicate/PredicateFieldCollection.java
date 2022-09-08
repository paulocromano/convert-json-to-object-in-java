package convert.predicate;

import convert.ConvertToTypeField;
import convert.collection.CollectionToTypeField;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class PredicateFieldCollection implements PredicateFieldFromJSON {
    @Override
    public Predicate<Field> getPredicate() {
        return field -> Arrays.stream(field.getType().getInterfaces())
            .filter(interfaceField -> interfaceField.equals(Collection.class))
            .findFirst()
            .isPresent();

    }

    @Override
    public ConvertToTypeField getClassToConvertToTypeField() {
        return new CollectionToTypeField();
    }
}
