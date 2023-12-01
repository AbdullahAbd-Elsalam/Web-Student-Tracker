package com.luv2.jdbc.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	// define annotation for datasaource
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
	
	// get studentDBUtil
	private StudentDBUtil studentdbutil;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		
		try {
			studentdbutil= 	new StudentDBUtil(datasource);
			
		}catch(Exception ex) {
			
			throw new ServletException();
			
		}
		
	}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listStudents(request, response);
				break;
				
			case "ADD":
				addstudent(request, response);
				break;
			case"LOAD": 
				loadStudent(request,response);
	 	        break;
			case "UPDATE" :
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request,response);
				break;
			default:
				listStudents(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}




	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		
		// read id  from form data 
		String id=request.getParameter("studentId");
		// delete data from database
		studentdbutil.deleteStudent(id);
		// send them back to the list page
		listStudents(request, response);

		
	}




	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		// read student info from form data
				int id = Integer.parseInt(request.getParameter("stdid"));
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String email = request.getParameter("email");
				
				// create a new student object
				Student theStudent = new Student(id, firstName, lastName, email);
				
				// perform update on database
				 studentdbutil.updateStudent(theStudent);
				
				// send them back to the "list students" page
				listStudents(request, response);
		
		 
	}




	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
 		
		// read student id from form data
				String theStudentId = request.getParameter("studentId");
				
				// get student from database (db util)
				Student theStudent = studentdbutil.getStudent(theStudentId);
				
				// place student in the request attribute
				request.setAttribute("THE_STUDENT", theStudent);
				
				// send to jsp page: update-student-form.jsp
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("/update-student-form.jsp");
				dispatcher.forward(request, response);		
	}




	private void addstudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
	 
		
		// read student info from form data
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String email = request.getParameter("email");		
				
				// create a new student object
				Student theStudent = new Student( firstName, lastName, email);
				
				// add the student to the database
				studentdbutil.addStudent(theStudent);
						
				// send back to main page (the student list)
				listStudents(request, response);
	}




	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		// TODO Auto-generated method stub
		
		// get student from db util
		
		
		List<Student> students=studentdbutil.getListStudent();
		// put student object into request
		request.setAttribute("STUDENT_LIST", students);
		// send request into jsp page (view)
		RequestDispatcher dispatchr=request.getRequestDispatcher("/list-students.jsp");
		dispatchr.forward(request, response);
		 
	}

}
