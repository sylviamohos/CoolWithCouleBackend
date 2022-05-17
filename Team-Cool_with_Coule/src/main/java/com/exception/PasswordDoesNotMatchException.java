package main.java.com.exception;

public class PasswordDoesNotMatchException extends RuntimeException{
    public PasswordDoesNotMatchException() {
    }
    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
    public PasswordDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
    public PasswordDoesNotMatchException(Throwable cause) {
        super(cause);
    }
}
