package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        String email = authentication.getName();                              // 認証情報からユーザーのメールアドレスを取得
        User user = userRepository.findByEmail(email)                         
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));  // ユーザーが見つからなければ例外を投げる
        return ResponseEntity.ok(informationService.getInfo(user));           // ユーザーの情報を取得して200 OKで返す
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getInfoById(@PathVariable Long id){
    	User user = userRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("user not found"));
    	return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<?> updateInfo(
            Authentication authentication,                   // ログインユーザーの認証情報
            @RequestBody InformationRequestDto dto) {         // 更新内容を含むリクエストDTO

        String email = authentication.getName();             // 認証情報からメールアドレスを取得
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));  // ユーザーが存在しなければ例外を投げる

        return ResponseEntity.ok(informationService.updateInfo(user, dto));  // ユーザー情報を更新し、結果を返す
    }

    
}
