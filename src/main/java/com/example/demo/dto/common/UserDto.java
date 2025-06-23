package com.example.demo.dto.common;

import java.time.LocalDateTime;

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
    
    private LocalDateTime joinedAt;
    
    private String department;// 所属部署（例：開発部、総務部など）
    
    private String hobby;
    
    private String intro;
    
    //    private String bio;
	//    
	//    private String imageUrl;
	//    
	//    private String status;
}
