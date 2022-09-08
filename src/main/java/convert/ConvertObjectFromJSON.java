package convert;

public interface ConvertObjectFromJSON<T> {

    T convert(Object objectFromJSON);
}
