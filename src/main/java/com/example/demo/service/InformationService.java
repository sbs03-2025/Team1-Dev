package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.InformationRequestDto;
import com.example.demo.dto.response.InformationResponseDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.InformationMapper;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InformationService {

	private final UserRepository userRepository;
	private final InformationMapper mapper;

	// お知らせ取得
	public InformationResponseDto getInfo(User user) {
		return mapper.toDto(user);
	}

	// お知らせ更新(UPDATE)
	public InformationResponseDto updateInfo(User user, InformationRequestDto dto) {
		mapper.updateUser(user, dto);
		return mapper.toDto(userRepository.save(user));
	}
}
