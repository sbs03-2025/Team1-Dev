package com.example.demo.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ
		response.setContentType("application/json;charset=UTF-8");
		
		String role = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.findFirst() //roleは複数持っていても、最初の１個だけを持ってくるようになっている。 
				.orElse("UNKNOWUN"); //roleを持っていなければ、role="unknowun"として送信。Vue側でそのようなユーザは排除。 
		
		Map<String,String> responseBody = new HashMap<>();
		responseBody.put("role", role);
		responseBody.put("message", "ログイン成功");
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		objectMapper.writeValue(response.getWriter(), responseBody); //Vue側に渡すJSONデータは、httpステータス、body(role,message)の２つだけ。 
	}
}
