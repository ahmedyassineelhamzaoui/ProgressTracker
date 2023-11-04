package com.app.repository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.app.model.User;
import com.app.service.UserService;
import com.app.repository.UserRepositoryImpl;

public class Test {
    public static void main(String[] args) {
		ApplicationContext cont = new  ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = cont.getBean(UserService.class);
		
		User user = new User();
	    user.setId(1L);
	    user.setName("John Doe");
	    user.setEmail("yassine@gmail.com");
	    user.setLastName("yassine");
	    user.setFirstName("ahmed");
	    user.setPassword("eRROR404@");
	    User savedUser = userService.saveUser(user);
	    if(savedUser != null) {
	        System.out.println("User saved successfully: " + savedUser);
	    }else {
	    	System.out.println("no there is an error");
	    }
    }
}
