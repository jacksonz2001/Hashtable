import java.util.NoSuchElementException;

/**
 * Class containing tests ensuring the functionality of the HashtableMap.java implementation
 */
public class HashtableMapTests {
  /**
   * Tests the functionality of the put() method
   * 
   * @return - true if all tests passed, false otherwise
   */
  public static boolean test1() {
    // Tests the put method within the HashtableMap class
    HashtableMap<String, Integer> hashTable = new HashtableMap<String, Integer>(5);

    // (1) Null key test
    boolean actual = hashTable.put(null, 3);
    boolean expected = false;
    if (actual != expected) {
      System.out.println("(1) Error in test1");
      return false;
    }

    // (2) Adding 1 Key/Value Pair to Hashtable
    actual = hashTable.put("Burger", 2);
    expected = true;
    if (!actual) {
      System.out.println("(2) Error in test1");
      return false;
    }
    int expectedSize = 1;
    int actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(2) Error in test1");
      return false;
    }

    // (3) Key already in the hashTable
    hashTable.put("Burger", 2);
    actual = hashTable.put("Burger", 1);
    expected = false;
    if (!actual) {
      System.out.println("(3) Error in test1");
      return false;
    }

    // (4) Need to resize
    hashTable.put("Sandwich", 4);
    hashTable.put("Fries", 1);
    hashTable.put("Wings", 5);
    actualSize = hashTable.getCapacity();
    expectedSize = 10;
    if (expectedSize != actualSize) {
      System.out.println("(4) Error in test1");
      return false;
    }
    expected = true;
    actual = hashTable.containsKey("Sandwich") && hashTable.containsKey("Fries")
        && hashTable.containsKey("Wings") && hashTable.containsKey("Burger");
    if (!actual) {
      System.out.println("(4) Error in test1");
      return false;
    }


