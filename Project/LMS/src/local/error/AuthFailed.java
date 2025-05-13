package local.error;

public class AuthFailed extends RuntimeException {
    public AuthFailed(String message) {
        super(message);
    }
    public AuthFailed(String message, Throwable cause) {
        super(message, cause);
    }
}
