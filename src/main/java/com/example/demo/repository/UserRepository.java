package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);
	Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findNameById(Long id);
    
    @Query("select user.role from User user where user.name = :name ")
    Optional<String> findRoleByName(@Param("name")String name);
    // 参加者取得に使われる findAllById(List<Long> ids) はJPAに既に存在しているためOK
}

