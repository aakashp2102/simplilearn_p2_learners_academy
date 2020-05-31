package com.learnersacademy.tagclass;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.learnersacademy.model.TeacherObj;

public class PrintTeacher extends SimpleTagSupport{

	private List<TeacherObj> teacherList;

	public void setTeacherList(List<TeacherObj> teacherList) {
		this.teacherList = teacherList;
	}
	
	@Override
	public void doTag() throws IOException{
		JspWriter out = getJspContext().getOut();
		String headings[]= {"ID","Name","Category", "Experience"};
		
		out.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");
		out.print("<tr>");
		for (String heading : headings) {
			out.print("<th>" + heading + "</th>");
		}
		out.print("</tr>");
		
		for(TeacherObj tch:teacherList)
		{	
			out.print("<tr>");
			out.print("<form action='teacher' method='post'>");
			out.print("<td><input type='hidden' name='teacherId_delete' value='" +tch.gettID() + "'/>"+tch.gettID()+"</td>");
			out.print("<td>"+tch.getTeacherName()+"</td>");
			out.print("<td>"+tch.getTeacherCategory()+"</td>");
			out.print("<td>"+tch.getExperience()+"</td>");
			out.print("<td><input type='submit' value='Delete'/></td>");
			out.print("</tr>");
			out.print("</form>");
			
			out.print("<tr bgcolor='grey'>");
			out.print("<form action='UpdateTeacher' method='get'>");
			out.print("<td><input type='text' name='id' value='" +tch.gettID() + "' readonly/></td>");
			out.print("<td><input type='text' name='newName' placeholder='New Teacher Name'></td>");
			out.print("<td><input type='text' name='newCategory' placeholder='New Category'></td>");
			out.print("<td><input type='text' name='newExp' value='"+tch.getExperience()+"'placeholder='New Experience'></td>");
			out.print("<td><input type='submit' value='Update'/></td>");
			out.print("</tr>");
			out.print("</form>");
			
		}
		out.print("</table>");

		
		
		
	}
}
