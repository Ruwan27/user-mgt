package lk.demo.app.util.exception;

public class InvalidTraceIdException extends Exception {
    private static final long serialVersionUID = 1199834084640342462L;

    public InvalidTraceIdException() {
        super("Invalid Trace ID");
    }
}
