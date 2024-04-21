package ee.viinamae.tuum.exceptions;

public class InvalidCurrencyException extends Exception {
    public InvalidCurrencyException(String errorMessage) {
        super(errorMessage);
    }
}
