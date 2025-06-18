package com.education.platform.service;

import com.education.platform.dto.UserRegistrationDTO;
import com.education.platform.security.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(UserRegistrationDTO registrationDTO);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getUsersByType(User.UserType userType);
    List<User> getEngineersByDepartment(String department);
    User updateUser(User user);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
    List<User> getAllUsers();
    User approveUser(Long userId);
    User suspendUser(Long userId);
    long getTotalUsers();
} 