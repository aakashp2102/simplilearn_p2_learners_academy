package com.learnersacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class UpdateStudent
 */
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudent() {
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
		StudentObj student=new StudentObj();
		if(session!=null) {
			try {
				student.setStudentName(request.getParameter("newName"));
				if(!request.getParameter("newDOB").isEmpty()) {
				Date date=new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("newDOB")).getTime());
				student.setStudentDOB((date));}
				student.setStudentId(Integer.parseInt(request.getParameter("id")));
				if(!request.getParameter("newClassId").isEmpty()) {
				student.setClassID(Integer.parseInt(request.getParameter("newClassId")));}
				else {student.setClassID(0);}
				boObj.updStudent(student);
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
		StudentObj student=new StudentObj();
		if(session!=null) {
			try {
				student.setStudentName(request.getParameter("studentName"));
				if(!request.getParameter("studentDoB").isEmpty()) {
				Date date=new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("studentDoB")).getTime());
				student.setStudentDOB((date));}
				boObj.addStudent(student);
				List<StudentObj> studentList=boObj.listStudents();
				session.setAttribute("studentList", studentList);
				response.sendRedirect("viewStudent.jsp");
			}catch (BusinessException e) {
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
		}
			}

	}


