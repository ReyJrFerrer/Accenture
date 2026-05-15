package accenture.customer;

import accenture.customer.exception.InvalidCardNumLengthException;
import accenture.customer.exception.InvalidCardNumLuhnException;

import java.util.InputMismatchException;
import java.util.Scanner;


public class DemoClassAndObject {

    public static void main(String[] args) throws InterruptedException{
        Scanner scan = new Scanner(System.in);
        BpiCardServiceDivision bpi = new BpiCardServiceDivision();
        try {
            System.out.print("Enter customer name: ");
            String name = scan.nextLine();
            //Display.displayMenuSelection(); //call to method overload 1
            Display.displayMenuSelection("\nAvailable Fuel Types"); //call to method overload 2
            System.out.print("\nSelect your fuel: ");
            String fuelType = scan.nextLine();
            System.out.print("Enter your card no: "); // 4111111111111111
            String cardNo = scan.nextLine();
            System.out.print("Enter number of liters: ");
            double litersOrdered = scan.nextDouble();
            Customer customerObj = new Customer(name, fuelType, litersOrdered, bpi);
            if (customerObj.getBpiCardService().isCardNumberLength16(cardNo)){
                if (customerObj.getBpiCardService().isCardNumberLuhnValid(cardNo)){
                    customerObj.computeFuelPurchase();
                    Display.printTrasactionDetail(customerObj);
                } else {
                    throw new InvalidCardNumLuhnException();
                }
            } else {
                throw new InvalidCardNumLengthException();
            }
        }

        catch (InputMismatchException e){
            System.out.println("Enter a valid number when inputting the fuel, card no. and the number of liters");

        } catch (InvalidCardNumLengthException | InvalidCardNumLuhnException e) {
            System.out.println(e.getMessage());
        }

        finally {
            char[] acMessage = "Thank you for buying our product".toCharArray();
            for (char message : acMessage){
                    System.out.print(message);
                    Thread.sleep(300);

            }
            System.out.println("\n");
            scan.close();
        }

    }

}