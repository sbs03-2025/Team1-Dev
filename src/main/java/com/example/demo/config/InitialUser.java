package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Configuration
public class InitialUser {
	//DB "users" の初期設定.InitUserとInitAdminUserを作成.
	@Bean
	public CommandLineRunner initUser(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			System.out.println("ユーザー初期設定");
			// githubに追加するためのコメント。後で消す。
			if(userRepository.findByName("InitUser").isEmpty()) {
				User user = new User();
				user.setName("InitUser");
				user.setEmail("init@email.com");
				user.setPasswordHash(encoder.encode("password"));
				user.setRole("ROLE_USER");
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
				userRepository.save(user);
				System.out.println("InitAdminUserを登録しました。");
			}else {
				System.out.println("InitAdminUserは登録済みです。");
			}
		};
	}
}
