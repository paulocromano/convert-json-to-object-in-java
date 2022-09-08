package convert;

import java.lang.reflect.Field;

public interface ConvertToTypeField {

    Object convert(Field field, Object objectFromJSON);
}
