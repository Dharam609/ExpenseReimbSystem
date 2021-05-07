package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.example.utility.DaoUtility;

import com.example.model.User;

public class UserDaoImpl {
	
	private static Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	public User insertUser(String username, String password, String fname, String lname, String email, int role_id){
		User user = getUserByUsername(username);
		if(user == null) {
			try {
				con = DaoUtility.getConnection();
				String sql = "insert into users(username, password, first_name, last_name, email, role_id)"
						+ "values(?,?,?,?,?,?)";
				
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, fname);
				ps.setString(4, lname);
				ps.setString(5, email);
				ps.setInt(6, role_id);
				
				ps.execute();
				
				user = getUserByUsername(username);
				if(user != null) {
					logger.info("A user successfully created.");
				}
				
				
			} catch (Exception e) {
				logger.warn("Exception while registering a user." , e);
			} finally {
//				con.close();
//				ps.close();
			}
		}
		return user;		
	}	
	
	public User getUserByUsername(String username)  {
		User user = null;
		try {	
			con = DaoUtility.getConnection();
			
			String sql = "select * from users where username = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			if(user != null) {
				logger.info("User returned provided username.");
			}
			
		} catch (Exception e) {
//			logger.warn("Exception while getting a user by username." , e);
		} finally {
//			con.close();
//			ps.close();
//			rs.close();
		}
		return user;
	}
	
	public User getUserById(int authorId)  {
		User user = new User();
		
		try {	
			con = DaoUtility.getConnection();
			
			String sql = "select * from users where user_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, authorId);			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			if(user != null) {
				logger.info("User returned provided user id.");
			}
			
		} catch (Exception e) {
			logger.warn("Exception while getting a user by user id." , e);
		} finally {
//			con.close();
//			ps.close();
//			rs.close();
		}
		return user;
	}	
}
