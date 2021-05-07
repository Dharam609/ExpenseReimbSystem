package com.example.servlet;

import java.io.IOException;
import javax.servlet.FilterChain;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.example.dispatcher.RequestDispatcher;

public class MasterServlet extends HttpServlet {	
   
   
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		try {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	        res.setHeader("Pragma", "no-cache");
	        res.setHeader("Expires", "0");
	        
			req.getRequestDispatcher(RequestDispatcher.process(req,res)).forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
//    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expiresponse", 0);
//        chain.doFilter(request, response);
//    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("in master doPost");
		try {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	        res.setHeader("Pragma", "no-cache");
	        res.setHeader("Expires", "0");
			req.getRequestDispatcher(RequestDispatcher.process(req,res)).forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}

}
