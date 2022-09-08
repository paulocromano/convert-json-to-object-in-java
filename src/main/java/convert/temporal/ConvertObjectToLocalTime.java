package convert.temporal;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConvertObjectToLocalTime implements ObjectToTemporal<LocalTime> {
    @Override
    public LocalTime convert(Object objectFromJSON) {
        String horario = objectFromJSON.toString();
        final String format = "HH:mm:ss";
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);

        if (objectFromJSON.toString().length() == 5) horario = horario.concat(":00");

        try {
            return LocalTime.parse(horario, timeFormatter);
        }
        catch (DateTimeParseException e) {
            throw new DateTimeException("Formato de LocalTime aceito -> " + format
                    + " - Valor recebido -> " + objectFromJSON.toString());
        }
    }
}
