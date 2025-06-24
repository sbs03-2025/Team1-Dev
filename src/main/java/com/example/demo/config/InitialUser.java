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
			
			Department newDepSales = departmentRepository.findByName("営業部").orElseGet(() -> {
				Department dep = new Department();
				dep.setName("営業部");
				return departmentRepository.save(dep);
			});
			
			Department newDepIt = departmentRepository.findByName("IT部").orElseGet(() -> {
				Department dep = new Department();
				dep.setName("IT部");
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
			
			User testUser3 = userRepository.findByName("taro").orElseGet(() -> {
				User user = new User();
				user.setName("taro");
				user.setEmail("taro@mail.com");
				user.setPasswordHash(encoder.encode("taro"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepSales));
				user.setHobby("ゲーム");
				user.setBio("こんにちは");
				user.setJoinedAt(LocalDateTime.of(2023,4,1,0,0));
				System.out.println("taroを登録しました。");
				return userRepository.save(user);
			});
			
			User testUser4 = userRepository.findByName("jiro").orElseGet(() -> {
				User user = new User();
				user.setName("jiro");
				user.setEmail("jiro@mail.com");
				user.setPasswordHash(encoder.encode("jiro"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepIt));
				user.setHobby("散歩");
				user.setBio("Hora!");
				user.setJoinedAt(LocalDateTime.of(2023,4,1,0,0));
				System.out.println("jiroを登録しました。");
				return userRepository.save(user);
			});
			
			List<User> userList12 = new ArrayList<>();
			userList12.add(testUser1);
			userList12.add(testUser2);
			
			List<User> userList3 = new ArrayList<>();
			userList3.add(testUser3);
			
			List<User> userList24 = new ArrayList<>();
			userList12.add(testUser2);
			userList12.add(testUser4);
			
			if(scheduleRepository.findByTitle("test").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("test1");
				schedule.setDescription("description");
				schedule.setStartDateTime(LocalDateTime.of(2025,4,1,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,4,1,0,0));
				schedule.setParticipants(userList12);
				schedule.setCreatedUser(testUser1);
				
				scheduleRepository.save(schedule);
			}
			
			if(scheduleRepository.findByTitle("test").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("test2");
				schedule.setDescription("description");
				schedule.setStartDateTime(LocalDateTime.of(2025,5,1,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,5,1,0,0));
				schedule.setParticipants(userList3);
				schedule.setCreatedUser(testUser3);
				
				scheduleRepository.save(schedule);
			}
			
			if(scheduleRepository.findByTitle("test").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("test3");
				schedule.setDescription("description");
				schedule.setStartDateTime(LocalDateTime.of(2025,5,1,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,5,1,0,0));
				schedule.setParticipants(userList24);
				schedule.setCreatedUser(testUser4);
				
				scheduleRepository.save(schedule);
			}
		};
	}
}
