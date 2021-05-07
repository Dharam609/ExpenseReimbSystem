package com.example.service;


import com.example.dao.UserDaoImpl;
import com.example.model.User;

public class UserService {
	
	UserDaoImpl udi = new UserDaoImpl();

	public User getVerifiedUser(String username, String password)  {
		User user = udi.getUserByUsername(username);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return user;
			}			
		} 
		return null;		
	}
	
	public User registerUser(String username, String password, String fname, String lname, String email, int role_id) {
		return udi.insertUser(username, password, fname, lname, email, role_id);
	}
	
	public User getUserById(int authId) {
		return udi.getUserById(authId);
	}
	
	public User getUserByUserName(String username) {
		return udi.getUserByUsername(username);
	}
}
