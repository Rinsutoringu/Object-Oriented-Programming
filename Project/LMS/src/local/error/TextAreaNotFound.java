package local.error;

public class TextAreaNotFound extends RuntimeException{
    public TextAreaNotFound(String message) {
        super(message);
    }

    public TextAreaNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
