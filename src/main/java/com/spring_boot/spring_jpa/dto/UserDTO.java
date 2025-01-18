package com.spring_boot.spring_jpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @Pattern(regexp = "^([a-zA-Z]{3,}\\s{0,})+$", message = "Name can only have letters and spaces")
    @Size(max = 100,min = 3, message = "Name must be greater than 3 and less than 100 characters")
    private String name;

    @Email(message = "Invalid email")
    @Size(max = 100,min = 3, message = "Email must be greater than 3 and less than 100 characters")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
