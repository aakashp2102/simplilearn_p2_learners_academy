package com.learnersacademy.model;

import java.sql.Date;

public class StudentObj {
private Integer studentId;
private String studentName;
private Date studentDOB;
private Integer classID;



public StudentObj() {}


public Integer getStudentId() {
	return studentId;
}
public void setStudentId(Integer studentId) {
	this.studentId = studentId;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public Date getStudentDOB() {
	return studentDOB;
}
public void setStudentDOB(Date studentDOB) {
	this.studentDOB = studentDOB;
}
public Integer getClassID() {
	return classID;
}
public void setClassID(Integer classID) {
	this.classID = classID;
}


@Override
public String toString() {
	return "StudentObj [studentId=" + studentId + ", studentName=" + studentName + ", studentDOB=" + studentDOB
			+ ", classID=" + classID + "]";
}



}
