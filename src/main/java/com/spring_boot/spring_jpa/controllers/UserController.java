package com.spring_boot.spring_jpa.controllers;

import com.spring_boot.spring_jpa.domain.User;
import com.spring_boot.spring_jpa.dto.CreatedUserDTO;
import com.spring_boot.spring_jpa.dto.UserDTO;
import com.spring_boot.spring_jpa.mappers.Mapper;
import com.spring_boot.spring_jpa.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private Mapper<User, UserDTO> userMapper;

    public UserController(UserService userService, Mapper<User, UserDTO> userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CreatedUserDTO> createUser(@RequestBody @Valid UserDTO userDto) {
        User user = userMapper.mapFrom(userDto);
        user.setPassword(new BCryptPasswordEncoder(12).encode("123456"));
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreatedUserDTO(createdUser.getId()));
    }

    @GetMapping(path = "/get-one/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        UserDTO userDTO = userMapper.mapTo(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping(path = "/get-many")
    public ResponseEntity<List<UserDTO>> getMany() {
        List<User> users = userService.getMany();
        List<UserDTO> userDTO = userMapper.mapTo(users);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
