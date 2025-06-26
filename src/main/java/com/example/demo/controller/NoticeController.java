package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.NoticeRequestDto;
import com.example.demo.dto.response.NoticeResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")  // フロントエンド（ポート5173）とのクロスオリジン通信を許可
public class NoticeController {

    private final NoticeService noticeService;                // お知らせ関連のビジネスロジックを処理
    private final UserRepository userRepository;              // ユーザー情報へのアクセス用
    private final NoticeRepository noticeRepository;          // お知らせ情報へのアクセス用

    @GetMapping
    public ResponseEntity<List<NoticeResponseDto>> getAll() {
        return ResponseEntity.ok(noticeService.getAllNotices());  // 全お知らせを取得して返す（200 OK）
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoticeById(@PathVariable Long id){
    	return noticeService.getNoticeById(id)
    			.map(ResponseEntity::ok)
    			.orElse(ResponseEntity.notFound().build());
    	
    }
    
    @GetMapping("/createdUser/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
    	return userRepository.findNameById(id)
    			.map(ResponseEntity::ok)
    			.orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<NoticeResponseDto> create(
            @RequestBody NoticeRequestDto dto,               // お知らせ作成リクエストDTO
            Authentication authentication) {

        String email = authentication.getName();             // 認証されたユーザーのメールアドレスを取得
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));  // ユーザーが存在しなければ例外

        return ResponseEntity.ok(noticeService.createNotice(dto, user));  // お知らせを作成し、結果を返す（200 OK）
    }
    

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotices(@PathVariable Long id){       // 指定IDのお知らせを削除
    	if (!noticeRepository.existsById(id)) {                    // 指定されたIDのお知らせが存在しない場合
    	    return ResponseEntity.notFound().build();              // 404 Not Found を返す
    	}
    	noticeRepository.deleteById(id);                           // 存在する場合は削除を実行
    	return ResponseEntity.ok("削除しました。");                 // 削除完了メッセージとともに200 OK を返す
    }
    
}

