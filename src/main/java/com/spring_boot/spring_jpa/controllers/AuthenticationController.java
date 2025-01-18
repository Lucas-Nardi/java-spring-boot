package com.spring_boot.spring_jpa.controllers;

import com.spring_boot.spring_jpa.domain.User;
import com.spring_boot.spring_jpa.dto.AuthDTO;
import com.spring_boot.spring_jpa.dto.LoginResponseDTO;
import com.spring_boot.spring_jpa.infra.security.JwtService;
import com.spring_boot.spring_jpa.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO authdto) {
        Optional<User> user = userRepository.authenticateUser(authdto.email());
        if (user == null || user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("Invalid email or password"));
        }
        boolean samePassword = new BCryptPasswordEncoder(12).matches(authdto.password(), user.get().getPassword());
        if (!samePassword) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("Invalid email or password"));
        }
        String token = jwtService.generatedToken(user.get());
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    }
}
