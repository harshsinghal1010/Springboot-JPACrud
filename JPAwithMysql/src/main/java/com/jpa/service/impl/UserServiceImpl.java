package com.jpa.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.entity.User;
import com.jpa.repository.UserRepository;
import com.jpa.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		System.err.println(user);
		userRepository.save(user);
		return user.getUid();
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElse(null);
		return user;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		System.err.println(user);
		userRepository.save(user);
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		return 0;
	}

}
