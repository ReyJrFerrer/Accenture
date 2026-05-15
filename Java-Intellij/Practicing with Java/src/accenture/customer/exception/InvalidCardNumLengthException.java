package accenture.customer.exception;

public class InvalidCardNumLengthException extends Exception {
    public InvalidCardNumLengthException() {
        super( "\nInvalid Card No. - Must be length of 16");
    }
}
