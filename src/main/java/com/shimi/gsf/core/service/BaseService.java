package com.shimi.gsf.core.service;

import com.shimi.gsf.core.exception.BaseServiceException;
import com.shimi.gsf.core.exception.ErrorCode;
import com.shimi.gsf.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * BaseService is a base class for all services in the application.
 * It provides common functionality such as getting the current user and publishing events.
 */
public abstract class BaseService {
    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    /**
     * Get the current user from the security context.
     * @return the current user, or null if the user is not authenticated.
     */
    protected User getCurrentUser() {
        return getCurrentUser(true);
    }

    protected User getCurrentUser(boolean nullable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof User) {
            return (User) auth.getPrincipal();
        } else if (!nullable) {
            throw new BaseServiceException(ErrorCode.USER_UNAUTHORIZED,
                    "The user is unauthorized or the login has expired"
                    , HttpStatus.UNAUTHORIZED);
        } else {
            return null;
        }
    }
}
