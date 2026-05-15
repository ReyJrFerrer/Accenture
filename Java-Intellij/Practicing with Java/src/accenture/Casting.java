package accenture;

public class Casting {
    public static void main(String[] args) {

        // Implicit Casting (Widening)
        int myInt = 100;
        double myDouble = myInt; // Implicitly casts int to double
        System.out.println("Implicit Casting (Widening):");
        System.out.println("Integer (int): " + myInt);
        System.out.println("Double (double): " + myDouble);

        // Explicit Casting (Narrowing)
        double myDouble2 = 3.14;
        int myInt2 = (int) myDouble2; // Explicitly casts double to int
        System.out.println("\nExplicit Casting (Narrowing):");
        System.out.println("Double (double): " + myDouble2);
        System.out.println("Integer (int): " + myInt2);


    }
}
