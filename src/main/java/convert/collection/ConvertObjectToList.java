package convert.collection;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ConvertObjectToList implements ObjectToCollection<List<Object>> {

    @Override
    public List<Object> convert(Object objectFromJSON) {
        if (objectFromJSON instanceof JSONArray) {
            return ((JSONArray) objectFromJSON).toList();
        }

        throw new IllegalArgumentException("Tipo de objeto inválido para converter dados para lista!");
    }
}
