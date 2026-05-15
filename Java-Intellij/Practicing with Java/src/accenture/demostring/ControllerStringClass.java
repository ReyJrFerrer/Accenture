package accenture.demostring;

import accenture.demostring.StringClass;
public class ControllerStringClass {
    public static void main(String[] args) {
        StringClass myObject = new StringClass();
        StringClass myObject2 = new StringClass();

        System.out.println(myObject);
        System.out.println(myObject.myStaticString);

        new StringClass(); // anonymous objects


        myObject.myStaticString = "Hi";
        System.out.println(StringClass.myStaticString);
        System.out.println(myObject.myStaticString);

        myObject.myNonstaticString = "Hello!";

        System.out.println(myObject.myNonstaticString);
        System.out.println(myObject2.myNonstaticString);


        myObject.nonStaticMethod();
        myObject.staticMethod();
    }
}
