package com.example.demo.mapper;


import org.springframework.stereotype.Component;

import com.example.demo.dto.request.NoticeRequestDto;
import com.example.demo.dto.response.NoticeResponseDto;
import com.example.demo.entity.Notice;
import com.example.demo.entity.User;

@Component
public class NoticeMapper {

    public Notice toEntity(NoticeRequestDto dto, User createdUser) {
        return Notice.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .createdAt(dto.getCreatedAt())
                .createdUser(createdUser)
                .build();
    }

    public NoticeResponseDto toDto(Notice notice) {
        NoticeResponseDto dto = new NoticeResponseDto();
        dto.setId(notice.getId());
        dto.setTitle(notice.getTitle());
        dto.setBody(notice.getBody()); // typo修正要注意
        dto.setCreatedAt(notice.getCreatedAt());
        dto.setCreatedUserId(notice.getCreatedUser().getId());
        return dto;
    }
}

