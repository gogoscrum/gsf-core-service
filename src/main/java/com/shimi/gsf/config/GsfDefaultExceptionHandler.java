package com.shimi.gsf.config;

import com.shimi.gsf.core.exception.BaseServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GsfDefaultExceptionHandler extends ResponseEntityExceptionHandler {
    public static final Logger log = LoggerFactory.getLogger(GsfDefaultExceptionHandler.class);

    @ExceptionHandler(BaseServiceException.class)
    protected ResponseEntity<Object> handleBaseException(BaseServiceException ex) {
        log.error("Exception occurred:", ex);

        Map<String, Object> result = new HashMap<>();

        result.put("code", ex.getCode());
        result.put("message", ex.getMessage());

        if(!CollectionUtils.isEmpty(ex.getExtendValues())) {
            result.put("extendValues", ex.getExtendValues());
        }

        return new ResponseEntity<>(result, ex.getStatus());
    }
}
