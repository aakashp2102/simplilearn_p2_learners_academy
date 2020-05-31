package com.learnersacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception.BusinessException;
import com.learnersacademy.bo.LearnersAcadBOImpl;
import com.learnersacademy.model.SubjectObj;

/**
 * Servlet implementation class ManageSubject
 */
public class ManageSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	HttpSession session=request.getSession(false);
	PrintWriter out=response.getWriter();
	LearnersAcadBOImpl boObj=new LearnersAcadBOImpl();
	if(session!=null) {
		try {
			List<SubjectObj> subList=boObj.listSubject();
			session.setAttribute("subList", subList);
			response.sendRedirect("viewSubject.jsp");
		} catch (BusinessException e) {
			session.setAttribute("exception", e.getMessage());
			response.sendRedirect( "Dashboard.jsp");}
		catch(Exception e1) {

			session.setAttribute("exceptionSys", e1.getMessage());
			response.sendRedirect( "Dashboard.jsp");
		}

	}
	else {
		out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
		response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		LearnersAcadBOImpl boObj=new LearnersAcadBOImpl();
		if(session!=null) {
			try {
				Integer subjectId = Integer.parseInt(request.getParameter("subjectId_delete"));
				boObj.deleteSubject(subjectId);
				List<SubjectObj> subList = boObj.listSubject();
				session.setAttribute("subList", subList);
				response.sendRedirect("viewSubject.jsp");
			} catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "subject");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "subject");
			}
					}
		else {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
		}
			}

}
