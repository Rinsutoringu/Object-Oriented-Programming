package local.utils.error;

public class CfgError extends RuntimeException {
    
    public CfgError (String message) {
        super(message);
    }
    
    public CfgError (String message, Exception e) {
        super(message, e);
    }
    
}
