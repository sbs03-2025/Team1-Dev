package com.example.demo.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Department;

import lombok.Getter;

@Getter
public class InformationRequestDto {
	private String name;
	private LocalDate joinedAt; //入社年月日は更新（変更）することが無いので削除.
	private List<Department> myDepartment;
	private String hobby;
	private String bio;
	
	 // --- プロフィール拡張項目（将来使用予定） ---
    // private String imageUrl;    // プロフィール画像のURLまたはパス
    // private String status;      // 現在のステータス（在席中、外出中など）
}
