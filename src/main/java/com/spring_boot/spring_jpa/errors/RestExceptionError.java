package com.spring_boot.spring_jpa.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionError {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ResponseError<Map<String, String>> responseError = new ResponseError<Map<String, String>>(errors, "Your request has invalid fields");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(TokenErrorException.class)
    public ResponseEntity<ResponseError<String>> tokenException(TokenErrorException ex) {
        ResponseError<String> responseError = new ResponseError<String>(ex.getMessage(), "Your token is invalid");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseError);
    }

    @ExceptionHandler(UserErrorException.class)
    public ResponseEntity<ResponseError<String>> userException(UserErrorException ex) {
        ResponseError<String> responseError = new ResponseError<String>(ex.getMessage(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }
}
