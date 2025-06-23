package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	@Autowired
	private final CustomLoginSuccessHandler customLoginSuccessHandler;
	@Autowired
	private final CustomLoginFailureHandler customLoginFailureHandler;
	@Autowired
	private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
	@Autowired
	private final CustomUserDetailsService customUserDetailService;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRFの無効設定 
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS設定を下のメソッドで設定 
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))//iframeの設定。H2データベースを使うには必要 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api/login/**").permitAll() //もしもログイン後のURLであれば、.requestMatchers().authenticated() 
                .requestMatchers(  "/users/**" ) .authenticated()       // ユーザー系（/me, /{id}, /users など）
                .requestMatchers( "/schedules/**") .authenticated() 	// スケジュール系
                .requestMatchers( "/Info/**") .authenticated()       // お知らせ一覧・登録・削除など
                .requestMatchers( "/attendance/**" ) .authenticated()   // 勤怠打刻API（出勤・退勤）（未実装）
                .requestMatchers( "/notices/**") .authenticated()       // お知らせ一覧・登録・削除など
                .anyRequest()
                .authenticated()
            )
            .formLogin(form -> form
                .loginProcessingUrl("/api/login")
                .successHandler(customLoginSuccessHandler) //ログイン成功時の処理 
                .failureHandler(customLoginFailureHandler) //ログイン失敗時の処理 
                .permitAll()
            )
            .userDetailsService(customUserDetailService)
            .logout(logout -> logout
            		.logoutUrl("/logout")
            		.logoutSuccessHandler(customLogoutSuccessHandler) //ログアウト時の処理 
            		.permitAll()
            );

        return http.build();
    }
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5173")); //@CrossOriginと同じ役割のもの 
		config.setAllowedMethods(List.of("GET","POST","DELETE","PUT","OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true); //Cookieを使えるようにしている 
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",config);
		return source;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		// passwordEncoder()メソッドを使えば、passwordをハッシュ化できる。@Beanなので、DI可能。 
	}
}