    return true;
  }

  /**
   * Tests the functionality of the remove() method
   * 
   * @return - true if all tests passed, false otherwise
   */
  public static boolean test2() {
    // Tests the remove method within the HashtableMap class
    HashtableMap<String, Integer> hashTable = new HashtableMap<String, Integer>(5);

    // (1) Removing an element that doesn't exist in empty HashTable
    if (hashTable.remove("Burger") != null) {
      System.out.println("(1) Error in test2");
      return false;
    }

    hashTable.put("Burger", 2);
    hashTable.put("Fries", 1);
    hashTable.put("Wings", 5);

    // (2) Removing 1 Element
    int actualPrice = hashTable.remove("Burger");
    int expectedPrice = 2;
    if (actualPrice != expectedPrice) {
      System.out.println("(2) Error in test2");
      return false;
    }
    int expectedSize = 2;
    int actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(2) Error in test2");
      return false;
    }

    // (3) Removing an element that doesn't exist in a non-empty HashTable
    if (hashTable.remove("Tatertots") != null) {
      System.out.println("(3) Error in test2");
      return false;
    }

    // (4) Removing 1 more element in HashTable
    actualPrice = hashTable.remove("Fries");
    expectedPrice = 1;
    if (actualPrice != expectedPrice) {
      System.out.println("(4) Error in test2");
      return false;
    }
    expectedSize = 1;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(4) Error in test2");
      return false;
    }


    // (5) Removing the last element in the HashTable
    actualPrice = hashTable.remove("Wings");
    expectedPrice = 5;
    if (actualPrice != expectedPrice) {
      System.out.println("(5) Error in test2");
      return false;
    }
    expectedSize = 0;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(5) Error in test2");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the size() & clear() method
   * 
   * @return - true if all tests passed, false otherwise
   */
  public static boolean test3() {
    // Tests the size & clear method within the HashtableMap class
    HashtableMap<String, Integer> hashTable = new HashtableMap<String, Integer>(5);

    // (1) Adding 1 item
    hashTable.put("Burger", 2);
    int expectedSize = 1;
    int actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(1) Error in test3");
      return false;
    }

    // (2) Adding +1 Item
    hashTable.put("Fries", 3);
    expectedSize = 2;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(2) Error in test3");
      return false;
    }

    // (3) Adding +1 Item
    hashTable.put("Wings", 4);
    expectedSize = 3;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(3) Error in test3");
      return false;
    }

    // (4) Adding +1 Item
    hashTable.put("Pizza", 5);
    expectedSize = 4;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(4) Error in test3");
      return false;
    }

    // (5) Adding +1 Item
    hashTable.put("Onion Rings", 6);
    expectedSize = 5;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(5) Error in test3");
      return false;
    }

    // (6) Clear Method
    hashTable.clear();
    expectedSize = 0;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(6) Error in test3");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the get() method
   * 
   * @return - true if all tests passed, false otherwise
   */
  public static boolean test4() {
    // Tests the get() method within the HashtableMap class
    HashtableMap<String, Integer> hashTable = new HashtableMap<String, Integer>(5);

    // (1) Catch the NoSuchElementException
    hashTable.put("Burger", 2);
    try {
      hashTable.get("Fries");
      System.out.println("(1) Error in test4");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("Test4 (1) Passed Exception");
    }

    // (2) get() method on a 1 element hashTable
    int expectedValue = 2;
    int actualValue = hashTable.get("Burger");
    if (actualValue != expectedValue) {
      System.out.println("(2) Error in test4");
      return false;
    }
    int expectedSize = 1;
    int actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(2) Error in test4");
      return false;
    }

    // (3) get() method on a multi-element hashTable
    hashTable.put("Fries", 1);
    hashTable.put("Wings", 5);
    hashTable.put("Onion Rings", 4);
    hashTable.put("Chicken Tenders", 6);
    expectedValue = 6;
    actualValue = hashTable.get("Chicken Tenders");
    if (actualValue != expectedValue) {
      System.out.println("(3) Error in test4");
      return false;
    }
    expectedSize = 5;
    actualSize = hashTable.size();
    if (expectedSize != actualSize) {
      System.out.println("(3) Error in test4");
      return false;
    }

    // (4) Catches the NoSuchElementException in multi-element hashTable
    try {
      hashTable.get("Water");
      System.out.println("(4) Error in test4");
      return false;
    } catch (NoSuchElementException f) {
      System.out.println("Test4 (4) Passed Exception");
    }

    return true;
  }

  /**
   * Tests the functionality of containsKey() method
   * 
   * @return - true if all tests passed, false otherwise
   */
  public static boolean test5() {
    // Tests the containsKey() method within the HashtableMap class
    HashtableMap<String, Integer> hashTable = new HashtableMap<String, Integer>(5);
    
    // (1) containsKey() method on empty list
    boolean expected = false;
    boolean actual = hashTable.containsKey("Burger");
    if (!actual) {
      System.out.println("(1) Error in test5");
      return false;
    }
    
    // (2) containsKey() method on a 1 element list that contains the item
    hashTable.put("Burger", 2);
    expected = true;
    actual = hashTable.containsKey("Burger");
    if (!actual) {
      System.out.println("(2) Error in test5");
      return false;
    }
    
    // (3) containsKey() method on a 1 element list that doesn't contain the item
    expected = false;
    actual = hashTable.containsKey("Fries");
    if (!actual) {
      System.out.println("(3) Error in test5");
      return false;
    }
    
    hashTable.put("Fries", 1);
    hashTable.put("Wings", 5);
    hashTable.put("Chicken Tenders", 6);
    hashTable.put("Sushi", 10);
    
    // (4) containsKey() method on a multi-element hashTable that contains the item
    expected = true;
    actual = hashTable.containsKey("Chicken Tenders");
    if (!actual) {
      System.out.println("(4) Error in test5");
      return false;
    }
    
    // (5) containsKey() method on a multi-element hashTable that does not contain the item
    expected = false;
    actual = hashTable.containsKey("Ice Cream");
    if (!actual) {
      System.out.println("(5) Error in test5");
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println("Test1 Passed All Tests: " + test1());
    System.out.println("Test2 Passed All Tests: " + test2());
    System.out.println("Test3 Passed All Tests: " + test3());
    System.out.println("Test4 Passed All Tests: " + test4());
    System.out.println("Test5 Passed All Tests: " + test5());
  }

}
