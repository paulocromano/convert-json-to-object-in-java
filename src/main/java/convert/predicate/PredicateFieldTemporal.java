package convert.predicate;

import convert.ConvertToTypeField;
import convert.temporal.TemporalToTypeField;

import java.lang.reflect.Field;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.function.Predicate;

public class PredicateFieldTemporal implements PredicateFieldFromJSON {

    @Override
    public Predicate<Field> getPredicate() {
        return field -> Arrays.stream(field.getType().getInterfaces())
            .filter(interfaceField -> interfaceField.equals(Temporal.class))
            .findFirst()
            .isPresent();
    }

    @Override
    public ConvertToTypeField getClassToConvertToTypeField() {
        return new TemporalToTypeField();
    }
}
