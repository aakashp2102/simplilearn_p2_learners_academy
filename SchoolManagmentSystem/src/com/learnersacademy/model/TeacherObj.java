package com.learnersacademy.model;

public class TeacherObj {
	private Integer tID;
	private String teacherName;
	private String teacherCategory;
	private Integer experience;
	
	
	public TeacherObj() {}

	public Integer gettID() {
		return tID;
	}
	public void settID(Integer tID) {
		this.tID = tID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherCategory() {
		return teacherCategory;
	}
	public void setTeacherCategory(String teacherCategory) {
		this.teacherCategory = teacherCategory;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "TeacherObj [tID=" + tID + ", teacherName=" + teacherName + ", teacherCategory=" + teacherCategory
				+ ", experience=" + experience + "]";
	}
	
	
	
	

}
