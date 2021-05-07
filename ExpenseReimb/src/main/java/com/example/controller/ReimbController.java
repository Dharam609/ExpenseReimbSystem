package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.service.ReimbService;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbController {

	static ReimbService rs = new ReimbService();
	static UserService us = new UserService();

	public static String sumbitExpense(HttpServletRequest req) {
		if (!req.getMethod().equals("POST")) {
			return "reimbSumbit.html";
		} else {

			double amount = Double.parseDouble(req.getParameter("amount"));
			String description = req.getParameter("description");
			String type = req.getParameter("type");
			rs.submitExpense(req, amount, description, type);
			return "employee.html";
		}

	}

	public static String approve(HttpServletRequest req) {
		
		User user = (User) req.getSession().getAttribute("currentUser");
	
		int rId = user.getUserId();
		int reimbId = Integer.parseInt(req.getParameter("value"));
		
		rs.approveStatus(reimbId, rId);
		
		return "manager.html";

	}

	public static String deny(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("currentUser");
		
		int rId = user.getUserId();
		int reimbId = Integer.parseInt(req.getParameter("value"));

		rs.denyStatus(reimbId, rId);
		
		return "manager.html";
	}

	public static void setLReimbsAttribute(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		List<Reimbursement> lReimbs = new ArrayList<>();

		lReimbs = rs.listAllReimbs();

		res.getWriter().write(new ObjectMapper().writeValueAsString(lReimbs));
	}

	public static void setUserReimbList(HttpServletRequest req, HttpServletResponse res)
			throws JsonProcessingException, IOException {
		List<Reimbursement> lReimbs = new ArrayList<>();
		HttpSession session = req.getSession(true);
		String username = (String) session.getAttribute("username");
		System.out.println(username);
//		User user = new User();
//		user = (User) session.getAttribute("loginUser");
		lReimbs = rs.listAllReimbsByUsername(username);
		res.getWriter().write(new ObjectMapper().writeValueAsString(lReimbs));
	}

}
