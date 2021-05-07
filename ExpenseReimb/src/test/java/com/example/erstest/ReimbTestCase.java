package com.example.erstest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.ReimbDaoImpl;
import com.example.model.Reimbursement;
import com.example.service.ReimbService;


public class ReimbTestCase {

	@Mock
	private ReimbService fakers = new ReimbService();
	private ReimbDaoImpl fakerdi = new ReimbDaoImpl();
	private Reimbursement reimb1;
	private Reimbursement reimb2;
	private Reimbursement reimb3;
	
	List<Reimbursement> lr = new ArrayList<Reimbursement>();
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
			
		reimb1 = new Reimbursement(500, "2021-04-24", "2021-04-36", "Testing1","Tester1", "Manager", "Pending", "Food");
		reimb2 = new Reimbursement(400, "2021-04-25", "2021-04-38", "Testing2","Tester2", "Manager", "Approved", "Travel");
		reimb3 = new Reimbursement(300, "2021-04-29", "2021-04-30", "Testing3","Tester3", "Manager", "Denied", "Other");
		lr.add(reimb1);
		lr.add(reimb2);
		lr.add(reimb3);

		when(fakers.listAllReimbs()).thenReturn(lr); 			
		
	}
	
	@Test
	public void getAllReimbsWithSuccess() {
		assertEquals(lr, fakers.listAllReimbs(), "All reimbs returned successfully."); 
	}
	
	@Test
	public void submitReimbRequestWithSuccess() {
		assertEquals(true, fakerdi.insertReimb(500,"Testing7",2,2), "Reimb request submitted successfully."); 
	}
	
	@Test
	public void approveReimbRequestWithFailure() {
		assertEquals(false, fakerdi.approve(100,50), "Did not approve provided invalid reimb id."); 
	}
	
}
