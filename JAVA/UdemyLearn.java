//! Static varibles ...static keyword is used to declare a variable that belongs to the class rather than to any specific instance of the class.

class Mobile {

    String brand;//*instance varibles
    int price;
    String name;

    public void show() {
        System.out.println("Brand: " + brand);
        System.out.println("Price: " + price);
        System.out.println("Name: " + name);
    }

    //*static Method: A static method belongs to the class rather than to any specific instance of the class.
    public static void show1() {
        System.out.println("This is a static method.");

    }

    public static void show2(Mobile obj) {
        System.out.println("Brand: " + obj.brand);
        System.out.println("Price: " + obj.price);
        System.out.println("Name: " + obj.name);
    }

    //* static block: A static block is used to initialize static variables or perform static initialization when the class is loaded.
    static {
        System.out.println("\nStatic block executed.");
    }

    public Mobile() {
        System.out.println("Constructor called.");
        //* constructor: A constructor is a special method that is called when an object of the class is created.
        //* It is used to initialize the instance variables of the class.
    }
}
//! Encapsulation: Encapsulation is the concept of wrapping data (variables) and methods (functions) together in a single unit (class).
// It restricts direct access to some of the object's components and can prevent the accidental modification of data.
// Encapsulation is achieved by using access modifiers (public, private, protected) to control the visibility of class members.

class Human {

    private int age=11;
    private String name="Siva";
    //* We cannot access private variables directly from outside the class.
    // The only way to access them is through public methods (getters and setters).
    public int getAge() {
        return age;
    }
    public String getName(){ //? Getter method to access private variable name
        return name;
    }
    public void setAge1(int age/* , Human obj*/) { 
        // Human h=obj;
        // h.age = age;
        //? In above we have pass the obj to set value to instance variable age.
        this.age = age; //? Using 'this' keyword to refer to the current instance's variable
    }
    public void setAge(int a,String n) {//? Setter method to set the value of private variable age
        age = a;
        name =n;
    }

}

public class UdemyLearn {

