package com.shimi.gsf.core.model;

import com.shimi.gsf.core.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User is an interface that represents a user in the system.
 */
public interface User extends Entity, UserDetails {
    /**
     * Get the username of the user. This is the unique identifier for the user.
     * @return the username of the user
     */
    String getUsername();

    /**
     * Get the password of the user. The nickname is used for display purposes, and can be duplicated in the system.
     * @return the nickname of the user
     */
    String getNickname();

    /**
     * Get the avatar image URL of the user. This is used for display purposes.
     * @return the avatar URL of the user
     */
    String getAvatarUrl();

    /**
     * Convert the user to a UserDto (Data Transfer Object).
     * @return the UserDto representation of the user
     */
    @Override
    UserDto toDto();
}
