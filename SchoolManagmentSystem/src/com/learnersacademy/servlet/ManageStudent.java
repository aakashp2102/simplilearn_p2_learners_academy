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
import com.learnersacademy.model.StudentObj;

/**
 * Servlet implementation class ManageStudent
 */
public class ManageStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageStudent() {
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
				List<StudentObj> studentList=boObj.listStudents();
				session.setAttribute("studentList", studentList);
				response.sendRedirect("viewStudent.jsp");
			}catch (BusinessException e) {
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
				Integer studentId = Integer.parseInt(request.getParameter("studentId_delete"));
				boObj.deleteStudent(studentId);
				List<StudentObj> studentList=boObj.listStudents();
				session.setAttribute("studentList", studentList);
				response.sendRedirect("viewStudent.jsp");
			} catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "student");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "student");
			}
			
		}
		else {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
		}	}

}
