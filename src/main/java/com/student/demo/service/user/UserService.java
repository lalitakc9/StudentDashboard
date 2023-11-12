package com.student.demo.service.user;

import com.student.demo.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;

import java.util.Optional;

public interface UserService {
    public User create(User user) throws AuthenticationException;

    public User login(User user) throws AuthenticationException;

    Optional<User> findByUserName(String username);
}
