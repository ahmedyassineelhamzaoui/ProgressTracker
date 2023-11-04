package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.exceptions.DuplicateUserException;
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
    public User updateUser(User user) {
        try {
            Optional<User> existingUserOptional = userRepository.findById(user.getId());
            if (existingUserOptional.isPresent()) {
                User existingUser = existingUserOptional.get();
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setLastName(user.getLastName());
                existingUser.setFirstName(user.getFirstName());
                existingUser.setPassword(user.getPassword());

                return userRepository.save(existingUser);
            } else {
                throw new IllegalArgumentException("User not found with ID: " + user.getId());
            }
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUserException("Error: Duplicate user found with email " + user.getEmail());
        }
    }
    public User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }
    public void deleteUser(Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userRepository.delete(user);
            } else {
                throw new IllegalArgumentException("User not found with ID: " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error deleting user with ID: " + id);
        }
    }

	 public List<User> getAllUsers() {
	     return userRepository.findAll();
	 }
}
