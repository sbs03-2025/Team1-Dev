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
			
			Department newDepZ = departmentRepository.findByName("財務部").orElseGet(() -> {
				Department dep = new Department();
				dep.setName("財務部");
				return departmentRepository.save(dep);
			});
			

			Department newDepS = departmentRepository.findByName("生産部").orElseGet(() -> {
				Department dep = new Department();
				dep.setName("生産部");
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
			if (scheduleRepository.findByTitle("test1").isEmpty()) {
			    Schedule schedule = new Schedule();
			    schedule.setTitle("test1");
			    schedule.setDescription("description");
			    schedule.setStartDateTime(LocalDateTime.of(2025,7,1,13,0));
			    schedule.setEndDateTime(LocalDateTime.of(2025,7,1,15,0));
			    schedule.setParticipants(userList12);
			    schedule.setCreatedUser(testUser1);
			    scheduleRepository.save(schedule);
			}

			if (scheduleRepository.findByTitle("test2").isEmpty()) {
			    Schedule schedule = new Schedule();
			    schedule.setTitle("test2");
			    schedule.setDescription("description");
			    schedule.setStartDateTime(LocalDateTime.of(2025,7,2,15,0));
			    schedule.setEndDateTime(LocalDateTime.of(2025,7,2,17,0));
			    schedule.setParticipants(userList3);
			    schedule.setCreatedUser(testUser3);
			    scheduleRepository.save(schedule);
			}

			if (scheduleRepository.findByTitle("test3").isEmpty()) {
			    Schedule schedule = new Schedule();
			    schedule.setTitle("test3");
			    schedule.setDescription("description");
			    schedule.setStartDateTime(LocalDateTime.of(2025,7,3,10,0));
			    schedule.setEndDateTime(LocalDateTime.of(2025,7,3,11,0));
			    schedule.setParticipants(userList24);
			    schedule.setCreatedUser(testUser4);
			    scheduleRepository.save(schedule);
			}

			if (scheduleRepository.findByTitle("test4").isEmpty()) {
			    Schedule schedule = new Schedule();
			    schedule.setTitle("全員参加の予定");
			    schedule.setDescription("Meeting");
			    schedule.setStartDateTime(LocalDateTime.of(2025,6,30,16,0));
			    schedule.setEndDateTime(LocalDateTime.of(2025,6,30,18,0));
			    schedule.setParticipants(userListAll);
			    schedule.setCreatedUser(ishihara);
			    scheduleRepository.save(schedule);
			}

			if (scheduleRepository.findByTitle("週始会議").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("週始会議");
			    s.setDescription("今週のタスク確認");
			    s.setStartDateTime(LocalDateTime.of(2025, 6, 30, 9, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 6, 30, 10, 0));
			    s.setParticipants(List.of(testUser1, testUser2, testUser4, testUser3, arai));
			    s.setCreatedUser(testUser1);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("部内チェック").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("部内チェック");
			    s.setDescription("部署別の状況確認");
			    s.setStartDateTime(LocalDateTime.of(2025, 6, 30, 13, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 6, 30, 14, 0));
			    s.setParticipants(List.of(wang, nguyen, testUser4, testUser2,testUser1));
			    s.setCreatedUser(wang);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("週報記入").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("週報記入");
			    s.setDescription("個人作業");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 4, 11, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 4, 12, 0));
			    s.setParticipants(List.of(furuta, testUser2, wang));
			    s.setCreatedUser(furuta);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("データレビュー").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("データレビュー");
			    s.setDescription("各部のデータ確認");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 1, 10, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 1, 11, 0));
			    s.setParticipants(List.of(nguyen, takiguchi, arai, testUser1, wang));
			    s.setCreatedUser(takiguchi);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("営業戦略会議").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("営業戦略会議");
			    s.setDescription("営業チームの月次計画");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 1, 13, 30));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 1, 15, 0));
			    s.setParticipants(List.of(testUser3, testUser4, furuta));
			    s.setCreatedUser(testUser4);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("一対一MTG").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("一対一MTG");
			    s.setDescription("メンタリング");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 1, 16, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 1, 17, 0));
			    s.setParticipants(List.of(arai, wang, testUser3, testUser2));
			    s.setCreatedUser(arai);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("コードレビュー").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("コードレビュー");
			    s.setDescription("ペアプログラミング & レビュー");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 2, 9, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 2, 10, 30));
			    s.setParticipants(List.of(testUser2, wang, ishihara, testUser1));
			    s.setCreatedUser(wang);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("部署横断会議").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("部署横断会議");
			    s.setDescription("部署間の意見交換");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 2, 11, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 2, 12, 0));
			    s.setParticipants(List.of(testUser1, nguyen, furuta, wang));
			    s.setCreatedUser(testUser1);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("業務改善MTG").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("業務改善MTG");
			    s.setDescription("改善提案の検討");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 2, 14, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 2, 15, 30));
			    s.setParticipants(List.of(takiguchi, arai, testUser4));
			    s.setCreatedUser(arai);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("レビュー準備").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("レビュー準備");
			    s.setDescription("成果物レビューの事前準備");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 3, 9, 30));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 3, 10, 30));
			    s.setParticipants(List.of(testUser1, nguyen, testUser4, testUser3));
			    s.setCreatedUser(nguyen);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("開発定例").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("開発定例");
			    s.setDescription("開発チームの定例会");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 3, 13, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 3, 14, 30));
			    s.setParticipants(List.of(ishihara, furuta, wang, nguyen));
			    s.setCreatedUser(ishihara);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("英語勉強会").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("英語勉強会");
			    s.setDescription("社内英語勉強会");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 3, 17, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 3, 18, 0));
			    s.setParticipants(List.of(testUser2, wang, testUser3));
			    s.setCreatedUser(testUser2);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("金曜ふりかえり").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("金曜ふりかえり");
			    s.setDescription("1週間のふりかえり会");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 4, 9, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 4, 10, 0));
			    s.setParticipants(List.of(testUser1, testUser4));
			    s.setCreatedUser(testUser1);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("次週準備").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("次週準備");
			    s.setDescription("来週のタスク整理");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 4, 13, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 4, 14, 0));
			    s.setParticipants(List.of(testUser3, arai, nguyen, testUser1));
			    s.setCreatedUser(arai);
			    scheduleRepository.save(s);
			}

			if (scheduleRepository.findByTitle("軽食交流会").isEmpty()) {
			    Schedule s = new Schedule();
			    s.setTitle("軽食交流会");
			    s.setDescription("部署間交流イベント");
			    s.setStartDateTime(LocalDateTime.of(2025, 7, 4, 17, 0));
			    s.setEndDateTime(LocalDateTime.of(2025, 7, 4, 18, 0));
			    s.setParticipants(List.of(ishihara, furuta, takiguchi, arai, nguyen, wang, testUser2, testUser3));
			    s.setCreatedUser(takiguchi);
			    scheduleRepository.save(s);
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
