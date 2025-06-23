package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.request.ScheduleRequestDto;
import com.example.demo.dto.response.ScheduleResponseDto;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.User;

@Component
public class ScheduleMapper {

    public Schedule toEntity(ScheduleRequestDto dto, List<User> participants, User createdUser) {
        return Schedule.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDateTime(dto.getStartDateTime())
                .endDateTime(dto.getEndDateTime())
                .participants(participants)
                .createdUser(createdUser)
                .build();
    }

    public void updateEntity(Schedule schedule, ScheduleRequestDto dto, List<User> participants) {
        schedule.setTitle(dto.getTitle());
        schedule.setDescription(dto.getDescription());
        schedule.setStartDateTime(dto.getStartDateTime());
        schedule.setEndDateTime(dto.getEndDateTime());
        schedule.setParticipants(participants);
    }

    public ScheduleResponseDto toDto(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .startDateTime(schedule.getStartDateTime())
                .endDateTime(schedule.getEndDateTime())
                .body(schedule.getDescription())
                .createdUserId(schedule.getCreatedUser() != null
                        ? schedule.getCreatedUser().getId().intValue()
                        : -1)
                .build();
    }
}
