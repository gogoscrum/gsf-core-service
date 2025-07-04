package com.shimi.gsf.core.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

/**
 * EntityNotFoundException is a custom exception class that extends BaseServiceException.
 * It is used to represent exceptions that occur when an entity is not found in the system.
 * This class includes additional fields for error code and HTTP status.
 */
public class EntityNotFoundException extends BaseServiceException {
    @Serial
    private static final long serialVersionUID = 5651979474455609596L;
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(String message) {
        super(ErrorCode.ENTITY_NOT_FOUND, message, HTTP_STATUS);
    }

    public EntityNotFoundException(String code, String message) {
        super(code, message, HTTP_STATUS);
    }

    public EntityNotFoundException(String code, String message, Throwable cause) {
        super(code, message, HTTP_STATUS, cause);
    }
}
