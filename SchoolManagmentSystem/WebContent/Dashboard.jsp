<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Learners Academy</title>
</head>
<body>

<%
HttpSession mySession=request.getSession(false);
if(mySession==null){
	out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
	response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
}
else{
	Date createTime = new Date(mySession.getCreationTime());
%>
<h1 style="Color:blue">Learner's Academy</h1><b style="Color:blue">You Logged in at <%=createTime %></b>
<div style="text-align:center;">
<h2>Welcome to your Dashboard
</h2>

<table  style="text-align:center; margin-left:auto;">
<tr><td>&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;</td></tr>
</table>
<br/>

<div style="color:red;text-align: center; border: 1px outset red;
  background-color: lightblue;" >
<%if(mySession.getAttribute("exception")!=null) {%>
<%=mySession.getAttribute("exception") %>
<%}if(mySession.getAttribute("exceptionSys")!=null) {%>
System Error <%=mySession.getAttribute("exceptionSys") %>
<%} %>
</div>
<br/>
 <table border="1" style="text-align:center; margin-left:auto;margin-right:auto;">
<tr><td>&nbsp;&nbsp;<a href="student">manage students</a>&nbsp;&nbsp;</td><td> Get a list of all students
<br/>Add New Students
<br/>Update Student Details
<br/>Delete an existing student</td></tr>
<tr><td>&nbsp;&nbsp;<a href="classes">manage classes</a>&nbsp;&nbsp;</td><td> Get a list of all Classes
<br/>Add New Class
<br/>Delete an existing Class
<br/>Generate Class Report</td></tr>
<tr><td>&nbsp;&nbsp;<a href="teacher">manage teachers</a>&nbsp;&nbsp;</td><td> Get a list of all Teachers
<br/>Add New Teachers
<br/>Update Teacher Details
<br/>Delete an existing Teacher</td></tr>
<tr><td>&nbsp;&nbsp;<a href="subject">manage subjects</a>&nbsp;&nbsp;</td><td> Get a list of all Subjects
<br/>Add New Subjesct
<br/>Update Subject Details, Assign a Teacher, Designate a Class
<br/>Delete an existing Subject</td></tr>
</table>

</div>
<%@include file='footer.html' %>
<%} %>
<%mySession.removeAttribute("exception");
mySession.removeAttribute("exceptionSys");%>
</body>
</html>
