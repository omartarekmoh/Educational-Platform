package com.education.platform.service;

import com.education.platform.exception.ResourceNotFoundException;
import com.education.platform.repository.UserRepository;
import com.education.platform.security.entity.User;
import com.education.platform.service.implementation.UserServiceImpl;
import com.education.platform.dto.UserRegistrationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserRegistrationDTO registrationDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFullName("Test User");
        user.setUserType(User.UserType.STUDENT);

        registrationDTO = new UserRegistrationDTO();
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setFullName("Test User");
        registrationDTO.setUserType(User.UserType.STUDENT);
    }

    @Test
    void registerUser_Success() {
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.registerUser(registrationDTO);

        assertNotNull(createdUser);
        assertEquals(registrationDTO.getEmail(), createdUser.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getUserById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(user.getId(), foundUser.get().getId());
    }

    @Test
    void getUserByEmail_Success() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void getUsersByType_Success() {
        List<User> users = Arrays.asList(user);
        when(userRepository.findByUserType(User.UserType.STUDENT)).thenReturn(users);

        List<User> foundUsers = userService.getUsersByType(User.UserType.STUDENT);

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals(User.UserType.STUDENT, foundUsers.get(0).getUserType());
    }

    @Test
    void updateUser_Success() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals(user.getId(), updatedUser.getId());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void deleteUser_Success() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void existsByEmail_Success() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        boolean exists = userService.existsByEmail("test@example.com");

        assertTrue(exists);
    }
} 