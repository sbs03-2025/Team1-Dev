package com.example.demo.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Department;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class InitialUser {
	//DB "users" の初期設定.InitUserとInitAdminUserを作成.
	@Bean
	public CommandLineRunner initUser(UserRepository userRepository, DepartmentRepository departmentRepository, ScheduleRepository scheduleRepository, PasswordEncoder encoder) {
		return args -> {
			System.out.println("ユーザー初期設定");
			
			Department newDepHumanResrc = departmentRepository.findByName("人事部").orElseGet(() -> {
				Department dep = new Department();
				dep.setName("人事部");
				return departmentRepository.save(dep);
			});
			
			Department newDepAccount = departmentRepository.findByName("経理部").orElseGet(() -> {
				Department dep = new Department();
				dep.setName("経理部");
				return departmentRepository.save(dep);
			});
			
			User testUser1 = userRepository.findByName("InitUser").orElseGet(() -> {
				User user = new User();
				user.setName("InitUser");
				user.setEmail("init@email.com");
				user.setPasswordHash(encoder.encode("password"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepHumanResrc));
				user.setHobby("読書");
				user.setBio("よろしく");
				user.setJoinedAt(LocalDateTime.now());
				System.out.println("InitUserを登録しました。");
				return userRepository.save(user);
			});
			User testUser2 = userRepository.findByName("InitAdminUser").orElseGet(() -> {
				User user = new User();
				user.setName("InitAdminUser");
				user.setEmail("initadmin@email.com");
				user.setPasswordHash(encoder.encode("adminpassword"));
				user.setRole("ROLE_ADMIN");
				user.setMyDepartment(Collections.singletonList(newDepAccount));
				user.setHobby("食べ歩き");
				user.setBio("Hello World!");
				user.setJoinedAt(LocalDateTime.of(2023,4,1,0,0));
				System.out.println("InitAdminUserを登録しました。");
				return userRepository.save(user);
			});
			
			List<User> userList = new ArrayList<>();
			userList.add(testUser1);
			userList.add(testUser2);
			
			if(scheduleRepository.findByTitle("test").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("test");
				schedule.setDescription("description");
				schedule.setStartDateTime(LocalDateTime.of(2025,4,1,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,4,1,0,0));
				schedule.setParticipants(userList);
				schedule.setCreatedUser(testUser1);
				
				scheduleRepository.save(schedule);
			}
		};
	}
}
