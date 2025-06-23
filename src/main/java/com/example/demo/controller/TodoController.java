package com.example.demo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.TodoRequestDto;
import com.example.demo.dto.response.TodoResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {

    private final UserRepository userRepository;
    private final TodoService todoService;

    /**
     * 認証情報からユーザーを取得するユーティリティメソッド
     *
     * @param auth 認証情報
     * @return User エンティティ
     * @throws RuntimeException ユーザーが見つからなかった場合
     */
    private User getUser(Authentication auth) {
        return userRepository.findByEmail(auth.getName())            // 認証情報からメールアドレスを取得してユーザーを検索
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));  // 見つからなければ例外を投げる
    }

    /**
     * ログインユーザーのTodo一覧を取得するエンドポイント
     *
     * @param auth 認証情報
     * @return Todo一覧のレスポンス
     */
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos(Authentication auth) {
        return ResponseEntity.ok(todoService.getTodos(getUser(auth)));  // 認証ユーザーのTodo一覧を取得して200 OKで返す
    }

    /**
     * ログインユーザーのTodoを新規作成するエンドポイント
     *
     * @param dto 新規Todo情報のDTO
     * @param auth 認証情報
     * @return 作成したTodoのレスポンス
     */
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @RequestBody TodoRequestDto dto,                  // クライアントから送られたTodo作成リクエストを受け取る
            Authentication auth) {
        return ResponseEntity.ok(
            todoService.createTodo(dto, getUser(auth))      // 認証ユーザーのTodoを作成し、結果を200 OKで返す
        );
    }


    /**
     * 指定IDのTodoを更新するエンドポイント
     *
     * @param id 更新対象のTodo ID
     * @param dto 更新内容のDTO
     * @param auth 認証情報
     * @return 更新したTodoのレスポンス、対象がなければ404
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(
            @PathVariable Long id,                           // 更新対象のTodoのIDをURLから取得
            @RequestBody TodoRequestDto dto,                 // 更新内容をリクエストボディから取得
            Authentication auth) {
        return todoService.updateTodo(id, dto, getUser(auth))   // 指定ユーザーのTodoを更新
                .map(ResponseEntity::ok)                          // 更新成功なら200 OKで更新済みTodoを返す
                .orElse(ResponseEntity.notFound().build());       // 対象がなければ404 Not Foundを返す
    }


    /**
     * 指定IDのTodoを削除するエンドポイント
     *
     * @param id 削除対象のTodo ID
     * @return 削除成功メッセージ or 404
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {      // URLから削除対象のTodoのIDを取得
        return todoService.deleteTodo(id)                             
                ? ResponseEntity.ok("削除しました")                    // 削除成功なら200 OKとメッセージを返す
                : ResponseEntity.notFound().build();                  // 対象がなければ404 Not Foundを返す
    }


    /**
     * 指定IDのTodoの完了状態をトグルするエンドポイント
     *
     * @param id 対象TodoのID
     * @return 更新したTodoのレスポンス、対象がなければ404
     */
    @PutMapping("/{id}/done")
    public ResponseEntity<?> toggleCompleted(@PathVariable Long id) {
        return todoService.toggleCompleted(id)
                .map(ResponseEntity::ok)// トグル成功 → 200 OK + 更新後のTodoを返す
                .orElse(ResponseEntity.notFound().build());// 対象Todoがなければ 404 Not Found
    }
}