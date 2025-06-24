package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.common.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	// 全件取得
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream()
				.map(userMapper::toDto)
				.toList();
	}
	
	// idとnameだけを取得
	public Map<Long, String> getIdAndName(){
		Map<Long, String> responseData = new HashMap<>();
		List<User> users = userRepository.findAll();
		
		for(User user : users) {
			responseData.put(user.getId(),user.getName());
		}
		
		return responseData;
	}

	// idからユーザー検索
	public Optional<UserDto> getUserById(Long id) {
		return userRepository.findById(id)
				.map(userMapper::toDto);
	}

	// 更新処理(UPDATE)
	public Optional<UserDto> updateUser(Long id, UserDto dto) {
		return userRepository.findById(id).map(user -> {
			userMapper.updateEntity(user, dto);
			return userMapper.toDto(userRepository.save(user));
		});
	}

	// Emailからユーザー検索
	public Optional<UserDto> getUserByEmail(String email) {
		return userRepository.findByEmail(email)
				.map(userMapper::toDto);
	}

}
