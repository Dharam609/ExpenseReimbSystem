package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.example.controller.ReimbController;
import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.utility.DaoUtility;

public class ReimbDaoImpl {
	
	UserDaoImpl udi = new UserDaoImpl();
	DaoUtility dUtil;
	private static Connection con = null;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static Logger logger = Logger.getLogger(ReimbDaoImpl.class);
	
	public ReimbDaoImpl() {
		dUtil = new DaoUtility();		
	}
	
	public boolean insertReimb(double amount, String description, int authorId, int typeId) {
				
		try {	
				con = DaoUtility.getConnection();

				String sql = "insert into reimbursements (amount, description, author_id, status_id, type_id) values(?,?,?,?,?)";
							
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, amount);
				ps.setString(2, description);					
				ps.setInt(3, authorId);					
				ps.setInt(4, 2);
				ps.setInt(5, typeId);
				
				int success = ps.executeUpdate();
				if(success == 1) {					
					logger.info("An Expense Reimbursement Request successfully submitted.");					
					return true;
				}				
				
							
		
		} catch (Exception e) {
			logger.warn("Exception while submitting expense reimbursement request." , e);
		} finally {
//			con.close();
//			ps.close();
		}
		return false;
	}
	

	public List<Reimbursement> getReimbsByUsername(String username) {		
		List<Reimbursement> lReimb = new ArrayList<>();
		User user = udi.getUserByUsername(username);
		if(user != null) {
			try {	
				con = DaoUtility.getConnection();
				String sql = "select * from reimbursements where author_id = ?";
				
				ps = con.prepareStatement(sql);
				ps.setInt(1, user.getUserId());
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					String applicant = udi.getUserById(rs.getInt(7)).getFname();
					String managerName = udi.getUserById(rs.getInt(8)).getFname();
					if(managerName == null) {
						managerName = "---";
					}
					String status = "";
					switch(rs.getInt(9)) {
					case 1:
						status = "Approved";
						break;
					case 2:
						status = "Pending";
						break;
					case 3:
						status = "Denied";
						break;
					default:
						break;
					}
					
					String type = "";
					switch(rs.getInt(10)) {
					case 1:
						type = "Lodging";
						break;
					case 2:
						type = "Travel";
						break;
					case 3:
						type = "Food";
						break;
					case 4:
						type = "Other";
						break;
					default:
						break;
					}
					lReimb.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), applicant, managerName, status, type));
				}
				if(lReimb.size() > 0) {					
					logger.info("Reimb rquests returned provided username.");					
				}
			
				
			} catch (SQLException e) {
				logger.warn("Exception while returning reimb list when username provided." , e);
			} 
			finally {
//				con.close();
//				ps.close();
//				rs.close();
			}
			
		}
		
		return lReimb;
	}
	
	public List<Reimbursement> getAllReimbs() {		
		List<Reimbursement> lReimb = new ArrayList<>();		
	
		try {	
				con = DaoUtility.getConnection();
				String sql = "select * from reimbursements";
				
				ps = con.prepareStatement(sql);					
				rs = ps.executeQuery();
				while(rs.next()) {
					String applicant = udi.getUserById(rs.getInt(7)).getFname();
					String managerName = udi.getUserById(rs.getInt(8)).getFname();
					if(managerName == null) {
						managerName = "---";
					}
					String status = "";
					switch(rs.getInt(9)) {
					case 1:
						status = "Approved";
						break;
					case 2:
						status = "Pending";
						break;
					case 3:
						status = "Denied";
						break;
					default:
						break;
					}
					
					String type = "";
					switch(rs.getInt(10)) {
					case 1:
						type = "Lodging";
						break;
					case 2:
						type = "Travel";
						break;
					case 3:
						type = "Food";
						break;
					case 4:
						type = "Other";
						break;
					default:
						break;
					}
					lReimb.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), applicant, managerName, status, type));
					if(lReimb.size() > 0) {					
						logger.info("All reimb requests returned.");					
					}
				}				
				ps.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				logger.warn("Exception while returning all reimb lists." , e);
			} finally {
			
			}
		
		return lReimb;
	}
	public boolean approve(int reimbId, int rId) {
		LocalDateTime timeStamp =  LocalDateTime.now();
		System.out.println(timeStamp);
		
		try {	
			con = DaoUtility.getConnection();
			
			String update = "update reimbursements set resolved_date=?, status_id=?, resolver_id=? where reimb_id = ?";
//			timeStamp = (Timestamp) new java.util.Date();
			System.out.println(timeStamp);
			PreparedStatement ps = con.prepareStatement(update);
			ps.setObject(1, timeStamp);
			ps.setInt(2, 1);
			ps.setInt(3, rId);
			ps.setInt(4, reimbId);
			
			int success = ps.executeUpdate();
			if(success == 1) {
				logger.info("Reimbursement Request Approved.");
				return true;
			}				
			
			
	} catch (Exception e) {
		logger.warn("Exception while approving a reimbursement request." , e);
	} finally {
		
//		con.close();
//		ps.close();
	}
	return false;
		
	}

	public boolean deny(int reimbId, int rId) {
		LocalDateTime timeStamp =  LocalDateTime.now();
		System.out.println(timeStamp);
		
		try {	
			con = DaoUtility.getConnection();
			
			String update = "update reimbursements set resolved_date=?, status_id=?, resolver_id=? where reimb_id = ?";

			System.out.println(timeStamp);
			PreparedStatement ps = con.prepareStatement(update);
			ps.setObject(1, timeStamp);
			ps.setInt(2, 3);
			ps.setInt(3, rId);
			ps.setInt(4, reimbId);
			
			int success = ps.executeUpdate();
			if(success == 1) {
				logger.info("Reimbursement Request Denied.");
				return true;
			}				
			
			
	} catch (Exception e) {
		logger.warn("Exception while denying a reimbursement request." , e);
	} finally {
		
//		con.close();
//		ps.close();
	}
	return false;
		
	}
}
