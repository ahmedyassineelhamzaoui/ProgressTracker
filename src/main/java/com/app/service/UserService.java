package com.app.service;

import com.app.repository.UserRepositoryImpl;

public class UserService {

	private UserRepositoryImpl userRepositoryImpl; 
	public UserService(UserRepositoryImpl userRepositoryImpl) {
		this.userRepositoryImpl = userRepositoryImpl;
	}
}
