package backend.customException;

public class SaveException extends Exception{
    public SaveException(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
