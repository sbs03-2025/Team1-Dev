package com.example.demo.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Department;
import com.example.demo.entity.Notice;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.repository.ScheduleRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class InitialUser {
	//DB "users" の初期設定.InitUserとInitAdminUserを作成.
	@Bean
	public CommandLineRunner initUser(
			UserRepository userRepository, 
			DepartmentRepository departmentRepository, 
			ScheduleRepository scheduleRepository, 
			NoticeRepository noticeRepository, 
			PasswordEncoder encoder) {
		return args -> {
			System.out.println("ユーザー初期設定");
			
			// 部署の初期設定.
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
			
			// ユーザーの初期設定.
			User testUser1 = userRepository.findByName("InitUser").orElseGet(() -> {
				User user = new User();
				user.setName("InitUser");
				user.setEmail("init@email.com");
				user.setPasswordHash(encoder.encode("password"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepHumanResrc));
				user.setHobby("読書");
				user.setBio("よろしく");
				user.setJoinedAt(LocalDate.now());
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
				user.setJoinedAt(LocalDate.of(2023,4,1));
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
				user.setJoinedAt(LocalDate.of(2023,4,1));
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
				user.setBio("Hola!");
				user.setJoinedAt(LocalDate.of(2023,4,1));
				System.out.println("jiroを登録しました。");
				return userRepository.save(user);
			});
			
			User ishihara = userRepository.findByName("ishihara").orElseGet(() -> {
				User user = new User();
				user.setName("ishihara");
				user.setEmail("ishihara@mail.com");
				user.setPasswordHash(encoder.encode("ishihara"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepIt));
				user.setHobby("散歩");
				user.setBio("你好");
				user.setJoinedAt(LocalDate.of(2025,4,1));
				System.out.println("ishiharaを登録しました。");
				return userRepository.save(user);
			});
			
			User furuta = userRepository.findByName("furuta").orElseGet(() -> {
				User user = new User();
				user.setName("furuta");
				user.setEmail("furuta@mail.com");
				user.setPasswordHash(encoder.encode("furuta"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepIt));
				user.setHobby("食べ歩き");
				user.setBio("Hello!");
				user.setJoinedAt(LocalDate.of(2025,4,1));
				System.out.println("furutaを登録しました。");
				return userRepository.save(user);
			});
			
			User takiguchi = userRepository.findByName("takiguchi").orElseGet(() -> {
				User user = new User();
				user.setName("takiguchi");
				user.setEmail("takiguchi@mail.com");
				user.setPasswordHash(encoder.encode("takiguchi"));
				user.setRole("ROLE_ADMIN");
				user.setMyDepartment(Collections.singletonList(newDepIt));
				user.setHobby("読書");
				user.setBio("こんにちは");
				user.setJoinedAt(LocalDate.of(2025,4,1));
				System.out.println("takiguchiを登録しました。");
				return userRepository.save(user);
			});
			
			User arai = userRepository.findByName("arai").orElseGet(() -> {
				User user = new User();
				user.setName("arai");
				user.setEmail("arai@mail.com");
				user.setPasswordHash(encoder.encode("arai"));
				user.setRole("ROLE_ADMIN");
				user.setMyDepartment(Collections.singletonList(newDepIt));
				user.setHobby("散歩");
				user.setBio("こんにちは");
				user.setJoinedAt(LocalDate.of(2025,4,1));
				System.out.println("araiを登録しました。");
				return userRepository.save(user);
			});
			
			User nguyen = userRepository.findByName("nguyen").orElseGet(() -> {
				User user = new User();
				user.setName("nguyen");
				user.setEmail("nguyen@mail.com");
				user.setPasswordHash(encoder.encode("nguyen"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepIt));
				user.setHobby("読書");
				user.setBio("Hello!");
				user.setJoinedAt(LocalDate.of(2025,4,1));
				System.out.println("nguyenを登録しました。");
				return userRepository.save(user);
			});
			
			User wang = userRepository.findByName("wang").orElseGet(() -> {
				User user = new User();
				user.setName("wang");
				user.setEmail("wang@mail.com");
				user.setPasswordHash(encoder.encode("wang"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepIt));
				user.setHobby("読書");
				user.setBio("Hello!");
				user.setJoinedAt(LocalDate.of(2025,4,1));
				System.out.println("wangを登録しました。");
				return userRepository.save(user);
			});
			
			// ユーザーのグループ.
			List<User> userList12 = new ArrayList<>();
			userList12.add(testUser1);
			userList12.add(testUser2);
			
			List<User> userList3 = new ArrayList<>();
			userList3.add(testUser3);
			
			List<User> userList24 = new ArrayList<>();
			userList12.add(testUser2);
			userList12.add(testUser4);
			
			List<User> userListAll = new ArrayList<>();
			userListAll.add(ishihara);
			userListAll.add(furuta);
			userListAll.add(takiguchi);
			userListAll.add(arai);
			userListAll.add(nguyen);
			userListAll.add(wang);
			
			// 予定の初期設定.
			if(scheduleRepository.findByTitle("test1").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("test1");
				schedule.setDescription("description");
				schedule.setStartDateTime(LocalDateTime.of(2025,4,1,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,4,2,0,0));
				schedule.setParticipants(userList12);
				schedule.setCreatedUser(testUser1);
				
				scheduleRepository.save(schedule);
			}
			
			if(scheduleRepository.findByTitle("test2").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("test2");
				schedule.setDescription("description");
				schedule.setStartDateTime(LocalDateTime.of(2025,5,3,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,5,4,0,0));
				schedule.setParticipants(userList3);
				schedule.setCreatedUser(testUser3);
				
				scheduleRepository.save(schedule);
			}
			
			if(scheduleRepository.findByTitle("test3").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("test3");
				schedule.setDescription("description");
				schedule.setStartDateTime(LocalDateTime.of(2025,5,5,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,5,6,0,0));
				schedule.setParticipants(userList24);
				schedule.setCreatedUser(testUser4);
				
				scheduleRepository.save(schedule);
			}
			
			if(scheduleRepository.findByTitle("test4").isEmpty()) {
				Schedule schedule = new Schedule();
				schedule.setTitle("全員参加の予定");
				schedule.setDescription("Meeting");
				schedule.setStartDateTime(LocalDateTime.of(2025,6,1,0,0));
				schedule.setEndDateTime(LocalDateTime.of(2025,7,1,0,0));
				schedule.setParticipants(userListAll);
				schedule.setCreatedUser(ishihara);
				
				scheduleRepository.save(schedule);
			}
			
			//Noticeの初期設定.
			if(noticeRepository.findByTitle("notice1").isEmpty()) {
				Notice notice = new Notice();
				notice.setTitle("notice1");
				notice.setBody("This is Body");
				notice.setCreatedAt(LocalDateTime.of(2023,3,31,0,0));
				notice.setCreatedUser(testUser1);
				
				noticeRepository.save(notice);
			}
			
			if(noticeRepository.findByTitle("飲み会のお知らせ").isEmpty()) {
				Notice notice = new Notice();
				notice.setTitle("飲み会のお知らせ");
				notice.setBody("6月30日（月）に懇親会を開催します。");
				notice.setCreatedAt(LocalDateTime.of(2025,6,25,14,23));
				notice.setCreatedUser(ishihara);
				
				noticeRepository.save(notice);
			}
			
			if(noticeRepository.findByTitle("これがお知らせ").isEmpty()) {
				Notice notice = new Notice();
				notice.setTitle("これがお知らせ");
				notice.setBody("これこそが真のお知らせ！");
				notice.setCreatedAt(LocalDateTime.of(2025,6,24,0,0));
				notice.setCreatedUser(furuta);
				
				noticeRepository.save(notice);
			}
			
			if(noticeRepository.findByTitle("これもお知らせ").isEmpty()) {
				Notice notice = new Notice();
				notice.setTitle("これもお知らせ");
				notice.setBody("これもお知らせの中のお知らせ");
				notice.setCreatedAt(LocalDateTime.of(2025,6,24,0,0));
				notice.setCreatedUser(takiguchi);
				
				noticeRepository.save(notice);
			}
			
			if(noticeRepository.findByTitle("これはお知らせ？").isEmpty()) {
				Notice notice = new Notice();
				notice.setTitle("これはお知らせ？");
				notice.setBody("これはお知らせなのだろうか？");
				notice.setCreatedAt(LocalDateTime.of(2025,6,24,0,0));
				notice.setCreatedUser(arai);
				
				noticeRepository.save(notice);
			}
		};
	}
}