    public static void main(String[] args) {
        System.out.println("Hello, Udemy!");
        // This is a simple Java program that prints a message to the console.
        // You can add more functionality here as you learn more about Java.

        //! String is an object that represents Sequence of characters.
        // Strings in Java are immutable, meaning once created, they cannot be changed.
        // When you modify a string, a new string is created in memory.
        //THe memory allocation is taken as below.
        //  //?/*In java memory allocation can be takes place in two ways.
        //   * Stack:Used for Method execution and local Variables.
        //           Short-lived,memory is automatically freed when the method call ends.
        //           For strings it only references  to strings are stored here... 
        //   *Heap :Used for all objects in java.
        //          Long-lived (survive multiple method calls if needed).
        //          Managed by garbage collector.
        //         It create string obects one in heap and another in Stringpool.
        //   *Stringpool:A special area in heap memory and strings in pool are immutable and shared.       
        //   */
        //Intern():used to add a heap string to the string pool to return the existing reference from the pool.
        System.out.println("Intern():");
        String Me = "Siva";
        String u = Me.intern();
        System.out.println(u + "\n");
        System.out.println("Strings:");
        String Name = new String("Siva");
        String NameEnd = "Reddy";
        System.out.println("My name is " + Name + " " + NameEnd);
        System.out.println(Name.concat(NameEnd));
        System.out.println("Length:  " + Name.length());
        System.out.println("Uppercase: " + Name.toUpperCase());
        System.out.println("Lowercase: " + Name.toLowerCase());
        System.out.println("Substring: " + Name.substring(1, 3)); // Extracts substring from index 1 to 3 (exclusive)
        System.out.println("Character at index 2: " + Name.charAt(2)); // Gets character at index 2
        String name = "siva";
        System.out.println("Equals:" + Name.equals(name));
        System.out.println("EqualsIgnoreCase:" + Name.equalsIgnoreCase(name));
        System.out.println("Starts with 'S': " + Name.startsWith("S")); // Checks if string starts with 'S'
        System.out.println("Ends with 'y': " + NameEnd.endsWith("y")); // Checks if string ends with 'y'
        System.out.println("Index of 'i': " + Name.indexOf('i')); // Finds index of character 'i'       
        System.out.println("Last index of 'i': " + Name.lastIndexOf('i')); // Finds last index of character 'i'     
        System.out.println("Replace 'i' with 'o': " + Name.replace('i', 'o')); // Replaces 'i' with 'o'
        System.out.println("Split by 'a': " + Name.split("a")[0]); // Splits string by 'a' and gets the first part
        System.out.println("Trimmed Name: " + Name.trim()); // Removes leading and trailing spaces
        System.out.println("Is Empty: " + Name.isEmpty()); // Checks if string is empty
        System.out.println("Is Blank: " + Name.isBlank()); // Checks if string is blank (contains only whitespace)
        System.out.println("Contains 'i': " + Name.contains("i")); // Checks if string contains 'i'
        System.out.println("Compare to 'Siva': " + Name.compareTo("Siva")); // Compares two strings lexicographically
        System.out.println("Compare to Ignore Case 'siva': " + Name.compareToIgnoreCase("siva")); // Compares two strings lexicographically ignoring case
        System.out.println("Format: " + String.format("My name is %s %s", Name, NameEnd)); // Formats string with placeholders
        System.out.println("Value of 10: " + String.valueOf(10)); // Converts integer to string
        System.out.println("Value of 10.5: " + String.valueOf(10.5)); // Converts double to string
        System.out.println("Value of true: " + String.valueOf(true)); // Converts boolean to string
        System.out.println("Value of char 'A': " + String.valueOf('A')); // Converts character to string
        System.out.println("Value of char array: " + String.valueOf(new char[]{'H', 'e', 'l', 'l', 'o'})); // Converts character array to string
        System.out.println("Value of byte array: " + String.valueOf(new byte[]{65, 66, 67})); // Converts byte array to string
        System.out.println("Value of short array: " + String.valueOf(new short[]{65, 66, 67})); // Converts short array to string
        System.out.println("Value of int array: " + String.valueOf(new int[]{65, 66, 67})); // Converts int array to string
        System.out.println("Value of long array: " + String.valueOf(new long[]{65, 66, 67})); // Converts long array to string
        System.out.println("Value of float array: " + String.valueOf(new float[]{65.5f, 66.5f, 67.5f})); // Converts float array to string
        System.out.println("Value of double array: " + String.valueOf(new double[]{65.5, 66.5, 67.5})); // Converts double array to string
        System.out.println("Value of boolean array: " + String.valueOf(new boolean[]{true, false, true})); // Converts boolean array to string
        System.out.println("Value of Object array: " + String.valueOf(new Object[]{"Hello", "World"})); // Converts Object array to string

        //! StringBuffer and StringBuilder are used for mutable strings.
        // StringBuffer is synchronized, making it thread-safe but slower.
        // It is used when you need to modify strings in a multi-threaded environment.
        System.out.println("\n StringBuffer (class):");
        StringBuffer sb = new StringBuffer("Siva");
        sb.append(" Reddy");
        System.out.println("append: " + sb);
        sb.insert(0, "Hello ");// Inserting "Hello " at the beginning
        System.out.println("insert: " + sb);
        sb.replace(3, 9, "Java123");// Replacing characters from index 3 to 9 with "Java"
        System.out.println("replace: " + sb);
        sb.delete(5, 6);// Deleting character at index 5
        System.out.println("delete: " + sb);
        sb.reverse(); // Reversing the string
        System.out.println("reverse: " + sb);
        sb.setCharAt(2, 'B'); // Setting character at index 2 to 'B'
        System.out.println("setCharAt: " + sb);
        System.out.println("sb.toString(): " + sb.toString());//Converts string buffer to string.
        System.out.println("Capacity: " + sb.capacity()); // Capacity of the StringBuffer
        System.out.println("Length:  " + sb.length());

        //! StringBuilder is similar to StringBuffer but is not synchronized, 
        // making it faster for single-threaded operations.
        // It is used when you need to modify strings in a single-threaded environment.
        // It is generally preferred for performance reasons.
        // StringBuilder is not thread-safe, so it should be used when synchronization is not a concern.
        // It is faster than StringBuffer.
        System.out.println("\nStringBuilder:");
        StringBuilder sb2 = new StringBuilder("Siva");
        sb2.append(" Reddy");
        System.out.println(sb2.length());

        //!/*Static variables*/
        Mobile obj1 = new Mobile();
        obj1.brand = "samsung";
        obj1.price = 1000;
        obj1.name = "Galaxy S21";
        System.out.println("\nStatic Variables:");
        obj1.show();
        //static keyword is used to declare a variable that belongs to the class rather than to any specific instance of the class.
        //static variables are shared among all instances of the class.

        System.out.println("\nStatic Method:");
        //*Static Method: A static method belongs to the class rather than to any specific instance of the class.
        Mobile.show1(); // Calling the static method directly using the class name
        Mobile.show2(obj1); // Calling the static method with an object of the class
        // Static variables are initialized when the class is loaded, and they exist for the lifetime of the application.

        //! Encapsulation
        System.out.println("\nEncapsulation:");
        Human human = new Human();
        human.setAge(25,"siva"); // Setting age using setter method
        System.out.println("Age: " + human.getAge()); // Accessing private variable through getter method
        System.out.println("Name: " + human.getName());

        //! Getters and Setters
        // Getters and setters are public methods that allow controlled access to private variables.
 
        //! This keyword
        // The `this` keyword is used to refer to the current instance of the class.
        System.out.println("\nThis keyword:");
        human.setAge1(30); // Setting age using setter method
        System.out.println("Updated Age: " + human.getAge()); // Accessing updated age through getter method

        //! Constructor
        System.out.println("\nConstructor:");

        
    }
}
