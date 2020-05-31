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
import com.learnersacademy.model.TeacherObj;

/**
 * Servlet implementation class UpdateTeacher
 */
public class UpdateTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTeacher() {
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
		TeacherObj teacher=new TeacherObj();
		if(session!=null) {
			try {
				teacher.settID(Integer.parseInt(request.getParameter("id")));
				teacher.setTeacherName(request.getParameter("newName"));
				teacher.setTeacherCategory(request.getParameter("newCategory"));
				if(request.getParameter("newExp").isEmpty()) {teacher.setExperience(0);}
				else {
				teacher.setExperience(Integer.parseInt(request.getParameter("newExp")));}
				boObj.updTeacher(teacher);
				List<TeacherObj> teacherList=boObj.listTeacher();
				session.setAttribute("teacherList", teacherList);
				response.sendRedirect("viewTeacher.jsp");
			} catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "teacher");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "teacher");
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
		TeacherObj teacher=new TeacherObj();
		if(session!=null) {
			try {
				teacher.setTeacherName(request.getParameter("teacherName"));
				teacher.setTeacherCategory(request.getParameter("teacherCategory"));
				if(request.getParameter("experience").isEmpty()) {
					teacher.setExperience(0);
				}else {
				teacher.setExperience(Integer.parseInt(request.getParameter("experience")));}
				boObj.addTeacher(teacher);
				List<TeacherObj> teacherList=boObj.listTeacher();
				session.setAttribute("teacherList", teacherList);
				response.sendRedirect("viewTeacher.jsp");
			} catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "teacher");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "teacher");
			}
		

					}
		else {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
		}
			}


}
