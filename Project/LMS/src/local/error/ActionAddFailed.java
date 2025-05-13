package local.error;

public class ActionAddFailed extends RuntimeException {
    public ActionAddFailed(String message) {
        super(message);
    }

    public ActionAddFailed(String message, Throwable cause) {
        super(message, cause);
    }
    
}
