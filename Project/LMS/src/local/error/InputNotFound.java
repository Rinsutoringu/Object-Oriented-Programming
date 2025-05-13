package local.error;

public class InputNotFound extends RuntimeException{
    public InputNotFound(String message) {
        super(message);
    }

    public InputNotFound(String message, Throwable cause) {
        super(message, cause);
    }
    
}
