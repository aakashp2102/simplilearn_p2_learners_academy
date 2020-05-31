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
 * Servlet implementation class UpdateSubject
 */
public class UpdateSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSubject() {
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
		SubjectObj subject=new SubjectObj();
		if(session!=null) {
			try {
				subject.setSubjectID(Integer.parseInt(request.getParameter("id")));
				subject.setSubName(request.getParameter("newName"));
				subject.setSubLang(request.getParameter("newLang"));
				if(!request.getParameter("newClassId").isEmpty()) {
				subject.setClassId(Integer.parseInt(request.getParameter("newClassId")));}
				else {subject.setClassId(0);}
				if(!request.getParameter("newTeacherId").isEmpty()) {
					subject.setTeacherId(Integer.parseInt(request.getParameter("newTeacherId")));}
					else {subject.setTeacherId(0);}
				boObj.updSubject(subject);
				List<SubjectObj> subList = boObj.listSubject();
				session.setAttribute("subList", subList);
				response.sendRedirect("viewSubject.jsp");
			} catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "subject");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "subject");
			}}
		else {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
		}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		LearnersAcadBOImpl boObj=new LearnersAcadBOImpl();
		SubjectObj subject=new SubjectObj();
		if(session!=null) {
			try {
				subject.setSubName(request.getParameter("subName"));
				subject.setSubLang(request.getParameter("subLang"));
				boObj.addSubject(subject);
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
