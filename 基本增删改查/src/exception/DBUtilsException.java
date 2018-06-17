package exception;

public class DBUtilsException extends Exception {

    private final Exception exception;

    public DBUtilsException(Exception e) {
        this.exception = e;
    }

    public Exception getException() {
        return exception;
    }
}
