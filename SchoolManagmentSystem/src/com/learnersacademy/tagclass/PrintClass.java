package com.learnersacademy.tagclass;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.learnersacademy.model.ClassObj;


public class PrintClass extends SimpleTagSupport{

	private List<ClassObj> classList;

	public void setClassList(List<ClassObj> classList) {
		this.classList = classList;
	}
	
	@Override
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		String headings[]= {"ID","Standard","Division"};
		
		out.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");
		out.print("<tr>");
		for (String heading : headings) {
			out.print("<th>" + heading + "</th>");
		}
		out.print("</tr>");
		
		for(ClassObj cls:classList)
		{	
			out.print("<tr>");
			out.print("<form action='classes' method='post'>");
			out.print("<td><input type='hidden' name='classId_delete' value='" +cls.getClassID() + "'/>"+cls.getClassID()+"</td>");
			out.print("<td>"+cls.getStandard()+"</td>");
			out.print("<td>"+cls.getDivision()+"</td>");
			out.print("<td><input type='submit' value='Delete'/></td>");
			out.print("</form>");
			out.print("<form action='UpdateClass' method='get'>");
			out.print("<td>"+"<input type='hidden' name='class_id' value='" 
			+cls.getClassID() 
			+"'/>"+"<input type='hidden' name='division' value='" +cls.getDivision() +"'/>"
			+"<input type='hidden' name='standard' value='" +cls.getStandard() 
			+"'/><input type='submit' value='Generate Report'/></td>");
			out.print("</form>");
			out.print("</tr>");
			
		}out.print("</table>");
	
		
		
		
	}

}
