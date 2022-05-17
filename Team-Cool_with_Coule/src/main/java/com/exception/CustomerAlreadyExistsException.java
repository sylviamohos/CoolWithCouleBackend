package main.java.com.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException() {
    }
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
    public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomerAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
