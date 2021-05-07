package com.example.controller;

import java.io.IOException;
import java.net.ResponseCache;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.example.dao.ReimbDaoImpl;
import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.service.ReimbService;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	static UserService us = new UserService();
	static ReimbService rs = new ReimbService();
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	
	public static String insertUser(HttpServletRequest req) {
		try {
			if (!req.getMethod().equals("POST")) {
				return "registration.html";
			} else {
				int role_id = 0;
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String cpassword = req.getParameter("cpassword");
				String fname = req.getParameter("fname");
				String lname = req.getParameter("lname");
				String email = req.getParameter("email");
				String role = req.getParameter("role");
				User user = us.getUserByUserName(username);
				if(user == null && password.equals(cpassword) ) {
					switch(role) {
					case "Employee":
						role_id = 1;
						break;
					case "Manager":
						role_id = 2;
						break;
					default:
						break;
					}
					us.registerUser(username, password, fname, lname, email, role_id);				
					return "index.html";
				} else {
					return "registration.html";
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "index.html";

	}
	public static String login(HttpServletRequest req){
		if(!req.getMethod().equals("POST")) {
			logger.warn("Uncusseful login");
			return "index.html";
		}
		String username = req.getParameter("username");
		User user = us.getVerifiedUser(req.getParameter("username"), req.getParameter("password"));
		
		if(user == null) {
			logger.warn("Uncusseful login");
			return "index.html";
		} 
		else {
			req.getSession().setAttribute("currentUser", user);
			HttpSession session=req.getSession(true);
			session.setAttribute("username", username);
			if(user.getRoleId() == 1) {
				logger.info("Successfully logged in as an employee.");
				return "employee.html";
			} else {
				logger.info("Successfully logged in as a manager");
				return "manager.html";
			}
		}
	}
	
	public static String logout(HttpServletRequest req, HttpServletResponse res) {
		//throw new RuntimeException("Something went wrong");
		
		/*
		 * If session.invalidate() doesn't work for you
		 */
		System.out.println(req.getSession());
		req.getSession().removeAttribute("currentUser");
		req.getSession().invalidate();
		System.out.println(req.getSession());
//		ResponseCache.setDefault(null);
		logger.info("Successfully logged out");
		return "index.html";
	}
	
	public static void getUserSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		User user = (User) req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(user));
	}
	
	public static void setUserReimbList(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursement> lReimbs = new ArrayList<>();
		HttpSession session=req.getSession(true);
		String username = (String) session.getAttribute("username");
		System.out.println(username);
//		User user = new User();
//		user = (User) session.getAttribute("loginUser");
		lReimbs = rs.listAllReimbsByUsername(username);		
		res.getWriter().write(new ObjectMapper().writeValueAsString(lReimbs));
	}

}
