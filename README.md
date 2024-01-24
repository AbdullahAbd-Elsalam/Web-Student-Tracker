# Web-Student-Tracker
A web app developed to track the students enrolled to a university

This project is done as a part of the course from Udemy: JSP, Servlets and JDBC for Beginners: Build a Database App by Chad Darby (https://www.udemy.com/jsp-tutorial/learn/v4/)

This web app has been developed using the MVC framework to understand the basic concepts of JSP, Servlets, JDBC. 
The web app is run on Apache Tomcat server.

The login page is reached by going to the link: http://localhost:8080/web-student-tracker/
![GitHub Logo](/images/loginPage.png)
In the backend, the request is sent to a Servlet which sends the request to the ServletUtil class. The ServletUtil class uses JDBC connection pooling framework to fetch the student details from the database. The data is stored in an ArrayList of Student objects which is added as an attribute in the request object. The arraylist stored as an attribute is then iterated and printed in the jsp page, where the request is redirected to.


After that the following actions are coded and done.

1. Add a student
It is done by clicking on the button Add Student which redirects to a new JSP page which has a form. The information put in this form gets inserted into the request object as the request parameter. The action of the form is the servlet which uses the request parameters to get the data that is to be inserted in the database. Once the data has been successfully inserted, the request is redirected to the main page.

![GitHub Logo](/images/addStudent.png)
![GitHub Logo](/images/studentAdded.png)


2. Update a student
Here we do two things. First, we get the data of the student from the DB and prefill the form. And then we make edits as per our need and then submit the form.


![GitHub Logo](/images/updateStudent.png)
![GitHub Logo](/images/updatedStudent.png)


3. Delete a student
Here we delete the entry. A pop up comes up asking for confirmation. Once it is acknowledged, the delete query in the DB is run from the backend side using JDBC connection pool, thus deleting the entry.

![GitHub Logo](/images/deleteStudent.png)
![GitHub Logo](/images/deletedStudent.png)
