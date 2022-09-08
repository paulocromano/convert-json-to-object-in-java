package convert.predicate;

import convert.ConvertToTypeField;

import java.lang.reflect.Field;
import java.util.function.Predicate;

public interface PredicateFieldFromJSON {

    Predicate<Field> getPredicate();
    ConvertToTypeField getClassToConvertToTypeField();
}
