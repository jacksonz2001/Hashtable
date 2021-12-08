/**
 * Node Class for storing Key/Value Pairs
 * 
 * @author jacksonzhao
 *
 * @param <KeyType> - Object Type for the Key
 * @param <ValueType> Object Type for the Value
 */
public class Node <KeyType, ValueType> {
  protected KeyType key;
  protected ValueType value;
  protected Node<KeyType, ValueType> next;
  
  /**
   * Constructor method for the Node class that stores a Key & Value
   * 
   * @param key - Key to be stored
   * @param value - Value to be stored
   */
  public Node(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }
  
  /**
   * Getter method for the Key of the Node
   * 
   * @return - Key of the Node
   */
  public KeyType getKey() {
    return this.key;
  }
  
  /**
   * Getter method for the Value of the Node
   * 
   * @return - Value of the Node
   */
  public ValueType getValue() {
    return this.value;
  }
  
}
