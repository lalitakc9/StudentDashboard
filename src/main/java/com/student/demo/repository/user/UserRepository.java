package com.student.demo.repository.user;

import com.student.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom {

    @Query("SELECT u.userId,u.username,u.password,u.firstName,u.lastName,u.phoneNumber,u.status,u.lastLogin,u.noFailedLogins from User as u "+
            "WHERE lower(u.username) = :username")
    Optional<User> findByUsername(String username);

    Optional<User> getDetailsByUsername(String username);
}
