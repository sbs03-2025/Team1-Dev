package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // DBからユーザーを検索
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + name));

        // 権限をSpring形式に変換（例："ADMIN" → ROLE_ADMIN）
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));

        // Spring Security用のユーザー情報を返す
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPasswordHash(),
                authorities
        );
    }
}

