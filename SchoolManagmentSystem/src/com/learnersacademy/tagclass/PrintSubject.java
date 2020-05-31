package com.learnersacademy.tagclass;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.learnersacademy.model.SubjectObj;

public class PrintSubject extends SimpleTagSupport {
	
	private List<SubjectObj> sublist;

	public void setSublist(List<SubjectObj> sublist) {
		this.sublist = sublist;
	}

	@Override
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		String headings[]= {"ID","Name","Language","Class ID","Teacher ID"};
		
		out.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");
		out.print("<tr>");
		for (String heading : headings) {
			out.print("<th>" + heading + "</th>");
		}
		out.print("</tr>");
		
		for(SubjectObj sub:sublist)
		{	
			out.print("<tr>");
			out.print("<form action='subject' method='post'>");
			out.print("<td><input type='hidden' name='subjectId_delete' value='" + sub.getSubjectID() + "'/>"+sub.getSubjectID()+"</td>");
			out.print("<td>"+sub.getSubName()+"</td>");
			out.print("<td>"+sub.getSubLang()+"</td>");
			out.print("<td>"+sub.getClassId()+"</td>");
			out.print("<td>"+sub.getTeacherId()+"</td>");
			out.print("<td><input type='submit' value='Delete'/></td>");
			out.print("</tr>");
			out.print("</form>");
			
			out.print("<tr bgcolor='grey'>");
			out.print("<form action='UpdateSubject' method='get'>");
			out.print("<td><input type='text' name='id' value='" +sub.getSubjectID() + "' readonly/></td>");
			out.print("<td><input type='text' name='newName' placeholder='New Sub Name'></td>");
			out.print("<td><input type='text' name='newLang' placeholder='New Language'></td>");
			out.print("<td><input type='number' name='newClassId' placeholder='New Class ID'></td>");
			out.print("<td><input type='number' name='newTeacherId' placeholder='New Teacher ID'></td>");
			out.print("<td><input type='submit' value='Update'/></td>");
			out.print("</tr>");
			out.print("</form>");
		}
		out.print("</table>");
		
		
		
	}
}
