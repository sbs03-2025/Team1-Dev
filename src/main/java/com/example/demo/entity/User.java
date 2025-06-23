package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String passwordHash;

    private String role;  // "USER" or "ADMIN"

    @ManyToMany(mappedBy = "participants")
    private List<Schedule> schedules;
    
//    @ManyToOne
//    private Role role;
    
    private LocalDateTime joinedAt;
    private String department;// 所属部署（例：開発部、総務部など）
    private String hobby; //入社日
    private String bio; //自己紹介
 // --- プロフィール拡張項目（将来使用予定） ---
    // private String imageUrl;    // プロフィール画像のURLまたはパス
    // private String status;      // 現在のステータス（在席中、外出中など）

}
