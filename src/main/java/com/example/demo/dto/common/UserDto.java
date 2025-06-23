package com.example.demo.dto.common;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

import lombok.Data;

@Data
public class UserDto {

    private String name;

    @Column(unique = true)
    @Email
    private String email;

    private String password;// これはプレーンパスワード

    private String role;
    
    private String department;
    
    private String bio;
    
    private String imageUrl;
    
    private String status;
}
