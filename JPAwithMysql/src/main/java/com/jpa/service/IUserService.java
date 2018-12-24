package com.jpa.service;

import java.util.List;
import java.util.Optional;

import com.jpa.entity.User;

public interface IUserService {

	public int insert(User user);
	public User findById(int id);
	public List<User> findAll();
	public int update(User user);
	public int delete(int id);
}
