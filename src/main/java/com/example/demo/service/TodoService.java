package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.TodoRequestDto;
import com.example.demo.dto.response.TodoResponseDto;
import com.example.demo.entity.Todo;
import com.example.demo.entity.User;
import com.example.demo.mapper.TodoMapper;
import com.example.demo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper mapper;

    /**
     * 指定したユーザーのTodo一覧を取得する
     * 
     * @param user Todoを取得したいユーザー
     * @return TodoResponseDtoのリスト
     */
    public List<TodoResponseDto> getTodos(User user) {
        return todoRepository.findByUser_Id(user.getId()).stream()// ユーザーIDに紐づくTodo一覧をDBから取得
                .map(mapper::toDto)// TodoエンティティをTodoResponseDtoに変換
                .toList();// StreamをListに変換して返す
    }

    /**
     * 新しいTodoを作成する
     * 
     * @param dto Todo作成に必要なデータを持つDTO
     * @param user Todoの所有者となるユーザー
     * @return 作成されたTodoのDTO
     */
    public TodoResponseDto createTodo(TodoRequestDto dto, User user) {
        Todo todo = mapper.toEntity(dto, user);			// DTOからTodoエンティティを作成しユーザー情報をセット
        return mapper.toDto(todoRepository.save(todo));	// エンティティをDBに保存し、保存後のデータをDTOに変換して返す
    }

    /**
     * 指定IDのTodoを更新する
     * 
     * @param id 更新対象のTodoのID
     * @param dto 更新内容を持つDTO
     * @param user 操作ユーザー（必要に応じて権限チェックに使用可能）
     * @return 更新後のTodoのDTO（存在しない場合は空のOptional）
     */
    public Optional<TodoResponseDto> updateTodo(Long id, TodoRequestDto dto, User user) {
        return todoRepository.findById(id).map(todo -> {          // IDでTodoを検索し、存在すれば処理を実行
            todo.setTitle(dto.getTitle());                         // タイトルを更新
            todo.setDescription(dto.getDescription());             // 説明を更新
            todo.setDueDate(dto.getDueDate());                     // 期限を更新
            return mapper.toDto(todoRepository.save(todo));        // 更新したTodoをDBに保存し、DTOに変換して返す
        });
    }

    /**
     * 指定IDのTodoを削除する
     * 
     * @param id 削除対象のTodoのID
     * @return 削除が成功したらtrue、対象が存在しなければfalse
     */
    public boolean deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) return false;  // 指定IDのTodoが存在しなければfalseを返す
        todoRepository.deleteById(id);                      // 存在すればTodoを削除する
        return true;                                        // 削除成功をtrueで返す
    }

    /**
     * 指定IDのTodoの完了状態をトグル（切り替え）する
     * 
     * @param id 対象のTodoのID
     * @return 更新後のTodoのDTO（存在しない場合は空のOptional）
     */
    public Optional<TodoResponseDto> toggleCompleted(Long id) {
        return todoRepository.findById(id).map(todo -> {
            todo.setCompleted(!todo.isCompleted());// 完了状態を反転させる
            return mapper.toDto(todoRepository.save(todo));// 保存してDTOに変換して返す
        });
    }
}
