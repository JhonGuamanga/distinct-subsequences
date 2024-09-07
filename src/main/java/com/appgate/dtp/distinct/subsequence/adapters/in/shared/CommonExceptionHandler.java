package com.appgate.dtp.distinct.subsequence.adapters.in.shared;

import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.InvalidSequenceLengthException;
import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.InvalidSubsequenceToSearchLengthException;
import com.appgate.dtp.shared.utils.AppgateLogger;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

public class CommonExceptionHandler {

    private static final AppgateLogger log = AppgateLogger.getLogger(CommonExceptionHandler.class.getName());
    public static final String BAD_REQUEST_MSG = "Bad Request";
    public static final String CONFLICT_REQUEST_MSG = "Conflict";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception exception) {
        log.error("[ERROR] Unexpected error", exception);
        return new ErrorMessage("Unexpected error");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.warn("HttpMessageNotReadableException: ", exception);
        return new ErrorMessage("Invalid payload");
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.warn("MethodArgumentTypeMismatchException: ", exception);
        return new ErrorMessage(BAD_REQUEST_MSG, List.of(new ErrorDetail(
            exception.getPropertyName(),
            exception.getValue() != null ? exception.getValue().toString() : "null",
            "Invalid value")));
    }

    @ExceptionHandler({MissingPathVariableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMissingPathVariableException(MissingPathVariableException exception) {
        log.warn("MissingPathVariableException: ", exception);
        return new ErrorMessage(BAD_REQUEST_MSG, List.of(new ErrorDetail(
            exception.getVariableName(),
            "",
            "'" + exception.getVariableName() + "' field must not be empty")));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("MethodArgumentNotValidException: ", exception);
        return new ErrorMessage(BAD_REQUEST_MSG);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleConstraintViolationException(ConstraintViolationException exception) {
        log.warn("ConstraintViolationException: ", exception);
        if(exception.getConstraintViolations() != null ){
            return new ErrorMessage(BAD_REQUEST_MSG,
                exception.getConstraintViolations().stream().map(
                    violation -> new ErrorDetail(
                        violation.getPropertyPath().toString(),
                        violation.getInvalidValue() != null ? violation.getInvalidValue().toString() : "null", violation.getMessage())
                )
                    .toList());
        }
        List<ErrorDetail> errorDetailList = new ArrayList<>();
        ErrorDetail errorDetail = new ErrorDetail(null, null, exception.getMessage());
        errorDetailList.add(errorDetail);
        return new ErrorMessage(BAD_REQUEST_MSG, errorDetailList);
    }

    @ExceptionHandler(InvalidSequenceLengthException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleInvalidSequenceLengthException(InvalidSequenceLengthException exception) {
        log.warn("InvalidSequenceLengthException: ", exception.getMessage());
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(InvalidSubsequenceToSearchLengthException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleInvalidSubsequenceToSearchLengthException(InvalidSubsequenceToSearchLengthException exception) {
        log.warn("InvalidSubsequenceToSearchLengthException: ", exception.getMessage());
        return new ErrorMessage(exception.getMessage());
    }
}
