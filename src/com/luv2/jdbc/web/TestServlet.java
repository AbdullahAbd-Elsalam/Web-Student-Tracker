package com.luv2.jdbc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		// get writer
		PrintWriter out =response.getWriter();
		response.setContentType("text/plain");
		
		// 2 create database to connect it
		
		Connection conn=null;
		Statement stml=null;
		ResultSet rs=null;
		try {
			// step 4 
			conn=datasource.getConnection();
			
			
			// step 4 create statmetn
			String sql="select * from student";
			stml=conn.createStatement();
			
		// step 5 process query
			rs= stml.executeQuery(sql);
			
			while(rs.next()) {
				
				String email=rs.getString("email");
			//	if(email.contains("john@luv2code.com")) {
				out.println(email);
				//}
			}
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			out.println(e.getMessage());
		}
		
		
		
		
		
		
	}

}
