package demo.exceptionHandler;

/**
 * Customed Exception which is to be cauge by ExceptionHandler.
 * The reason of using RuntimeException is because only RuntimeException type could be caught at any processing point in Service,
 * otherwise, all stacked methods should catch the Exception till it is caught by the Exception Handler.
 */
public class CustomedException extends RuntimeException {
    private String exceptionCode; // suggest to create one ExceptionCode
    public CustomedException(String exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
