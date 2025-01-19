package com.spring_boot.spring_jpa.services.impl;

import com.spring_boot.spring_jpa.domain.User;
import com.spring_boot.spring_jpa.errors.UserErrorException;
import com.spring_boot.spring_jpa.repositories.UserRepository;
import com.spring_boot.spring_jpa.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        UserDetails createdUser = userRepository.findUserByEmail(user.getEmail());
        if (createdUser != null) {
            throw new UserErrorException("An user already has this email");
        }
        return userRepository.save(user);
    }


    @Override
    public UserDetails findUserByEmail(String name) {
        UserDetails user = userRepository.findUserByEmail(name);
        if (user == null) {
            throw new UserErrorException("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserErrorException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getMany() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserErrorException("User not found");
        }
        userRepository.deleteById(id);
    }
}
