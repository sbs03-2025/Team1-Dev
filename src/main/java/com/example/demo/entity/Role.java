package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Role {
	@Id
	private Long id;
	private String role;
	
	@OneToMany
	List<User> user ;
}
