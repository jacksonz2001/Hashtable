import java.util.NoSuchElementException;

/**
 * Interface used for abstraction of the HashtableMap
 *
 * @param <KeyType> - KeyType that Hashtable will be storing
 * @param <ValueType> - ValueType that Hashtable will be storing
 */
public interface MapADT<KeyType, ValueType> {

    public boolean put(KeyType key, ValueType value);
    public ValueType get(KeyType key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(KeyType key);
    public ValueType remove(KeyType key);
    public void clear();
    
}