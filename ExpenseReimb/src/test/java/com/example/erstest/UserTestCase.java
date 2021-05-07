package com.example.erstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.example.model.User;
import com.example.service.UserService;


public class UserTestCase {

	@Mock
	private UserService fakeus = new UserService();;	
	private User fUser1;
	private User fUser2;
	private User fUser3;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fUser1 = new User("username1", "password1", "fname1", "lname1", "email1", 1);
		fUser2 = new User("username2", "password2", "fname2", "lname2", "email2", 2);		

		when(fakeus.getVerifiedUser("username1", "password1")).thenReturn(fUser1);
		when(fakeus.getUserByUserName("username2")).thenReturn(fUser2);
		when(fakeus.getUserByUserName("")).thenReturn(null);		
	}
	
	@Test
	public void getUsernameWithSuccess() {		
		assertEquals("username1", fUser1.getUsername(), "User name returned."); 
	}	
	
	@Test
	public void registerUserWithoutUsernameFailure() {
		assertEquals(null, fakeus.registerUser(null, "password1", "fname1", "lname1", "email1", 1), "User not registered provided no username."); 
	}	
	
	@Test
	public void getUserByUserNameWithFailure() {
		assertEquals(null, fakeus.getUserByUserName("nouser"), "User not returned for non existing user."); 
	}
}
