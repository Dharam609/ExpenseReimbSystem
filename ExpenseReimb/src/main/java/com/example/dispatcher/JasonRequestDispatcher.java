package com.example.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.ReimbController;
import com.example.controller.UserController;
import com.example.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JasonRequestDispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
			
			switch(req.getRequestURI()) {
			
			case "/ExpenseReimb/getusersession.json":
				UserController.getUserSession(req, res);
	//			ReimbController.setLReimbsAttribute(req, res);/ExpenseReimb/update.dashboard
				break;			
			case "/ExpenseReimb/getreimblist.json":			
				ReimbController.setLReimbsAttribute(req, res);
				break;			
			case "/ExpenseReimb/formreimblist.json":			
				ReimbController.setLReimbsAttribute(req, res);
				break;
			case "/ExpenseReimb/getuserreimblist.json":
				UserController.setUserReimbList(req, res);
				break;
			default:
				res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
			
			}
		}

}
