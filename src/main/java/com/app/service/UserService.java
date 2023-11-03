package com.app.service;

import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepositoryImpl;

@Service
public class UserService {

	private UserRepositoryImpl userRepositoryImpl; 
	public UserService(UserRepositoryImpl userRepositoryImpl) {
		this.userRepositoryImpl = userRepositoryImpl;
	}
	public void saveUser(User user) {
        if (isValidUser(user)) {
        	userRepositoryImpl.save(user);
        } else {
            throw new IllegalArgumentException("Invalid user data");
        }
    }
	 private boolean isValidUser(User user) {
        return user.getFirstName() != null && !user.getFirstName().isEmpty() &&
                user.getLastName() != null && !user.getLastName().isEmpty();
    }
}
