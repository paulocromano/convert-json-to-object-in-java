package convert.collection;

import org.json.JSONArray;

import java.util.Set;
import java.util.stream.Collectors;

public class ConvertObjectToSet implements ObjectToCollection<Set<Object>> {

    @Override
    public Set<Object> convert(Object objectFromJSON) {
        if (objectFromJSON instanceof JSONArray) {
            return ((JSONArray) objectFromJSON).toList()
                .stream()
                .collect(Collectors.toSet());
        }

        throw new IllegalArgumentException("Tipo de objeto inválido para converter dados para lista!");
    }
}
