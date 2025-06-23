package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.NoticeRequestDto;
import com.example.demo.dto.response.NoticeResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class NoticeController {

    private final NoticeService noticeService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<NoticeResponseDto>> getAll() {
        return ResponseEntity.ok(noticeService.getAllNotices());
    }

    @PostMapping
    public ResponseEntity<NoticeResponseDto> create(
            @RequestBody NoticeRequestDto dto,
            Authentication authentication) {

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

        return ResponseEntity.ok(noticeService.createNotice(dto, user));
    }
}
