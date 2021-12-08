import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Hashtable implementation that uses Nodes containing Key/Value to store data
 *
 * @param <KeyType> - ObjectType of the Keys
 * @param <ValueType> - ObjectType of the Values
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  // Capacity of the HashTable
  private int capacity;
  // Will double capacity & rehas whenever load factor is >= 0.8
  private double LOAD_FACTOR_THRESHOLD = 0.8;
  // Object array that will be Hashtable
  private Object[] hashTable;

  /**
   * Constructor method for HashTableMap
   * 
   * @param capacity - Original Capacity of Hashtable
   */
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    this.hashTable = new Object[this.capacity];
  }

  /**
   * Default constructor method for the HashtableMap with a default capacity of 20
   */
  public HashtableMap() {
    this.capacity = 20;
    this.hashTable = new Object[20];
  }

  /**
   * Hash Function that calculates and returns the index of the HashMap to store key
   * 
   * @param key - key that contains the necessary hashcode to calculate index
   * @return - index of HashMap to store key
   */
  private int hashFunction(KeyType key) {
    // Takes the key's hashcode and modulus by the HashMap's current capacity
    int index = Math.abs(key.hashCode() % this.capacity);
    return index;
  }
  
  /**
   * Getter method for the capacity of hashtable
   * 
   * @return - Capacity of Hashtable
   */
  public int getCapacity() {
    return this.capacity;
  }
  
  /**
   * Stores the Key/Value pair into the HashTable
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean put(KeyType key, ValueType value) {
    // Checks that key is not null
    if (key == null) {
      return false;
    }

    // Gets the index to place the Node & creates the NodeToAdd
    int index = hashFunction(key);
    Node<KeyType, ValueType> nodeToAdd = new Node<KeyType, ValueType>(key, value);

    // Checks that key is not equal to a key already in HashTable
    if (containsKey(key)) {
      return false;
    }

    // If no collision has occurred, simply place the Node into the Hashtable
    if (hashTable[index] == null) {
      // Creates a LinkedList at the hashTable index
      hashTable[index] = new LinkedList<Node<KeyType, ValueType>>();
      // Adds the node into the newly created LinkedList
      ((LinkedList<Node<KeyType, ValueType>>) hashTable[index]).add(nodeToAdd);

      // If the ratio of the numOfPairs / capacity is greater than 0.8, rehash & resize
      double numOfPairs = size();
      double currentThreshold = numOfPairs / capacity;
      if (Double.compare(LOAD_FACTOR_THRESHOLD, currentThreshold) <= 0) {
        Object[] oldTable = hashTable;
        int oldCapacity = capacity;
        hashTable = new Object[this.capacity * 2];
        capacity = capacity * 2;
        capacityAdjustment(oldTable, oldCapacity);
      }

      return true;
    }

    // If collision has occurred
    if (hashTable[index] != null) {
      // Get the LinkedList at that hashTable index
      LinkedList<Node<KeyType, ValueType>> nodeList =
          (LinkedList<Node<KeyType, ValueType>>) hashTable[index];
      // Add the node within the LinkedList
      nodeList.add(nodeToAdd);

      // If the ratio of the numOfPairs / capacity is greater than 0.8, rehash & resize
      double numOfPairs = size();
      double currentThreshold = numOfPairs / capacity;
      if (Double.compare(LOAD_FACTOR_THRESHOLD, currentThreshold) <= 0) {
        Object[] oldTable = hashTable;
        int oldCapacity = capacity;
        hashTable = new Object[this.capacity * 2];
        capacity = capacity * 2;
        capacityAdjustment(oldTable, oldCapacity);
      }

      return true;
    }

    return true;
  }

  /**
   * Rehashing function once the LOAD_FACTOR_THRESHOLD is reached
   * 
   * @param oldTable - old HashTable that needs to be enlarged
   * @param oldCapacity - capacity of oldTable
   */
  @SuppressWarnings("unchecked")
  private void capacityAdjustment(Object[] oldTable, int oldCapacity) {
    // Goes through the old HashTable
    for (int i = 0; i < oldCapacity; i++) {
      if (oldTable[i] != null) {
        // Gets the LinkedList for the i index of the old HashTable
        LinkedList<Node<KeyType, ValueType>> nodeList =
            (LinkedList<Node<KeyType, ValueType>>) oldTable[i];

        // Goes through the LinkedList, getting the key, value pairs
        for (int j = 0; j < nodeList.size(); j++) {
          KeyType key = nodeList.get(j).getKey();
          ValueType value = nodeList.get(j).getValue();
          // Puts the Key/Value pair into the new HashTable
          put(key, value);
        }
      }
    }
  }

  /**
   * Gets the Value associated with the Key Exception is thrown when Key is not contained within
   * HashTable
   */
  @Override
  @SuppressWarnings("unchecked")
  public ValueType get(KeyType key) throws NoSuchElementException {
    // If the hashTable does not contain key, throw the exception
    if (!containsKey(key)) {
      throw new NoSuchElementException("Does not contain this key!");
    }

    // Calculates index of the Key and creates a null ValueType variable
    int index = hashFunction(key);
    ValueType value = null;

    // Gets the LinkedList at the key's hashTable index
    LinkedList<Node<KeyType, ValueType>> nodeList =
        (LinkedList<Node<KeyType, ValueType>>) hashTable[index];

    // Goes through the LinkedList to find the Key, then get its value
    for (int i = 0; i < nodeList.size(); i++) {
      if (nodeList.get(i).getKey().equals(key)) {
        value = nodeList.get(i).getValue();
      }
    }

    // Return the retrieved key's value variable
    return value;
  }

  /**
   * Gets the number of Key/Value pairs stored within the HashTable
   */
  @Override
  @SuppressWarnings("unchecked")
  public int size() {
    // Initialize size variable used for storing # of Key/Value pairs
    int size = 0;


    // Goes through hashTable
    for (int i = 0; i < capacity; i++) {
      // If the specific index of the HashTable is not null
      if (hashTable[i] != null) {
        // Gets the LinkedList for that specific HashTable index
        LinkedList<Node<KeyType, ValueType>> nodeList =
            (LinkedList<Node<KeyType, ValueType>>) hashTable[i];
        // Add the size of that LinkedList to the size variable
        size += nodeList.size();
      }
    }

    // Returns # of Key/Value pairs in the HashTable
    return size;
  }

  /**
   * Checks if the HashTable contains the Key
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean containsKey(KeyType key) {
    // Gets the index the possible Key would be at
    int index = hashFunction(key);

    // If that index has a null reference, key is not in HashTable, return false
    if (hashTable[index] == null) {
      return false;
    }

    // If that index does not contain a null reference
    if (hashTable[index] != null) {
      // Get the LinkedList for that HashTable index
      LinkedList<Node<KeyType, ValueType>> nodeList =
          (LinkedList<Node<KeyType, ValueType>>) hashTable[index];
      // Go through the LinkedList and if the key is found, return true
      for (int j = 0; j < nodeList.size(); j++) {
        if (nodeList.get(j).getKey().equals(key)) {
          return true;
        }
      }
    }

    // Key was not found, returns false
    return false;
  }

  /**
   * Removes the Node containing the Key within the HashTable Returns Value associated with the Key
   */
  @Override
  @SuppressWarnings("unchecked")
  public ValueType remove(KeyType key) {
    // If the key is null
    if (key == null) {
      return null;
    }
    
    // If the HashTable does not contain the key, return null
    if (!containsKey(key)) {
      return null;
    }

    // Gets the index the key will be at and initalizes a ValueType variable to hold
    // the value of the Key being removed
    int index = hashFunction(key);
    ValueType value = null;

    // Gets the LinkedList at the index of the HashTable
    LinkedList<Node<KeyType, ValueType>> nodeList =
        (LinkedList<Node<KeyType, ValueType>>) hashTable[index];

    // Goes through the LinkedList
    for (int i = 0; i < nodeList.size(); i++) {
      // Once finding the key, store its value, then remove the key, break loop
      if (nodeList.get(i).getKey().equals(key)) {
        value = nodeList.get(i).getValue();
        nodeList.remove(nodeList.get(i));
        break;
      }
    }

    // Return value of the removed Key
    return value;
  }

  /**
   * Removes all key-value pairs from the collection without changing underlying array capacity
   */
  @Override
  public void clear() {
    // Go through all the HashTable's indexes and set all of them to null
    // This will remove all the Key/Value pairs within the Hashtable
    for (int i = 0; i < capacity; i++) {
      hashTable[i] = null;
    }
  }

}

