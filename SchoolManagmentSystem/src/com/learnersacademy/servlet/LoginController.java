package com.learnersacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception.BusinessException;
import com.learnersacademy.bo.LearnersAcadBOImpl;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		RequestDispatcher rd = null;
		
		LearnersAcadBOImpl boObject=new LearnersAcadBOImpl();
		try {
			if(boObject.userLogin(username, password)) {
				HttpSession session=request.getSession();
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(60);
				response.sendRedirect("Dashboard.jsp");
//				rd=request.getRequestDispatcher("Dashboard.jsp");
//				rd.forward(request, response);
			}
		} catch (BusinessException e) {
			rd=request.getRequestDispatcher("login.html");
			//out.print(username+" "+password);
		rd.include(request, response);	
		out.print("<center><span style='color:red'>"+e.getMessage()+"</span></center>");
		}
		
	}

}
