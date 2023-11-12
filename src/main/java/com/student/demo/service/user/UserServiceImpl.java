package com.student.demo.service.user;

import com.student.demo.entity.User;
import com.student.demo.exception.RecordNotFoundException;
import com.student.demo.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    /**
     * This function creates new user
     * @param userData - an User object
     * @return  a saved User object
     */
    public User create(User userData) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByUsername(userData.getUsername().toLowerCase());
        if (!optionalUser.isPresent() && optionalUser.isEmpty()) {
            User user = new User();
            user.setStatus(userData.getStatus());
            String password = user.getPassword();
            if (password != "") {
                String hashedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
                user.setPassword(hashedPwd);
            }
            user.setFirstName(userData.getFirstName());
            user.setLastName(userData.getLastName());
            user.setUsername(userData.getUsername());
            user.setPhoneNumber(userData.getPhoneNumber());
            return userRepository.save(user);
        }
        else {
            throw new RuntimeException("Username already exists.");
        }
    }

    /**
     * This function provides user login  with username and password
     * @param user - User type object
     * @return  an object of LoginToken
     */
    public User login(User user) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.getDetailsByUsername(user.getUsername().toLowerCase());

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        if (optionalUser.isPresent()) {
            User userDetails = optionalUser.get();
            boolean checkPassword = BCrypt.checkpw(user.getPassword(), userDetails.getPassword());

            if (checkPassword) {
                userDetails.setLastLogin(ts);
                userDetails.setNoFailedLogins(0);
                return userRepository.save(userDetails);
            } else {
                throw new AuthenticationException("User or Password not matched");
            }
        } else {
            throw new AuthenticationException("User or Password not matched");
        }
    }


    @Override
    public Optional<User> findByUserName(String username) {
        return  userRepository.getDetailsByUsername(username);
    }

}