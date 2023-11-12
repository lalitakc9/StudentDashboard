package com.student.demo.repository.user;

import com.student.demo.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> getDetailsByUsername(String username);
}
