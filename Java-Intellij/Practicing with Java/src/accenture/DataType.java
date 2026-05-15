package accenture;

public class DataType {
    //Instance Variable Declaration and Initialization
    double myDouble = 3.14; // Double (double)
    int myIntInstance ; // Integer (int)

    // Static Variable Declaration and Initialization
    static String myString = "Hello, World!"; // String (String)


    public static void main(String[] args) {

        System.out.println("Data Types in Java:");

        //Local Variable Declaration and Initialization
        int myInt = 10; // Integer (int)
        System.out.println("Integer (int): " + myInt);

        //instance variable can be accessed by creating an object of the class
        DataType dataType = new DataType();
        System.out.println("Double (double): " + dataType.myDouble);
        System.out.println("Integer (int) instance variable: " + dataType.myIntInstance);

        // Static variable can be accessed directly using the class name
        System.out.println("String (String): " + DataType.myString);


        // Data type Integer Types

        byte myByte = 127; // Byte (byte)
        short myShort = 32_767; // Short (short)
        int myInt2 = 483_647; // Integer (int)
        long myLong = 4_775_807L; // Long (long
        System.out.println("\nByte (byte): " + myByte);
        System.out.println("Short (short): " + myShort);
        System.out.println("Long (long): " + myLong);
        System.out.println("Integer (int): " + myInt2);

        // Data type Floating-Point Types
        float myFloat = 3.141592653589793f; // Float (float)
        double myDouble2 = 3.141592653589793; // Double (double
        System.out.println("\nFloat (float): " + myFloat);
        System.out.println("Double (double): " + myDouble2);

        // Data type Character Type
        char myChar = 'A'; // Character (char)
        System.out.println("\nCharacter (char): " + myChar);

        // Data type Boolean Type
        boolean myBoolean = true; // Boolean (boolean)
        System.out.println("\nBoolean (boolean): " + myBoolean);

        // Data type String Type
        String myString2 = "Hello, Java!"; // String (String)
        System.out.println("\nString (String): " + myString2);

        // Data type Array Type
        int[] myIntArray = {1, 2, 3, 4, 5}; // Array of integers
        System.out.print("\nArray of integers: ");



    }
}
