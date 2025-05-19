package local.error;

public class LabelNotFound extends RuntimeException{
    public LabelNotFound(String message) {
        super(message);
    }

    public LabelNotFound(String message, Exception e) {
        super(message, e);
    }
}
