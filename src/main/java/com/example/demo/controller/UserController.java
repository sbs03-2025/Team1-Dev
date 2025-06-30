package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.common.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")                    // フロントエンド（localhost:5173）からのリクエストを許可
public class UserController {
	
	@Autowired
	private final UserRepository userRepository;

    private final UserService userService;                         // ユーザー関連のビジネスロジックを処理するサービス

    @Autowired
    private final PasswordEncoder encoder;
    
    // 全ユーザー取得（管理者用）
    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());       // すべてのユーザーDTOを取得して200 OKで返す
    }
    
 // 全てのユーザーのID,nameを取得して200 OKで返す
    @GetMapping("/abstract")
    public ResponseEntity<Map<Long,String>> getIdAndName() {
        return ResponseEntity.ok(userService.getIdAndName());
    }
    
 // 全てのユーザーのID,name,myDepartmentを取得して200 OKで返す
    @GetMapping("/abstract/department")
    public ResponseEntity<List<Map<String,String>>> getIdAndNameAndDepartment() {
        return ResponseEntity.ok(userService.getIdAndNameAndDepartment());
    }
    
 // 全てのユーザーのID,name,myDepartment,deleteFlagを取得して200 OKで返す
    @GetMapping("/abstract/delete")
    public ResponseEntity<List<Map<String,String>>> getDeleteFlag(){
    	return ResponseEntity.ok(userService.getIdAndNameAndDepartmentAndDeleteFlag());
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

        String name = authentication.getName();                  // ログインユーザーのnameを取得

        Optional<UserDto> userOpt = userService.getUserByName(name);  // 上で取得したnameからユーザー情報を取得

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());              // ユーザーが存在すれば200 OKで返す
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND) // 見つからなければ404 Not Found
                                 .body("ユーザーが見つかりません");
        }
    }
    
    //ユーザーの新規登録
    @PostMapping("/save")
    public ResponseEntity<?> saveData(@RequestBody UserDto dto){
    	User user = new User();
    	user.setName(dto.getName());
    	user.setEmail(dto.getEmail());
    	user.setPasswordHash(encoder.encode(dto.getPassword()));
//    	user.setPasswordHash(encoder.encode(dto.getPassword()));
    	user.setRole("ROLE_USER");
    	user.setMyDepartment(dto.getMyDepartment());
    	user.setJoinedAt(dto.getJoinedAt());
    	user.setHobby(dto.getHobby());
    	user.setBio(dto.getBio());
    	user.setDeleteFlag(false);
    	return ResponseEntity.ok(userRepository.save(user));
    }
    
    // ユーザー削除.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteData(@PathVariable Long id){
    	User user = userRepository.findById(id)
    			.orElseThrow(() -> new UsernameNotFoundException("該当するユーザーがいません"));
    	
    	user.setDeleteFlag(true);
    	userRepository.save(user);
    	
    	return ResponseEntity.ok("ユーザーを削除しました。");
    }
    
    //パスワード更新時のチェック.
    //現在のパスワードと新しく更新しようとしているパスワードの比較.
    @PostMapping("/check/me")
    public ResponseEntity<?> validPassword(@RequestBody UserDto dto,Authentication authentication){
    	if(authentication == null || !authentication.isAuthenticated()) {
    		return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND)
    				.body("ログインしていません");
    	}
    	
    	String name = authentication.getName();
    	
    	User user = userRepository.findByName(name).orElseThrow(()->new UsernameNotFoundException("ユーザーが見つかりません"));
    	
    	String currentPasswordHash = user.getPasswordHash();
    	String newPassword = dto.getPassword();
    	
    	if(encoder.matches(newPassword, currentPasswordHash)) {
    		return ResponseEntity.status(HttpServletResponse.SC_CONFLICT)
    				.body("現在のパスワード一致しています");
    	}else {
    		return ResponseEntity.ok("パスワード登録可能");
    	}
    }
    
    //社員登録時の確認.
    //現在登録されている社員の名前とパスワードの組み合わせと同一のものが登録されないようにする.
    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestBody UserDto dto,Authentication authentication){
    	//ログインしている（登録の処理をしている）ユーザーのroleを確認
    	String role = userRepository.findRoleByName(authentication.getName())
    			.orElseThrow(() -> new UsernameNotFoundException("該当のユーザーが見つかりません"));
    	if(role == "ROLE_USER") {
    		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
    				.body("権限がありません");
    	}
    	
    	String newName = dto.getName();
    	String newPassword = dto.getPassword();
    	
    	//登録済みのuserの中に同じ名前が存在するかを確認.
    	Optional<User> checkUser = userRepository.findByName(newName);
    	
    	if(checkUser.isPresent()) {
    		//ここに処理が来ていたら、同じ名前のuserが存在している.
    		User existingUser = checkUser.get();//Optionalをアンラップ.
    		
    		//パスワードが一致していないかどうかを確認
    		if(encoder.matches(newPassword, existingUser.getPasswordHash())) {
    			return ResponseEntity.status(HttpServletResponse.SC_CONFLICT)
    					.body("名前とパスワードが同じユーザーが存在しています");
    		}
    	}
    	//同じnameのuserがいない or nameは一緒だけどパスワードが違う場合には以下を返す.
    	return ResponseEntity.ok("ユーザー登録が可能");
    	
    }
    
    @GetMapping("/myrole")
    public ResponseEntity<?> getRole(Authentication authentication){
    	String name = authentication.getName();
    	if(name == null) {
    		return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
    				.body("未ログインです");
    	}
    	User user = userRepository.findByName(name).orElseThrow(() -> new RuntimeException());
    	String role = user.getRole();
    	
    	return ResponseEntity.ok(role);
    }
    
}