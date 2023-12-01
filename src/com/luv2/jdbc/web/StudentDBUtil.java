package com.luv2.jdbc.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.DataSource;

public class StudentDBUtil {
	
	
	// define object of dataSource to connection pool 
	private DataSource datasource;
	
	public StudentDBUtil(DataSource datasource) {
		
		
		this.datasource=datasource;
	}
	// first define method return list of student
	public List<Student> getListStudent() throws Exception{
		
		// derfine empty object of list of student
		List<Student> students=new ArrayList<Student>();
		
		
		// deifne conn of database
		Connection conn=null;
		Statement stml=null;
		ResultSet rs=null;
		try {
			
			
			// step 1 getconnetion
			conn=datasource.getConnection();
			
			// step 2 create sql statment
			String sql="select * from student order by last_name";
			stml= conn.createStatement();
			// step 3 generate query
			rs=stml.executeQuery(sql);
			// step 4 do precess to object of resultSet
			while(rs.next()) {
				
				// step 1 get attribute
				
				int id=rs.getInt("id");
				String fristName=rs.getString("first_name");
				String last_name=rs.getString("last_name");
				String email=rs.getString("email");

				// step 2 create object of student
				  Student tempStudent=new Student(id, fristName, last_name, email);
				// put all attrubute inside object 
				
						  students.add(tempStudent);
			 
			}
			
			
			return students;
			
			
		}
		finally {
			
			
			// close connection on database
			close(conn,stml,rs);
		}
		
		
		
		
		
	}
	private void close(Connection conn, Statement stml, ResultSet rs) {
		// TODO Auto-generated method stub
		
		try {
		
		if(conn!=null) {
			
			conn.close();
		}
		
		if(stml!= null ) {
			
			stml.close();
		}
		
		if(rs!=null) {
			
			
			rs.close();
		}
		
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addStudent(Student student) throws SQLException {
		// TODO Auto-generated method stub
		
		
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = datasource.getConnection();
			
			// create sql for insert
			String sql = "insert into student "
					   + "(first_name, last_name, email) "
					   + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1,  student.getFirst_name());
			myStmt.setString(2, student.getLast_name());
			myStmt.setString(3, student.getEmail());
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}
	public Student getStudent(String theStudentId) throws  Exception {
		

		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = datasource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from student where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				// use the studentId during construction
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}				
			
			return theStudent;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
		
 	}
 
		

 
 
 
	public void updateStudent(Student students) throws Exception {
 		
		// convert string id into in
		
 		
		Connection conn=null;
		PreparedStatement  stml=null;
  		// define dbconn
	    try {
	    	
	     // get db connection
	    	conn=datasource.getConnection();
	    	
	    		// create SQL update statement
	    		 
	    	String sql="update student set first_name=?,last_name=?,email=? where id=?";
	    	stml=conn.prepareStatement(sql);
	    	
	    	// get values 
	    	stml.setString(1, students.getFirst_name() );
	    	stml.setString(2, students.getLast_name());
	    	stml.setString(3,students.getEmail());
	    	stml.setInt(4, students.getId());
		// execute SQL statement
	    	
          stml.executeUpdate();	 
           
           
           
	    	
	    }catch(Exception ex) {
	    	
	    	throw new ServletException();
	    }
		
		finally {
			
 if(conn!=null) {
	 
	 conn.close();
 }
			
 if(stml!=null)
 {
	 
	 stml.close();
 }
		}		
		
		
		
		
	}
	public void deleteStudent(String id)  throws Exception{
 		
		
		// convert id into integer
		int stdid=Integer.parseInt(id);
		// define conn to database
		Connection conn=null;
        PreparedStatement stml=null;
        try {
		// get conn
		conn=datasource.getConnection();
		
		// create sql statmetn 
		String sql="delete from student where id=?";
		// set prepared statmetn
		stml=conn.prepareStatement(sql);
		
		stml.setInt(1, stdid);
		// execute statment
		stml.execute();
		
        }
		finally {
			
			// clean up the database
			close(conn,stml,null);
			
		}
	}
	
 

}
