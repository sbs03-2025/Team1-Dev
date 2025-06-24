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
                .joinedAt(dto.getJoinedAt())
                .myDepartment(dto.getDepartment())
                .hobby(dto.getHobby())
                .bio(dto.getBio())
                
//                .imageUrl(dto.getImageUrl())
//                .status(dto.getStatus())
                .build();
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(null); // パスワードは返さないように制御
        dto.setRole(user.getRole());
        dto.setJoinedAt(user.getJoinedAt());
        dto.setDepartment(user.getMyDepartment());
        dto.setHobby(user.getHobby());
        dto.setBio(user.getBio());

//        dto.setImageUrl(user.getImageUrl());
//        dto.setStatus(user.getStatus());
        return dto;
    }

    public void updateEntity(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setJoinedAt(dto.getJoinedAt());
        user.setMyDepartment(dto.getDepartment());
        user.setHobby(dto.getHobby());
        user.setBio(user.getBio());
        
//        user.setImageUrl(dto.getImageUrl());
//        user.setStatus(dto.getStatus());
        // email, role, password は原則更新しない
    }
}

