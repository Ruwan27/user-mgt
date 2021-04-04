package lk.demo.app.util.exception;

public class NullTraceIdException extends Exception {
    public NullTraceIdException() {
        super("Null Trace ID");
    }
}
