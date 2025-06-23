package com.example.demo.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class InformationRequestDto {
	private String name;
	private LocalDateTime joinedAt;
	private String department;
	private String hobby;
	private String intro;
}
