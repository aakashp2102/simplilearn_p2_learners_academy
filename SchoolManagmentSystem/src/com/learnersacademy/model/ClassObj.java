package com.learnersacademy.model;

public class ClassObj {
private Integer classID;
private Integer standard;
private String Division;


public ClassObj() {}


public Integer getClassID() {
	return classID;
}


public void setClassID(Integer classID) {
	this.classID = classID;
}


public Integer getStandard() {
	return standard;
}


public void setStandard(Integer standard) {
	this.standard = standard;
}


public String getDivision() {
	return Division;
}


public void setDivision(String division) {
	Division = division;
}


@Override
public String toString() {
	return "ClassObj [classID=" + classID + ", standard=" + standard + ", Division=" + Division + "]";
}







}
