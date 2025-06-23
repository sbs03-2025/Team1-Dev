package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestDto {
	@NotBlank(message = "メールアドレスは必須です")
    @Email(message = "有効なメールアドレス形式ではありません")
    private String email;

    @NotBlank(message = "パスワードは必須です")
    private String password;
}
