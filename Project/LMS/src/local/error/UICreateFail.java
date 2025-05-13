package local.error;

public class UICreateFail extends RuntimeException {
    public UICreateFail(String message) {
        super(message);
    }
    public UICreateFail(String message, Throwable cause) {
        super(message, cause);
    }
}
