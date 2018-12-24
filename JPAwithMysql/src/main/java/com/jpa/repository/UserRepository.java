package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
