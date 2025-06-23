package com.example.demo.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoResponseDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;

    // // カレンダー表示拡張用
    // private LocalDateTime startDateTime;
    // private LocalDateTime endDateTime;
    // private boolean allDay;
}
