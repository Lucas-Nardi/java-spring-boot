package com.spring_boot.spring_jpa.errors;

public class UserErrorException extends RuntimeException{

    public UserErrorException(String message) {
        super(message);
    }
}
