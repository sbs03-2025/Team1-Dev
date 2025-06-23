package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 追加の検索が必要な場合はここに書く（例：ユーザーIDで検索など）
	
	// 特定ユーザーが参加している予定一覧
    List<Schedule> findByParticipants_Id(Long userId);

    // 特定ユーザーが作成した予定一覧
    List<Schedule> findByCreatedUser_Id(Long userId);
}

