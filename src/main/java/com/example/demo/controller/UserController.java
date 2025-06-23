package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.common.UserDto;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")                    // フロントエンド（localhost:5173）からのリクエストを許可
public class UserController {

    private final UserService userService;                         // ユーザー関連のビジネスロジックを処理するサービス

    // 全ユーザー取得（管理者用）
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());       // すべてのユーザーDTOを取得して200 OKで返す
    }

    // ユーザー詳細取得（ID指定）
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)                         // 指定IDのユーザーを取得
                .map(ResponseEntity::ok)                           // 存在すれば200 OKで返す
                .orElse(ResponseEntity.notFound().build());        // 存在しなければ404 Not Foundを返す
    }

    // ユーザー情報更新（ID指定）
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,                                 // 更新対象ユーザーのID
            @RequestBody UserDto dto) {                            // 更新するユーザー情報（DTO）

        return userService.updateUser(id, dto)                     // 指定IDのユーザー情報を更新
                .map(ResponseEntity::ok)                           // 成功時は200 OKと更新後のDTOを返す
                .orElse(ResponseEntity.notFound().build());        // ユーザーが存在しない場合は404を返す
    }

    // ログイン中のユーザー取得
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED) // 未ログイン状態なら401 Unauthorized
                                 .body("未ログインです");
        }

        String email = authentication.getName();                  // ログインユーザーのメールアドレスを取得

        Optional<UserDto> userOpt = userService.getUserByEmail(email);  // メールアドレスからユーザー情報を取得

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());              // ユーザーが存在すれば200 OKで返す
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND) // 見つからなければ404 Not Found
                                 .body("ユーザーが見つかりません");
        }
    }
}