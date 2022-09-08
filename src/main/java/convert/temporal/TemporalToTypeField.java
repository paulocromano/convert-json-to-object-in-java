package convert.temporal;

import convert.ConvertToTypeField;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class TemporalToTypeField implements ConvertToTypeField {

    @Override
    public Object convert(Field field, Object objectFromJSON) {
        ObjectToTemporal<?> convertObjectToTemporal = null;

        if (field.getType().equals(LocalDate.class)) {
            convertObjectToTemporal = new ConvertObjectToLocalDate();
        }
        else if (field.getType().equals(LocalTime.class)) {
            convertObjectToTemporal = new ConvertObjectToLocalTime();
        }
        else if (field.getType().equals(LocalDateTime.class)) {
            convertObjectToTemporal = new ConvertObjectToLocalDateTime();
        }

        if (Objects.isNull(convertObjectToTemporal))
            throw new IllegalArgumentException("Conversão de data e/ou hora " +
                    "não implementada!");

        return convertObjectToTemporal.convert(objectFromJSON);
    }
}
