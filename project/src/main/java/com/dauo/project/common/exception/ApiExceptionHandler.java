package com.dauo.project.common.exception;

import com.dauo.project.common.base.Responses;
import com.dauo.project.common.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Responses.ErrorResponse> handleApiException(ApiException e) {
        ErrorCode errorCode = e.getErrorCode();
        Responses.ErrorResponse response = Responses.ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Responses.ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;
        Responses.ErrorResponse response = Responses.ErrorResponse.of(errorCode, makeValidationErrors(e.getBindingResult()));
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Responses.ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException : {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;
        Responses.ErrorResponse response = Responses.ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Responses.ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException : {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.INVALID_METHOD;
        Responses.ErrorResponse response = Responses.ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Responses.ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("AccessDeniedException : {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED;
        Responses.ErrorResponse response = Responses.ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Responses.ErrorResponse> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.SYSTEM_ERROR;
        Responses.ErrorResponse response = Responses.ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    private List<Responses.ErrorResponse.ValidationError> makeValidationErrors(BindingResult bindingResult) {
        List<Responses.ErrorResponse.ValidationError> validationErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(Responses.ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return validationErrors;
    }
}