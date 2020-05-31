package com.learnersacademy.model;

public class SubjectObj {


private Integer subjectID;
private String subName;
private String subLang;
private Integer classId;
private Integer teacherId;

public SubjectObj() {}

public Integer getSubjectID() {
	return subjectID;
}

public void setSubjectID(Integer subjectID) {
	this.subjectID = subjectID;
}

public String getSubName() {
	return subName;
}

public void setSubName(String subName) {
	this.subName = subName;
}

public String getSubLang() {
	return subLang;
}

public void setSubLang(String subLang) {
	this.subLang = subLang;
}

public Integer getClassId() {
	return classId;
}

public void setClassId(Integer classId) {
	this.classId = classId;
}

public Integer getTeacherId() {
	return teacherId;
}

public void setTeacherId(Integer teacherId) {
	this.teacherId = teacherId;
}

@Override
public String toString() {
	return "SubjectObj [subjectID=" + subjectID + ", subName=" + subName + ", subLang=" + subLang + ", classId="
			+ classId + ", teacherId=" + teacherId + "]";
}


}
