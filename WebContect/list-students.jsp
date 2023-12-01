<%@page import="com.luv2.jdbc.web.Student"%>
<%@ page import="java.util.*, com.luv2.jdbc.web.*" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Student Tracker App</title>	
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	
</head>
<!-- 
<%
	// get the students from the request object (sent by servlet)
	List<Student> theStudents = 
					(List<Student>) request.getAttribute("STUDENT_LIST");
 
           
pageContext.setAttribute("listStudent",theStudents);
%>

 -->

<body>

 
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
		
      <input type="button"  value="Add Student" onclick="window.location.href='add-student-form.jsp';return false;" 
      class="add-student-button"/>
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				
				<c:url var="templink" value="StudentControllerServlet">
				      <c:param name="command" value="LOAD"></c:param>  
				      <c:param name="studentId" value="${tempStudent.id}"></c:param>
				</c:url>
				
				
				<c:url var="deletelink" value="StudentControllerServlet">
				      <c:param name="command" value="DELETE"></c:param>  
				      <c:param name="studentId" value="${tempStudent.id}"></c:param>
				</c:url>
				
				
						<tr>
						<td>   ${tempStudent.getFirst_name() }  </td>
						<td>   ${tempStudent.getLast_name() }  </td>
						<td>   ${tempStudent.getEmail()}  </td>
						<td>
						 <a href="${templink }">Update</a>
						 |
						 <a href="${deletelink}" onclick="if(!(confirm('are you sure you want to delete this record'))) return false">Delete</a>
						 </td>
					</tr>
				
				
				</c:forEach>
				<!--  
				<% for (Student tempStudent : theStudents) { %>
				
					<tr>
						<td> <%= tempStudent.getFirst_name() %> </td>
						<td> <%= tempStudent.getLast_name() %> </td>
						<td> <%= tempStudent.getEmail() %> </td>
					</tr>
				
				<% } %>
				-->
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








