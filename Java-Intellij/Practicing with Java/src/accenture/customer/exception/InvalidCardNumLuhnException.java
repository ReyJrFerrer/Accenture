package accenture.customer.exception;

public class InvalidCardNumLuhnException extends RuntimeException{
    public InvalidCardNumLuhnException(){
        super("\nInvalid card number");
    }
}
