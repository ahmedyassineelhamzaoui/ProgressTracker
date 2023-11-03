package com.app.service;

import com.app.model.User;
import com.app.repository.UserRepositoryImpl;

public class UserService {

	private UserRepositoryImpl userRepositoryImpl; 
	public UserService(UserRepositoryImpl userRepositoryImpl) {
		this.userRepositoryImpl = userRepositoryImpl;
	}
	 private boolean isValidUser(User user) {
        return user.getFirstName() != null && !user.getFirstName().isEmpty() &&
                user.getLastName() != null && !user.getLastName().isEmpty();
    }
}
