package com.dauo.project.common.utils;

import com.dauo.project.common.code.ErrorCode;
import com.dauo.project.common.exception.ApiException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatetimeUtils {
    public static LocalDateTime parseyyyyMMddHH(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new ApiException(ErrorCode.INVALID_PARAMETER);
        }
    }
}
