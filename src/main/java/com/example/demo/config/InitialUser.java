package com.example.demo.config;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Department;
import com.example.demo.entity.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class InitialUser {
	//DB "users" の初期設定.InitUserとInitAdminUserを作成.
	@Bean
	public CommandLineRunner initUser(UserRepository userRepository, DepartmentRepository departmentRepository ,PasswordEncoder encoder) {
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
			
			if(userRepository.findByName("InitUser").isEmpty()) {
				User user = new User();
				user.setName("InitUser");
				user.setEmail("init@email.com");
				user.setPasswordHash(encoder.encode("password"));
				user.setRole("ROLE_USER");
				user.setMyDepartment(Collections.singletonList(newDepHumanResrc));
				userRepository.save(user);
				System.out.println("InitUserを登録しました。");
			}else {
				System.out.println("InitUserは登録済みです。");
			}
			
			if(userRepository.findByName("InitAdminUser").isEmpty()) {
				User user = new User();
				user.setName("InitAdminUser");
				user.setEmail("initadmin@email.com");
				user.setPasswordHash(encoder.encode("adminpassword"));
				user.setRole("ROLE_ADMIN");
				user.setMyDepartment(Collections.singletonList(newDepAccount));
				userRepository.save(user);
				System.out.println("InitAdminUserを登録しました。");
			}else {
				System.out.println("InitAdminUserは登録済みです。");
			}
		};
	}
}
