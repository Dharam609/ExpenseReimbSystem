package com.example.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.ReimbController;
import com.example.controller.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestDispatcher {
	
	public static String process(HttpServletRequest req, HttpServletResponse res) throws  JsonProcessingException, IOException {
			String URI = req.getRequestURI();
			switch(URI) {
				case "/ExpenseReimb/login.dashboard":
					System.out.println("in login.dashboard dispatcher");
					return UserController.login(req);
					
				case "/ExpenseReimb/update.dashboard":
					System.out.println("in approve");
					return ReimbController.approve(req);
					
				case "/ExpenseReimb/deny.dashboard":
					System.out.println("in deny");
					return ReimbController.deny(req);
					
				case "/ExpenseReimb/reimb.dashboard":
					System.out.println("in expenss.dashboard dispatcher");
					return ReimbController.sumbitExpense(req);
					
				case "/ExpenseReimb/logout.dashboard":
					System.out.println("in logout dispatcher");
					return UserController.logout(req,res);
					
				case "/ExpenseReimb/register.dashboard":
					System.out.println("in register dispatcher");
					return UserController.insertUser(req);
					
				default:
						System.out.println("in default");
						return "/ExpenseReimb/index.change";					
			
			}
			
		}


}
