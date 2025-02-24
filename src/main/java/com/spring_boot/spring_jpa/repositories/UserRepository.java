package com.spring_boot.spring_jpa.repositories;

import com.spring_boot.spring_jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findUserByEmail(String email);

    @Query(value = "SELECT u.* FROM USERS u where u.email = :email", nativeQuery = true)
    Optional<User> authenticateUser(@Param("email") String email);

}
