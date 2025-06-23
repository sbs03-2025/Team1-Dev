package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.NoticeRequestDto;
import com.example.demo.dto.response.NoticeResponseDto;
import com.example.demo.entity.Notice;
import com.example.demo.entity.User;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;         // お知らせエンティティへのDB操作を行うリポジトリ
	private final NoticeMapper noticeMapper;                 // NoticeとDTO間の変換を行うマッパー

	// 全件取得
	public List<NoticeResponseDto> getAllNotices() {
		return noticeRepository.findAll().stream()           // DBからすべてのお知らせを取得し、Stream化
				.map(noticeMapper::toDto)                    // 各エンティティをレスポンスDTOに変換
				.toList();                                   // DTOのリストに変換して返す
	}

	// お知らせ作成 (CREATE)
	public NoticeResponseDto createNotice(NoticeRequestDto dto, User createdUser) {
		Notice notice = noticeMapper.toEntity(dto, createdUser);      // リクエストDTOと作成者情報からNoticeエンティティを生成
		return noticeMapper.toDto(noticeRepository.save(notice));     // エンティティを保存し、保存結果をDTOに変換して返す
	}
}
