package com.example.demo.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TodoRequestDto {
    private String title;
    private String description;
    private LocalDateTime dueDate;

    // // カレンダー表示拡張用
    // private LocalDateTime startDateTime;
    // private LocalDateTime endDateTime;
    // private boolean allDay;
}
