package com.shimi.gsf.core.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * BadRequestException is a custom exception class that extends BaseServiceException.
 * It is used to represent exceptions that occur when a request is invalid or cannot be
 * processed due to client-side errors.
 */
public class BadRequestException extends BaseServiceException {
    @Serial
    private static final long serialVersionUID = -6715829320450426979L;
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(ErrorCode.INVALID_REQUEST_DATA, message, HTTP_STATUS);
    }

    public BadRequestException(String code, String message) {
        super(code, message, HTTP_STATUS);
    }

    public BadRequestException(String code, String message, Throwable cause) {
        super(code, message, HTTP_STATUS, cause);
    }
}
