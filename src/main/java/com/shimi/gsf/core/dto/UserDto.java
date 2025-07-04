package com.shimi.gsf.core.dto;

/**
 * UserDto is an interface that represents a Data Transfer Object (DTO) for a user.
 * It extends the Dto interface and provides methods to get user-related information.
 */
public interface UserDto extends Dto {
    String getUsername();
    String getNickname();
    String getAvatarUrl();
}
