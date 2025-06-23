package com.example.demo.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponseDto {
	private Long id;
	private String title;
	private LocalDateTime endDateTime;
	private LocalDateTime startDateTime;
	private String body;
	private int createdUserId; // 作成者id
}