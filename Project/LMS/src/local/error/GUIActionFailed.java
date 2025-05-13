package local.error;

public class GUIActionFailed extends RuntimeException {
    public GUIActionFailed(String message) {
        super(message);
    }
    public GUIActionFailed(String message, Throwable cause) {
        super(message, cause);
    }
}
