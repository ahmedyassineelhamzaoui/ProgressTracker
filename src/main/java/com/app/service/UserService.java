package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.User;
import com.app.repository.CrudRepository;

@Service
public class UserService {

	private CrudRepository userRepository; 
	
	public UserService(CrudRepository userRepository) {
		this.userRepository = userRepository;
	}
	public User saveUser(User user) {
        if (isValidUser(user)) {
        	return userRepository.save(user);
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
