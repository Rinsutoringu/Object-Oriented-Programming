package local.error;

public class UserInfoError extends RuntimeException {
    public UserInfoError(String message) {
        super(message);
    }
    public UserInfoError(String message, Throwable cause) {
        super(message, cause);
    }
}
