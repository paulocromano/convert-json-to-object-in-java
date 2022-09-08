package convert.temporal;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConvertObjectToLocalDateTime implements ObjectToTemporal<LocalDateTime> {

    @Override
    public LocalDateTime convert(Object objectFromJSON) {
        String dataHorario = objectFromJSON.toString();

        final String format = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

        try {
            if (dataHorario.split(" ")[1].length() == 5) dataHorario = dataHorario.concat(":00");
            return LocalDateTime.parse(dataHorario, dateTimeFormatter);
        }
        catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DateTimeException("Formato de LocalDateTime aceito -> " + format
                    + " - Valor recebido -> " + objectFromJSON.toString());
        }
    }
}
