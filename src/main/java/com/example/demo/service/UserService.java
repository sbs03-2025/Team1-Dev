package com.example.demo.service;

import java.util.ArrayList;
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
	public List<Map<String, String>> getAllUsers() {
		List<Map<String, String>> responseList = new ArrayList<>();
		List<User> users = userRepository.findAll();
		
		for (User user: users) {
			Map<String, String> userData = new HashMap<>();
			userData.put("id", Long.toString(user.getId()));
			userData.put("email", user.getEmail());
			userData.put("role", user.getRole());
			if (user.getMyDepartment() != null && !user.getMyDepartment().isEmpty()) {
				userData.put("myDepartment", user.getMyDepartment().get(0).getName());
			} else {
				userData.put("myDepartment", "なし");
			}
			if(user.getSchedules() != null && !user.getSchedules().isEmpty()) {
				for(int i = 0; i < user.getSchedules().size(); i++) {
					userData.put("Schedule" + "[" + i + "]", user.getSchedules().get(i).getTitle());
				}
			}
			else {
				userData.put("Schedule", "なし");
			}
			userData.put("deleteFlag", String.valueOf(user.isDeleteFlag()));
			responseList.add(userData);
		}
		return responseList;
	}

	// idとnameだけを取得
	public Map<Long, String> getIdAndName() {
		Map<Long, String> responseData = new HashMap<>();
		List<User> users = userRepository.findAll();

		for (User user : users) {
			responseData.put(user.getId(), user.getName());
		}

		return responseData;
	}

	// id,name, departmentを取得
	public List<Map<String, String>> getIdAndNameAndDepartment() {
		List<Map<String, String>> responseList = new ArrayList<>();
		List<User> users = userRepository.findAll();

		for (User user : users) {
			Map<String, String> userData = new HashMap<>();
			userData.put("id", Long.toString(user.getId()));
			userData.put("name", user.getName());

			// 複数部門がある場合、1つ目だけ取得（例外回避も一応入れておく）
			if (user.getMyDepartment() != null && !user.getMyDepartment().isEmpty()) {
				userData.put("myDepartment", user.getMyDepartment().get(0).getName());
			} else {
				userData.put("myDepartment", "なし");
			}

			responseList.add(userData);
		}

		return responseList;
	}

	// id,name,myDepartment,deleteFlagを取得
	public List<Map<String, String>> getIdAndNameAndDepartmentAndDeleteFlag() {
		List<Map<String, String>> responseList = new ArrayList<>();
		List<User> users = userRepository.findAll();

		for (User user : users) {
			Map<String, String> userData = new HashMap<>();
			userData.put("id", Long.toString(user.getId()));
			userData.put("name", user.getName());

			// 複数部門がある場合、1つ目だけ取得（例外回避も一応入れておく）
			if (user.getMyDepartment() != null && !user.getMyDepartment().isEmpty()) {
				userData.put("myDepartment", user.getMyDepartment().get(0).getName());
			} else {
				userData.put("myDepartment", "なし");
			}
			
			userData.put("deleteFlag", String.valueOf(user.isDeleteFlag()));

			responseList.add(userData);
		}

		return responseList;
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

	// idからName検索
	public Optional<UserDto> findNameById(Long id) {
		return userRepository.findNameById(id)
				.map(userMapper::toDto);
	}

}
