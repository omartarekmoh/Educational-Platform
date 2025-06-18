package com.education.platform.dto;

import com.education.platform.security.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String fullName;
    private User.UserType userType;
} 