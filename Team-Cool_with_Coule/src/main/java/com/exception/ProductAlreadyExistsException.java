package main.java.com.exception;

public class ProductAlreadyExistsException extends RuntimeException{

    public ProductAlreadyExistsException() {
    }
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public ProductAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
