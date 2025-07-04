package com.shimi.gsf.core.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * EntityDuplicatedException is a custom exception class that extends BaseServiceException.
 * It is used to represent exceptions that occur when an entity already exists in the system.
 * This class includes additional fields for error code and HTTP status.
 */
public class EntityDuplicatedException extends BaseServiceException {
    @Serial
    private static final long serialVersionUID = -4012421907618201811L;
    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

    public EntityDuplicatedException(String message) {
        super(ErrorCode.ENTITY_ALREADY_EXIST, message, HTTP_STATUS);
    }

    public EntityDuplicatedException(String code, String message) {
        super(code, message, HTTP_STATUS);
    }

    public EntityDuplicatedException(String code, String message, Throwable cause) {
        super(code, message, HTTP_STATUS, cause);
    }
}
