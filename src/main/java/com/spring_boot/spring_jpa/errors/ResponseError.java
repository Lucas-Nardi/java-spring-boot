package com.spring_boot.spring_jpa.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseError <E>{
    private E error;
    private String message;
}
