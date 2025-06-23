package com.example.demo.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeResponseDto {
	private String title;
	private String body;
	private LocalDateTime createdAt;
	private Long createdUserId;
}