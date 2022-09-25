package com.dauo.project.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    SYSTEM_ERROR(HttpStatus.BAD_REQUEST, "API_0000", "System Error"),

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "API_0001", "Invalid parameter included."),
    INVALID_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "API_0002", "Method Not Allowed."),

    INVALID_AUTHENTICATE(HttpStatus.UNAUTHORIZED, "API_0101", "Authenticate validation failed."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "API_0102", "Access is Denied."),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "API_0103", "Too many request."),

    NOT_FOUND_DATA(HttpStatus.BAD_REQUEST, "API_0201", "Not found data."),
    FILE_PATH_FAILED(HttpStatus.GONE, "API_0202", "file import path fail."),
    FILE_IMPORT_FAILED(HttpStatus.GONE, "API_0203", "file import fail."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}