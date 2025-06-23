package com.example.demo.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ScheduleRequestDto {

    private String title;

    private String description;

    private LocalDateTime startDateTime;

    private LocalDateTime EndDateTime;

    // 参加者のユーザーIDリスト（Vueから送られる）
    private List<Long> participantIds;
}
