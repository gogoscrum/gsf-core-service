package com.shimi.gsf.core.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * NoPermissionException is a custom exception class that extends BaseServiceException.
 * It is used to represent exceptions that occur when a user does not have the necessary permissions.
 * This class includes additional fields for error code and HTTP status.
 */
public class NoPermissionException extends BaseServiceException {
    @Serial
    private static final long serialVersionUID = -403906105535908031L;
    private static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

    public NoPermissionException(String message) {
        super(ErrorCode.NO_PERMISSION, message, HTTP_STATUS);
    }

    public NoPermissionException(String code, String message) {
        super(code, message, HTTP_STATUS);
    }

    public NoPermissionException(String code, String message, Throwable cause) {
        super(code, message, HTTP_STATUS, cause);
    }
}
