package com.dauo.project.common.base;

import com.dauo.project.common.code.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

public class Responses {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class DataResponse<T> {
        private final T data;

        public static <T> DataResponse<T> of(T data) {
            return DataResponse.<T>builder()
                    .data(data)
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ErrorResponse {
        private final String code;
        private final String message;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private final List<?> errors;

        public static ErrorResponse of(ErrorCode errorCode) {
            return ErrorResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build();
        }

        public static ErrorResponse of(ErrorCode errorCode, List<?> errors) {
            return ErrorResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .errors(errors)
                    .build();
        }

        @Getter
        @Builder
        @RequiredArgsConstructor
        public static class ValidationError {
            private final String field;
            private final String message;

            public static ValidationError of(final FieldError fieldError) {
                return ValidationError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build();
            }
        }
    }
}