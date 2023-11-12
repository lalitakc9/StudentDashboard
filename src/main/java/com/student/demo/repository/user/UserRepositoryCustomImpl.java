package com.student.demo.repository.user;

import com.student.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional(readOnly = true)
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Optional<User> getDetailsByUsername(String username){
        Query query = entityManager.createNativeQuery("SELECT u.user_id,u.username,u.password,u.first_name," +
                        "u.last_name,u.phone_num,u.status,u.last_login,u.no_failed_logins from users as u where lower(u.username) = :username",User.class)
                .setParameter("username",username);
        User user = (User) query.getSingleResult();
        return Optional.of(user);
    }
}
