package com.example.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dao.ReimbDaoImpl;
import com.example.model.Reimbursement;
import com.example.model.User;

public class ReimbService {
	
	ReimbDaoImpl rdi = new ReimbDaoImpl();
	
	public boolean submitExpense(HttpServletRequest req, double amount, String description, String type)  {
		User user = (User) req.getSession().getAttribute("currentUser");		
		int authorId = 0;
		int typeId = 0;
		if(user != null) {
			authorId = user.getUserId();
			typeId = 0;
			
			switch(type.toLowerCase().trim()) {				
			case "lodging":
				typeId = 1;
				break;
			case "travel":
				typeId = 2;
				break;
			case "food":
				typeId = 3;
				break;
			case "other":
				typeId = 4;
				break;
			default:
				break;
				
			}			
		}	
		return rdi.insertReimb(amount, description, authorId, typeId);
	}
	
	public boolean approveStatus(int reimbId, int rId) {
		return rdi.approve(reimbId, rId);
	}
	
	public boolean denyStatus(int reimbid, int rId) {		
		return rdi.deny(reimbid, rId);
	}
	
	public List<Reimbursement> listAllReimbs() {
		return rdi.getAllReimbs();
	}	
	
	public List<Reimbursement> listAllReimbsByUsername(String username) {
		return rdi.getReimbsByUsername(username);
	}	
}
