package com.shimi.gsf.core.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseServiceException is a custom exception class that extends RuntimeException.
 * It is used to represent exceptions that occur in the service layer of the application.
 * This class includes additional fields for error code, HTTP status, and extended values.
 */
public class BaseServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1183918529004224645L;
    protected String code;
    protected HttpStatus status;
    protected Map<String, Object> extendValues = new HashMap<>();

    public BaseServiceException(String message) {
        super(message);
    }

    public BaseServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseServiceException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public BaseServiceException(String code, String message, HttpStatus status, Map<String, Object> extendValues) {
        super(message);
        this.code = code;
        this.status = status;
        this.extendValues = extendValues;
    }

    public BaseServiceException(String code, String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.status = status;
    }

    public BaseServiceException(String code, String message, HttpStatus status, Map<String, Object> extendValues, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.status = status;
        this.extendValues = extendValues;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, Object> getExtendValues() {
        return extendValues;
    }
}
