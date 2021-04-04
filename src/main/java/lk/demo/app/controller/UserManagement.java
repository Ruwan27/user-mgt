package lk.demo.app.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.demo.app.domain.NewUser;
import lk.demo.app.domain.NewUserResponse;
import lk.demo.app.response.ResponseMessage;
import lk.demo.app.services.UserManagementServices;
import lk.demo.app.util.DatetimeUtil;
import lk.demo.app.util.ResponseBuilderUtil;
import lk.demo.app.util.TraceIdUtil;
import lk.demo.app.util.exception.InvalidTraceIdException;
import lk.demo.app.util.exception.NullTraceIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserManagement {

    private static final String MANDATORY_FIELD_MISSING_TYPE = "Validation failure(s)";
    private static final String MANDATORY_FIELD_MISSING_CODE = "0999";

    private static final String TRACE_ID = "traceId";
    private static final String ERROR_TYPE_VALIDATION = "Validation";
    private static final String ORIGIN = "User ms - API";
    public static final String CONFLUENCE_URL = "https://www.demo.lk/app/Error+Codes";

    private static final String ERROR = "error";
    private static final String FIELD = "field";
    private static final String CODE = "code";
    private static final String MESSAGE = "message";

    private HttpServletRequest request;
    private final UserManagementServices userManagementServices;

    @Autowired
    public UserManagement(HttpServletRequest request, UserManagementServices userManagementServices) {
        this.request = request;
        this.userManagementServices = userManagementServices;
    }

    @PostMapping(value = "/new-user", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "New User create success"),
            @ApiResponse(code = 500, message = "Exception occur when processing create new order")})
    public ResponseEntity<ResponseMessage> UserCreation(@RequestBody NewUser newUser, Errors errors)
            throws InvalidTraceIdException, NullTraceIdException {
        String traceId = request.getHeader(TRACE_ID);
        String user = "PRANEETH";
        TraceIdUtil.validateTraceId(traceId);

        if (true) {
            HashMap<String, String> map = errorValidation(errors, newUser);
            return getErrorResponseMessageValidate(map, MANDATORY_FIELD_MISSING_TYPE, HttpStatus.BAD_REQUEST,
                    MANDATORY_FIELD_MISSING_CODE, request);
        }
        HashMap map = new HashMap();
        NewUserResponse newUserResponse = userManagementServices.addUser(newUser);
        map.put(TRACE_ID, newUserResponse);
        return ResponseBuilderUtil.getSuccessResponseMessage(map, HttpStatus.CREATED, request);
    }

    private HashMap<String, String> errorValidation(Errors errors, NewUser newUser) {
        ArrayList arrayList = new ArrayList();
        if (errors.hasErrors()) {
            for (int i = 0; i < errors.getAllErrors().size(); i++) {
                HashMap map = new HashMap();
                map.put(FIELD, errors.getFieldErrors().get(i).getField());
                map.put(MESSAGE, errors.getFieldErrors().get(i).getDefaultMessage());
                if (errors.getFieldErrors().get(i).getDefaultMessage().contains("invalid size")) {
                    map.put(CODE, "0004");
                } else if (errors.getFieldErrors().get(i).getDefaultMessage().contains("cannot be null")) {
                    map.put(CODE, "0003");
                } else if (errors.getFieldErrors().get(i).getDefaultMessage().contains("invalid input")) {
                    map.put(CODE, "0008");
                }
                arrayList.add(map);
            }
        }
        /*Account ebill reuired "Y" logic impl*/
        if (newUser != null &&
                newUser.getEmpId() != null) {
            HashMap<String, String> errorMap = new HashMap();
            errorMap.put(CODE, "0003");
            errorMap.put(FIELD, "enterpriseNewRegistrationOrder.customer.profile.account.ebill.ebillInfo");
            errorMap.put(MESSAGE, "ebillInfo cannot be null");
            arrayList.add(errorMap);
        }
        HashMap map = new HashMap();
        if (arrayList.isEmpty()) {
            map.put(ERROR, null);
        } else {
            map.put(ERROR, arrayList);
        }
        return map;
    }

    public static ResponseEntity<ResponseMessage> getErrorResponseMessageValidate(HashMap map, String value,
                                                                                  HttpStatus status, String code, HttpServletRequest request) {
        ResponseMessage message = new ResponseMessage.ResponseBuilder()
                .setCode(code)
                .setMessage(value)
                .setMap(map)
                .setTraceId(request.getHeader(TRACE_ID))
                .setType(ERROR_TYPE_VALIDATION)
                .setOrigin(ORIGIN)
                .setDetails(CONFLUENCE_URL)
                .setTimeStamp(DatetimeUtil.dateFormatter(new Date()))
                .build();
        return new ResponseEntity<>(message, status);
    }

    @GetMapping(value = "/list-user", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "list All Users"),
            @ApiResponse(code = 500, message = "Exception occur when processing list all user")})
    public ResponseEntity<ResponseMessage> ListUser(Errors errors)
            throws InvalidTraceIdException, NullTraceIdException {
        return null;
    }

    @PutMapping(value = "/update-user", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Update Users"),
            @ApiResponse(code = 500, message = "Exception occur when processing update all user")})
    public ResponseEntity<ResponseMessage> UpdateUser(@RequestBody NewUser newUser, Errors errors)
            throws InvalidTraceIdException, NullTraceIdException {
        return null;
    }
}
