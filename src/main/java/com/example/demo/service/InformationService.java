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

	private final UserRepository userRepository;            // ユーザーデータの永続化処理を行うリポジトリ
	private final InformationMapper mapper;                 // UserエンティティとDTOの変換を行うマッパー

	// お知らせ取得
	public InformationResponseDto getInfo(User user) {
		return mapper.toDto(user);                          // ユーザー情報をレスポンスDTOに変換して返す
	}

	// お知らせ更新(UPDATE)
	public InformationResponseDto updateInfo(User user, InformationRequestDto dto) {
		mapper.updateUser(user, dto);                       // リクエストDTOの内容でUserエンティティを更新
		return mapper.toDto(userRepository.save(user));     // 更新されたUserをDBに保存し、DTOに変換して返す
	}
}
