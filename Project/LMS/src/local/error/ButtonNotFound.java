package local.error;

public class ButtonNotFound extends RuntimeException{
    public ButtonNotFound(String message) {
        super(message);
    }

    public ButtonNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}
