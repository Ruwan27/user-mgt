package lk.demo.app.util;

import lk.demo.app.util.exception.InvalidTraceIdException;
import lk.demo.app.util.exception.NullTraceIdException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraceIdUtil {
    public static void validateTraceId(String traceId) throws InvalidTraceIdException, NullTraceIdException {
        if (traceId == null) {
            throw new NullTraceIdException();
        }
        if (!traceId.matches("^[a-zA-Z]{3}\\d{1,12}$")) {
            throw new InvalidTraceIdException();
        }
    }
}
