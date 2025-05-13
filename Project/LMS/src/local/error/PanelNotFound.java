package local.error;

public class PanelNotFound extends RuntimeException{
    public PanelNotFound(String message) {
        super(message);
    }

    public PanelNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}