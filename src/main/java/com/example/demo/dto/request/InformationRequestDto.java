package com.example.demo.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class InformationRequestDto {
	private String name;
	private LocalDateTime joinedAt;
	private String department;
	private String hobby;
	private String bio;
	
	 // --- プロフィール拡張項目（将来使用予定） ---
    // private String imageUrl;    // プロフィール画像のURLまたはパス
    // private String status;      // 現在のステータス（在席中、外出中など）
}
