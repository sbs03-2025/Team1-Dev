package com.example.demo.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.demo.dto.request.TodoRequestDto;
import com.example.demo.dto.response.TodoResponseDto;
import com.example.demo.entity.Todo;
import com.example.demo.entity.User;

@Component
public class TodoMapper {

    /**
     * TodoRequestDtoとUser情報からTodoエンティティを生成する
     * 
     * @param dto クライアントからのTodo作成リクエストDTO
     * @param user Todoの所有者となるユーザーエンティティ
     * @return 新規作成されたTodoエンティティ
     */
	public Todo toEntity(TodoRequestDto dto, User user) {
	    return Todo.builder()
	            .title(dto.getTitle())                        // タイトルを設定
	            .description(dto.getDescription())            // 説明を設定
	            .dueDate(dto.getDueDate())                    // 期限を設定
	            .createdAt(LocalDateTime.now())               // 現在時刻を作成日時として設定
	            .completed(false)                             // 初期状態は未完了に設定
	            .user(user)                                   // 所有者のユーザーを設定
	            .build();                                     // Todoエンティティを構築
	}


    /**
     * TodoエンティティをTodoResponseDtoに変換する
     * 
     * @param todo Todoエンティティ
     * @return クライアントに返すためのTodoレスポンスDTO
     */
	public TodoResponseDto toDto(Todo todo) {
	    return TodoResponseDto.builder()
	            .id(todo.getId())                         // TodoのIDを設定
	            .title(todo.getTitle())                   // タイトルを設定
	            .description(todo.getDescription())       // 説明を設定
	            .completed(todo.isCompleted())            // 完了状態を設定
	            .dueDate(todo.getDueDate())               // 期限を設定
	            .createdAt(todo.getCreatedAt())           // 作成日時を設定
	            .build();                                 // DTOを構築
	}

}
