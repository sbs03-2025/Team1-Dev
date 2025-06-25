package com.example.demo.dto.common;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

import com.example.demo.entity.Department;
import com.example.demo.entity.Schedule;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;

    private String name;

    @Column(unique = true)
    @Email
    private String email;

    private String password;// これはプレーンパスワード

    private String role;
    
    private LocalDateTime joinedAt;
    
    private List<Department> department;// 所属部署（例：開発部、総務部など）
    
    private String hobby;
    
    private String bio;
    
    private List<Schedule> schedules;
    
    // --- プロフィール拡張項目（将来使用予定） ---
    
	//    private String imageUrl;
	//    
	//    private String status;
}
