package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.common.UserDto;
import com.example.demo.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .passwordHash(dto.getPassword())
                .role(dto.getRole())
//                .department(dto.getDepartment())
//                .bio(dto.getBio())
//                .imageUrl(dto.getImageUrl())
//                .status(dto.getStatus())
                .build();
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(null); // パスワードは返さないように制御
        dto.setRole(user.getRole());
//        dto.setDepartment(user.getDepartment());
//        dto.setBio(user.getBio());
//        dto.setImageUrl(user.getImageUrl());
//        dto.setStatus(user.getStatus());
        return dto;
    }

    public void updateEntity(User user, UserDto dto) {
        user.setName(dto.getName());
//        user.setDepartment(dto.getDepartment());
//        user.setBio(dto.getBio());
//        user.setImageUrl(dto.getImageUrl());
//        user.setStatus(dto.getStatus());
        // email, role, password は原則更新しない
    }
}

