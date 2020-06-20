package exceptions;

public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(String number) {
        super(number);
    }
}
