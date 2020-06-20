package exceptions;

public class IllegalMailException extends RuntimeException {
    public IllegalMailException(String mail) {
        super(mail);
    }
}
