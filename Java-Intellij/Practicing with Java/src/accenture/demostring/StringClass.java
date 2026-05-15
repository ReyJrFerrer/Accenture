package accenture.demostring;

public class StringClass {

    static { // Static block/ initializer
        // This always runs first before the main method
        // This only runs once
        myStaticString = "I am a static string";
        System.out.println("This is the static block");
    }
    String myNonstaticString;
    static String myStaticString;
    public StringClass(){
        myNonstaticString = "I am a non-static string";


        System.out.println(myNonstaticString);
        System.out.println(myStaticString);
    }
    public void nonStaticMethod(){
        System.out.println("Non-static method "); // owned by the object
    }
    public static void staticMethod(){
        System.out.println("I am a static method"); // owned by the class
    }

}
