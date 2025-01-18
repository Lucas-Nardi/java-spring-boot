package com.spring_boot.spring_jpa.services;

import com.spring_boot.spring_jpa.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User createUser(User user);

    UserDetails findUserByEmail(String name);

    User findUserById(Long id);

    List<User> getMany();

    void deleteUser(Long id);
}
