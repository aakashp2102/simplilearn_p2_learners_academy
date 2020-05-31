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
import com.learnersacademy.model.ClassObj;

/**
 * Servlet implementation class UpdateClass
 */
public class UpdateClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClass() {
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
		ClassObj cls=new ClassObj();
		if(session!=null) {
			cls.setClassID(Integer.parseInt(request.getParameter("class_id")));
			cls.setStandard(Integer.parseInt(request.getParameter("standard")));
			cls.setDivision(request.getParameter("division"));
			try {
				List<Object> reportList=boObj.generateReport(cls);
				session.setAttribute("reportList", reportList);
				session.setAttribute("class", cls);
				response.sendRedirect("viewReport.jsp");
			}  catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "classes");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "classes");
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
		ClassObj cls=new ClassObj();
		if(session!=null) {
			try {
				if(!request.getParameter("standard").isEmpty())
					{cls.setStandard(Integer.parseInt(request.getParameter("standard")));}
				if(!request.getParameter("division").isEmpty()) {
					cls.setDivision(request.getParameter("division"));
				}
				
				boObj.addClass(cls);
				List<ClassObj> classList=boObj.listClasses();
				session.setAttribute("classList", classList);
				response.sendRedirect("viewClass.jsp");
			} catch (BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect( "classes");}
			catch(Exception e1) {

				session.setAttribute("exceptionSys", e1.getMessage());
				response.sendRedirect( "classes");
			}
				
		}
		else {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/SchoolManagmentSystem'");
		}
			}

	}


