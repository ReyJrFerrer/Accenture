package accenture.customer;

import accenture.customer.Customer;

public class Display {

//method overload 1
        public static void displayMenuSelection() {
            System.out.println("1 - Diesel");
            System.out.println("2 - Unleaded");
            System.out.println("3 - Premium 97");
}

        //method overload 2
        public static void displayMenuSelection(String message) {
        System.out.println(message);
        System.out.println("1 - Diesel");
        System.out.println("2 - Unleaded");
        System.out.println("3 - Premium 97");
        }

        public static void printTrasactionDetail(Customer customer) {
            if (customer.isValidTransaction()) {
                System.out.println("\nCustomer Transaction Details Report");
                System.out.println("Name: " + customer.getName());
                System.out.println("Fuel Type: " + customer.getFuelName());
                System.out.println("Liters Ordered: " + customer.getNumberOfLiters());
                System.out.println("\nGross Amount: Php " + customer.getGrossAmount());
                System.out.println("VAT: Php " + customer.getVatAmount());
                System.out.println("NET AMOUNT: Php " + customer.getNetAmount());
            } else {
                System.err.println("\nInvalid fuel type - please select from options 1-3.");
            }
}
}

