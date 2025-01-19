package com.spring_boot.spring_jpa.errors;

public class TokenErrorException extends RuntimeException{
    public TokenErrorException() {
        super("Token inválido");
    }
    public TokenErrorException(String message) {
        super(message);
    }
}
