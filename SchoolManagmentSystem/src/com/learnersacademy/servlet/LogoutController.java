package com.learnersacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		RequestDispatcher rd=null;
		if(session==null) {
			rd=request.getRequestDispatcher("/login.html");
			rd.include(request, response);
			out.print("<center><span style='color:red'>Please login first.</span></center>");
			
		}
		else {
			session.invalidate();
			out.print("<center><h3>You have been logged out successfully.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
		}
	}

}
