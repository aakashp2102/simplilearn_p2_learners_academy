<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.learnersacademy.model.ClassObj"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="pt" uri="WEB-INF/print_tags.tld" %>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Class</title>
</head>
<body>
 
<div style="text-align:centre">
<%
HttpSession mySession=request.getSession(false);
if(mySession==null){
	out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
	response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
}
else{
@SuppressWarnings("unchecked")
List<ClassObj> classList=(List<ClassObj>)mySession.getAttribute("classList");
if(classList !=null){
Date createTime = new Date(mySession.getCreationTime());
%>
<h1 style="Color:blue">Learner's Academy</h1><b style="Color:blue">You Logged in at <%=createTime %></b>
<table  style="text-align:center; margin-left:auto;">
<tr><td>&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;</td></tr>
<tr><td>&nbsp;&nbsp;<a href="/SchoolManagmentSystem/Dashboard.jsp">Dashboard</a>&nbsp;&nbsp;</td></tr>
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
<center><b>Class Details</b></center>
<br/>
<pt:printClass classList="<%=classList %>"/>
<br/>
<br/>
<form action="UpdateClass" method="post">
<table border="1" style="text-align:center; margin-left:auto;margin-right:auto;">
<tr>
<td>Standard </td><td><input type="number" name="standard" min="0" placeholder="standard"></td>
</tr>
<tr>
<td>Division </td><td><input type="text" name="division" placeholder="division"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" name="add" value="ADD CLASS"></td>
</tr>
</table>
</form>
</div>
<%@include file='footer.html' %>
<%} else{
response.sendRedirect("/SchoolManagmentSystem/Dashboard.jsp");
%>

<%}} %>

<%mySession.removeAttribute("exception");
mySession.removeAttribute("exceptionSys");%>
</body>
</html>