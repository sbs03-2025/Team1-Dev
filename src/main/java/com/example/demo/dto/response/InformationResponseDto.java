package com.example.demo.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InformationResponseDto {
	private String name;
	private LocalDateTime joinedAt;
	private String department;
	private String hobby;
	private String intro;
}