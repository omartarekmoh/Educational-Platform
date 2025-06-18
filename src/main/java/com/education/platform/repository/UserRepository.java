package com.education.platform.repository;

import com.education.platform.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByUserType(User.UserType userType);
    List<User> findByUserTypeAndDepartment(User.UserType userType, String department);
} 