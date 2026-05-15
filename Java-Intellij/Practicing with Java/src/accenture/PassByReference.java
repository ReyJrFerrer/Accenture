package accenture;

public class PassByReference {
    public static void main(String[] args) {
        // In Java, when we pass an array to a method, we are passing a reference to that array,
        // not a copy of the array itself. This means that any changes made to the array inside
        // the method will affect the original array outside the method.
        double[] accountBalances = {5000.0, 10000.0, 15000.0};

        System.out.println("Before Fee: ");
        for (double bal : accountBalances) {
            System.out.println("$" + bal);
        }

        applyServiceFee(accountBalances);

        System.out.println("\nAfter Fee: ");
        for (double bal : accountBalances) {
            System.out.println("$" + bal);
        }

        //Passing by value example
        int x = 5;
        System.out.println("\nBefore method call: x = " + x);
        modifyValue(x);
        System.out.println("After method call: x = " + x);
    }

    static void modifyValue(int value) {
        value += 10;// This will not affect the original variable x in main

        System.out.println("Inside method: value = " + value);
    }

    // Here, we defined a method called applyServiceFee that accepts an array of doubles representing account balances.
    // Inside the method using the passing by reference simulation, we loop through each balance in the array and deduct a service fee of $10 from each balance.
    static void applyServiceFee(double[] balances) {
        for (int i = 0; i < balances.length; i++) {
            balances[i] -= 10; // Deduct a service fee of $10 from each balance
        }
    }
}
