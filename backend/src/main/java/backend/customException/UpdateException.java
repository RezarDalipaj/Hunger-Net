package backend.customException;

public class UpdateException extends Exception{
    public UpdateException(String message){
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
