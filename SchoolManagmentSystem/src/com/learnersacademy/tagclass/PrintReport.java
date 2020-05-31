package com.learnersacademy.tagclass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.learnersacademy.model.StudentObj;
import com.learnersacademy.model.SubjectObj;
import com.learnersacademy.model.TeacherObj;

public class PrintReport extends SimpleTagSupport {
	private List<Object> reportList;

	public void setReportList(List<Object> reportList) {
		this.reportList = reportList;
	}
	
	@Override
	public void doTag() throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		JspWriter out = getJspContext().getOut();
		String headings[]= {"ID","Name","Date of Birth"};
		out.print("<div style='text-align:center'>");
		out.print("<b>Student Details</b>");
		out.print("<br/>");
		out.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");		
		out.print("<tr>");
		for (String heading : headings) {
			out.print("<th>" + heading + "</th>");
		}
		out.print("</tr>");
		
		for(Object studObj:reportList)
		{	
			if(studObj.getClass()==StudentObj.class) {
			StudentObj std=(StudentObj)studObj;
			out.print("<tr>");
			out.print("<form action='student' method='post'>");
			out.print("<td>" +std.getStudentId() + "</td>");
			out.print("<td>"+std.getStudentName()+"</td>");
			out.print("<td>"+format.format(std.getStudentDOB())+"</td>");
			out.print("<tr>");
			}
		}
		out.print("</table>");

		out.print("<br/>");

		out.print("<br/>");
		out.print("<b>Subject Details</b>");
		out.print("<br/>");
		
		
		String headings2[]= {"ID","Name","Language","Teacher ID"};
		
		out.print("<table border='1px' style='text-align:center;  margin-left:auto;margin-right:auto;'>");
		out.print("<tr>");
		for (String heading : headings2) {
			out.print("<th>" + heading + "</th>");
		}
		out.print("</tr>");
		
		for(Object subObj:reportList)
			{	
			if(subObj.getClass()==SubjectObj.class) {
				SubjectObj sub=(SubjectObj)subObj;
			out.print("<tr>");
			out.print("<form action='subject' method='post'>");
			out.print("<td>"+sub.getSubjectID() +"</td>");
			out.print("<td>"+sub.getSubName()+"</td>");
			out.print("<td>"+sub.getSubLang()+"</td>");
			out.print("<td>"+sub.getTeacherId()+"</td>");
			out.print("<tr>");
			}
		}

				out.print("</table>");
		
				out.print("<br/>");
		
		out.print("<br/>");
		
		
		out.print("<b>Teacher Details</b>");
		out.print("<br/>");
String headings3[]= {"ID","Name","Category", "Experience"};
		
		out.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");
		out.print("<tr>");
		for (String heading : headings3) {
			out.print("<th>" + heading + "</th>");
		}
		out.print("</tr>");
		
		for(Object tchObj:reportList)
		{	
			if(tchObj.getClass()==TeacherObj.class) {
			TeacherObj tch=(TeacherObj)tchObj;
			out.print("<tr>");
			out.print("<form action='teacher' method='post'>");
			out.print("<td>" +tch.gettID() +"</td>");
			out.print("<td>"+tch.getTeacherName()+"</td>");
			out.print("<td>"+tch.getTeacherCategory()+"</td>");
			out.print("<td>"+tch.getExperience()+"</td>");
			out.print("<tr>");
			}
		}
		out.print("</table>");
		
		out.print("</div>");
	}


}
