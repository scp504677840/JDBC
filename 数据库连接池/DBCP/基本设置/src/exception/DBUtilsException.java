package exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DBUtilsException extends Exception {

    private final Exception exception;

    public DBUtilsException(Exception e) {
        this.exception = e;
    }

    @Override
    public void printStackTrace() {
        //super.printStackTrace();
        exception.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        //super.printStackTrace(s);
        exception.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        //super.printStackTrace(s);
        exception.printStackTrace(s);
    }

    public Exception getException() {
        return exception;
    }
}
