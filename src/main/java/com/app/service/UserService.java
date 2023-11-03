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
        return isValidEmail(user.getEmail()) &&
               isValidPassword(user.getPassword()) &&
               isValidName(user.getFirstName()) &&
               isValidName(user.getLastName());
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
        return password.matches(passwordRegex);
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }
}
