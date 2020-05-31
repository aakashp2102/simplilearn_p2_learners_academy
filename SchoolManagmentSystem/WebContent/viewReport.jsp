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
<title>View Class Report</title>
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
ClassObj cls=(ClassObj)mySession.getAttribute("class");
@SuppressWarnings("unchecked")
List<Object> reportList=(List<Object>)mySession.getAttribute("reportList");
if(reportList !=null){
Date createTime = new Date(mySession.getCreationTime());
%>
<h1 style="Color:blue">Learner's Academy</h1><b style="Color:blue">You Logged in at <%=createTime %></b>
<table  style="text-align:center; margin-left:auto;">
<tr><td>&nbsp;&nbsp;<a href="logout">logout</a>&nbsp;&nbsp;</td></tr>
<tr><td>&nbsp;&nbsp;<a href="/SchoolManagmentSystem/Dashboard.jsp">Dashboard</a>&nbsp;&nbsp;</td></tr>
</table>
<br/>
<br/>
<h3 style="text-align:center"> - - - Class Report - - - </h3>
<h3 style="text-align:center">Id: <%=cls.getClassID() %> Standard: <%=cls.getStandard() %> Division: <%=cls.getDivision() %></h3>

<pt:printReport reportList="<%=reportList %>"/>
<br/>
<br/>
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