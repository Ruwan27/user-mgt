package lk.demo.app.util;

import lk.demo.app.response.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ResponseBuilderUtil {
    private static final String DIALOG_TRACE_ID = "traceId";
    private static final String SUCCESS_MESSAGE = "success";
    private static final String ERROR_TYPE_VALIDATION = "Validation";
    private static final String ORIGIN = "Demo ms - API";
    private static final String ERROR = "error";
    private static final String SUCCESS = "0000";
    public static final String CONFLUENCE_URL = "https://demo.lk/display/app/Error+Codes";
    private static final Logger LOGGER = LogManager.getLogger(ResponseBuilderUtil.class);

    public static ResponseEntity<ResponseMessage> getSuccessResponseMessage(Object value, HttpStatus status, HttpServletRequest request) {
        LOGGER.info("Success : traceId={}", request.getHeader(DIALOG_TRACE_ID));
        HashMap<String, Object> map = new HashMap<>();
        if (value != null) {
            map.put("dmsReturns", value);
        }
        ResponseMessage message = new ResponseMessage.ResponseBuilder()
                .setCode(SUCCESS)
                .setMessage(SUCCESS_MESSAGE)
                .setTraceId(request.getHeader(DIALOG_TRACE_ID))
                .setMap(map)
                .setTimeStamp(DatetimeUtil.dateFormatter(new Date()))
                .build();
        return new ResponseEntity<>(message, status);
    }


    public static ResponseEntity<ResponseMessage> getErrorResponseMessageValidate(HashMap map, String value,
                                                                                  HttpStatus status, String code, HttpServletRequest request) {

        LOGGER.error("Validation : traceId={}|{}", request.getHeader(DIALOG_TRACE_ID), map);
        ResponseMessage message = new ResponseMessage.ResponseBuilder()
                .setCode(code)
                .setMessage(value)
                .setMap(map)
                .setTraceId(request.getHeader(DIALOG_TRACE_ID))
                .setType(ERROR_TYPE_VALIDATION)
                .setOrigin(ORIGIN)
                .setDetails(CONFLUENCE_URL)
                .setTimeStamp(DatetimeUtil.dateFormatter(new Date()))
                .build();
        return new ResponseEntity<>(message, status);
    }

    public static ResponseEntity<ResponseMessage> getErrorResponseMessage(String value, HttpStatus status, String code, Error error, HttpServletRequest request) {
        List<Object> errors = new ArrayList<>();
        errors.add(error);

        HashMap<String, Object> data = new HashMap<>();
        data.put(ERROR, errors);
        LOGGER.error("Validation : traceId = {} | {}", request.getHeader(DIALOG_TRACE_ID), error.getMessage());
        ResponseMessage message = new ResponseMessage.ResponseBuilder()
                .setCode(code)
                .setMessage(value)
                .setTraceId(request.getHeader(DIALOG_TRACE_ID))
                .setType(ERROR_TYPE_VALIDATION)
                .setOrigin(ORIGIN)
                .setDetails(CONFLUENCE_URL)
                .setMap(data)
                .setTimeStamp(DatetimeUtil.dateFormatter(new Date()))
                .build();
        return new ResponseEntity<>(message, status);
    }
}
