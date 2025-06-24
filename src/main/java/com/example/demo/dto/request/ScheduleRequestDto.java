package com.example.demo.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ScheduleRequestDto {
	
	@NotBlank(message = "タイトルは必須です")
    private String title;

    private String description;
    
    @NotNull(message = "開始日時は必須です")
    private LocalDateTime startDateTime;

    @NotNull(message = "終了日時は必須です")
    private LocalDateTime endDateTime;

    // 参加者のユーザーIDリスト（Vueから送られる）
    private List<Long> participantIds;
}
