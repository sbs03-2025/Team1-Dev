package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.ScheduleRequestDto;
import com.example.demo.dto.response.ScheduleResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserRepository userRepository;

    // スケジュール一覧取得
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(
            @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(scheduleService.getSchedules(Optional.ofNullable(userId)));
    }
    
    
    // スケジュール取得(id)
    @GetMapping("/{id}")
    public ResponseEntity<?> getSchedule(@PathVariable Long id) {
        return scheduleService.getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // スケジュール登録	
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @RequestBody ScheduleRequestDto dto,
            Authentication authentication) {

        String name = authentication.getName();
        User createdUser = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

        return ResponseEntity.ok(scheduleService.createSchedule(dto, createdUser));
    }
    
    
    // スケジュール更新
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto) {
        return scheduleService.updateSchedule(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    // スケジュール削除
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        boolean deleted = scheduleService.deleteSchedule(id);
        return deleted 
    		    ? ResponseEntity.ok("削除しました")
				: ResponseEntity.notFound().build();
    }
}
