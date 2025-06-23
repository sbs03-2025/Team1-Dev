package com.example.demo.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeRequestDto {
	private String title;
	private String body;
	private LocalDateTime createdAt;
	private Long createdUserId;
}


