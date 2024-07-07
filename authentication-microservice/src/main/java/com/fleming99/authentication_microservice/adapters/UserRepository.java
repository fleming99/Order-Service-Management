package com.fleming99.authentication_microservice.adapters;

import com.fleming99.authentication_microservice.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user_accounts WHERE user_email = ?1", nativeQuery = true)
    User findUserByEmail(String email);
}
