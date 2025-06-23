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

	private final NoticeRepository noticeRepository;
	private final NoticeMapper noticeMapper;

	// 全件取得
	public List<NoticeResponseDto> getAllNotices() {
		return noticeRepository.findAll().stream()
				.map(noticeMapper::toDto)
				.toList();
	}

	// お知らせ作成(CREATE)
	public NoticeResponseDto createNotice(NoticeRequestDto dto, User createdUser) {
		Notice notice = noticeMapper.toEntity(dto, createdUser);
		return noticeMapper.toDto(noticeRepository.save(notice));
	}
}
