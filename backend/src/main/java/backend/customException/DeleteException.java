package backend.customException;

public class DeleteException extends Exception{
    public DeleteException(String message){
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
