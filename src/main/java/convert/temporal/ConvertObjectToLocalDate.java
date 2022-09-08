package convert.temporal;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConvertObjectToLocalDate implements ObjectToTemporal<LocalDate> {

    @Override
    public LocalDate convert(Object objectFromJSON) {
        final String format = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

        try {
            return LocalDate.parse(objectFromJSON.toString(), dateFormatter);
        }
        catch (DateTimeParseException e) {
            throw new DateTimeException("Formato de LocalDate aceito -> " + format
                + " - Valor recebido -> " + objectFromJSON.toString());
        }
    }
}
