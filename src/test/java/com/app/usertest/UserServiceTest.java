package com.app.usertest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.model.User;
import com.app.repository.UserRepositoryImpl;
import com.app.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {
	
	

    @Mock
    private UserRepositoryImpl userRepositoryImpl;
    
    @InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testSaveUser() {
	    User user = new User();
	    user.setId(1L);
	    user.setName("John Doe");
	    user.setEmail("yassine@gmail.com");
	    user.setLastName("yassine");
	    user.setFirstName("ahmed");
	    user.setPassword("eRROR404@");

	    when(userRepositoryImpl.save(user)).thenReturn(user);

	    User savedUser = userService.saveUser(user);

	    verify(userRepositoryImpl).save(user);

	    assertEquals(user, savedUser);
	}


}
