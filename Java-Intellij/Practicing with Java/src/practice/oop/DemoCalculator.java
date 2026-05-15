package practice.oop;
import java.util.InputMismatchException;
import java.util.Scanner;
public class DemoCalculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        calculator.displayChoices();
        try {
        int choice = scanner.nextInt();
            System.out.print("Enter first number: ");
            double a = scanner.nextDouble();
            System.out.print("Enter second number: ");
            double b = scanner.nextDouble();

        switch (choice){
            case 1:
                calculator.display(calculator.add(a,b));
                break;
            case 2:
                calculator.display(calculator.subtract(a,b));
                break;
            case 3:
                calculator.display(calculator.multiply(a,b));
                break;
            case 4:
                calculator.display(calculator.divide(a,b));
                break;
        }
        } catch (InputMismatchException exception){
            System.out.println("Please input a valid number");

        }

        scanner.close();

    }

}
