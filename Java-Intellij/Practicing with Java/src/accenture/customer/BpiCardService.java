package accenture.customer;

import accenture.customer.exception.InvalidCardNumLengthException;
import accenture.customer.exception.InvalidCardNumLuhnException;

public abstract class BpiCardService { //parent class

    public abstract boolean isCardNumberLength16(String number) throws InvalidCardNumLengthException;

    public boolean isCardNumberLuhnValid(String s) throws InvalidCardNumLuhnException {
        System.out.println("Please override this function by providing a business logic to check if "
                + "card number is LUHN valid.");
        return false;
    }
}
