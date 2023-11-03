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
	public User saveUser(User user) {
        if (isValidUser(user)) {
        	return userRepositoryImpl.save(user);
        } else {
            throw new IllegalArgumentException("Invalid user data");
        }
        
    }
	 private boolean isValidUser(User user) {
		 String emailRegex = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
		 String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
	    
        return !user.getEmail().matches(emailRegex) || !user.getPassword().matches(passwordRegex) || (user.getFirstName() != null && !user.getFirstName().isEmpty() &&
                user.getLastName() != null && !user.getLastName().isEmpty());
    }
}
