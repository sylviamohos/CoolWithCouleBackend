package main.java.com.exception;

public class ProductDoesNotExistException extends RuntimeException{
    public ProductDoesNotExistException() {
    }
    public ProductDoesNotExistException(String message) {
        super(message);
    }
    public ProductDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public ProductDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
