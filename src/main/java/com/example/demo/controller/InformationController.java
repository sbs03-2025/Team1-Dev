package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.InformationRequestDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.InformationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class InformationController {

    private final UserRepository userRepository;
    private final InformationService informationService;

    @GetMapping("/me")
    public ResponseEntity<?> getInfo(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
        return ResponseEntity.ok(informationService.getInfo(user));
    }

    @PutMapping
    public ResponseEntity<?> updateInfo(
            Authentication authentication,
            @RequestBody InformationRequestDto dto) {

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

        return ResponseEntity.ok(informationService.updateInfo(user, dto));
    }
}
