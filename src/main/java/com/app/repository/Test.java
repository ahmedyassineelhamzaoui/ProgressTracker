package com.app.repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.app.exceptions.DuplicateUserException;
import com.app.model.User;
import com.app.service.UserService;

public class Test {
    public static void main(String[] args) {
		ApplicationContext cont = new  ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = cont.getBean(UserService.class);
		
		User user = new User();
	    user.setName("zdk dz");
	    user.setEmail("lacx,k@gmail.com");
	    user.setLastName("kezjf");
	    user.setFirstName("zdkdccf");
	    user.setPassword("sdl@JSQ23");
	    User savedUser = null ;
	    try {
	        savedUser = userService.saveUser(user);
	        if(savedUser != null) {
		        System.out.println("User saved successfully: " + savedUser);
		    }else {
		    	System.out.println("no there is an error");
		    }
	    } catch (DataIntegrityViolationException e) {
	        System.out.println("Error: Duplicate user found with email " + user.getEmail());
	    }
	    
    }
}
