package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.request.InformationRequestDto;
import com.example.demo.dto.response.InformationResponseDto;
import com.example.demo.entity.User;

@Component
public class InformationMapper {

    public InformationResponseDto toDto(User user) {
        InformationResponseDto dto = new InformationResponseDto();
        dto.setName(user.getName());
        dto.setJoinedAt(user.getJoinedAt());
        dto.setDepartment(user.getMyDepartment());
        dto.setHobby(user.getHobby());
        dto.setBio(user.getBio());
        
//      dto.setImageUrl(user.getImageUrl());
//      dto.setStatus(user.getStatus());
        
        return dto;
        
        
    }

    public void updateUser(User user, InformationRequestDto dto) {
        user.setName(dto.getName());
        user.setJoinedAt(dto.getJoinedAt());
        user.setMyDepartment(dto.getDepartment());
        user.setHobby(dto.getHobby());
        user.setBio(dto.getBio());
        
//      user.setImageUrl(dto.getImageUrl());
//      user.setStatus(dto.getStatus());

    }
}
