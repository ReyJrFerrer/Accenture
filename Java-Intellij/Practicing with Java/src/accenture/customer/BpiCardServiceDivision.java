package accenture.customer;

import accenture.customer.exception.InvalidCardNumLengthException;
import accenture.customer.exception.InvalidCardNumLuhnException;

/**
 *
 Steps involved in the Luhn algorithm
 Let's understand the algorithm with an example:
 Consider the example of an account number "79927398713".
 Step 1 - Starting from the rightmost digit, double the value of every second digit,
 Step 2 - If doubling of a number results in a two digit number i.e greater than 9(e.g., 6 × 2 = 12), then add the digits of the product (e.g., 12: 1 + 2 = 3, 15: 1 + 5 = 6), to get a single digit number.
 Step 3 - Now take the sum of all the digits.
 Step 4 - If the total modulo 10 is equal to 0 (if the total ends in zero) then the number is valid according to the Luhn formula; else it is not valid.

 Since the sum is 70 which is a multiple of 10, the account number is possibly valid.
 The idea is simple; we traverse from the end. For every second digit, we double it before adding it. We add two digits of the number obtained after doubling.
 *
 */

public class BpiCardServiceDivision extends BpiCardService {

    @Override
    public boolean isCardNumberLength16(String number) throws InvalidCardNumLengthException {
        return number.trim().length() == 16;
    }
    @Override
    public  boolean isCardNumberLuhnValid(String s) throws InvalidCardNumLuhnException {
        char[] charArray = s.toCharArray();
        int numSum = 0;
        for (int i = charArray.length - 1; i >= 0 ; i--){
            int d = Integer.parseInt(String.valueOf(charArray[i]));
            if ((charArray.length - 1 - i) % 2 == 1){
                d = d * 2;
            }
            numSum += d / 10;
            numSum += d % 10;
        }
        return (numSum % 10 == 0);
    }

}
