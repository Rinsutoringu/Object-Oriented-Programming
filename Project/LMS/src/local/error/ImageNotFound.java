package local.error;

public class ImageNotFound extends RuntimeException{
    public ImageNotFound(String message) {
        super(message);
    }

    public ImageNotFound(String message, Throwable cause) {
        super(message, cause);
    }
    
}
