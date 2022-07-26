package backend.customException;

public class InvalidDataException extends Exception{
    public InvalidDataException(String message){
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
