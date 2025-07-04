package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    
    private String role;

    @ManyToMany(fetch = FetchType.EAGER) // EAGERはセキュリティ用に即時ロードが多い
    @JoinTable(
        name = "user_department",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @Builder.Default
    private List<Department> myDepartment = new ArrayList<>();
    
    private LocalDate joinedAt;	// 入社日
//    private String department;		// 所属部署（例：開発部、総務部など）
    private String hobby;	 		// 趣味
    private String bio; 			// 自己紹介
    
    @ManyToMany(mappedBy = "participants")
    private List<Schedule> schedules;
    
    //削除するために追加
    private boolean deleteFlag;
    
 // --- プロフィール拡張項目（将来使用予定） ---
    // private String imageUrl;   	// プロフィール画像のURLまたはパス
    // private String status;   	// 現在のステータス（在席中、外出中など）

}
