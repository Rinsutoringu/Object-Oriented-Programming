package local.utils.error;

public class FileNotExist extends RuntimeException {
    
    public FileNotExist (String message) {
        super(message);
    }
    
    public FileNotExist (String message, Exception e) {
        super(message, e);
    }
}
