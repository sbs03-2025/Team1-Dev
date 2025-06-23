package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.ScheduleRequestDto;
import com.example.demo.dto.response.ScheduleResponseDto;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.User;
import com.example.demo.mapper.ScheduleMapper;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;
	private final ScheduleMapper scheduleMapper;

	// 全件取得
	public List<ScheduleResponseDto> getSchedules(Optional<Long> userId) {
		List<Schedule> schedules = userId
				.map(id -> scheduleRepository.findByParticipants_Id(id))
				.orElseGet(scheduleRepository::findAll);

		return schedules.stream()
				.map(scheduleMapper::toDto)
				.toList();
	}

	// idからスケジュール検索
	public Optional<ScheduleResponseDto> getScheduleById(Long id) {
		return scheduleRepository.findById(id)
				.map(scheduleMapper::toDto);
	}

	// スケジュール作成(CREATE)
	public ScheduleResponseDto createSchedule(ScheduleRequestDto dto, User createdUser) {
		List<User> participants = userRepository.findAllById(dto.getParticipantIds());
		Schedule schedule = scheduleMapper.toEntity(dto, participants, createdUser);
		return scheduleMapper.toDto(scheduleRepository.save(schedule));
	}

	// スケジュール更新(UPDATE)
	public Optional<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto dto) {
		return scheduleRepository.findById(id).map(schedule -> {
			List<User> participants = userRepository.findAllById(dto.getParticipantIds());
			scheduleMapper.updateEntity(schedule, dto, participants);
			return scheduleMapper.toDto(scheduleRepository.save(schedule));
		});
	}

	// 削除(DELETE)
	public boolean deleteSchedule(Long id) {
		if (!scheduleRepository.existsById(id))
			return false;
		scheduleRepository.deleteById(id);
		return true;
	}
}
