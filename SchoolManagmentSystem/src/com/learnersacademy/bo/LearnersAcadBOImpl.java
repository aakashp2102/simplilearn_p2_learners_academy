package com.learnersacademy.bo;

import java.util.List;

import com.exception.BusinessException;
import com.learnersacademy.dao.LearnersAcadDAOImpl;
import com.learnersacademy.model.ClassObj;
import com.learnersacademy.model.StudentObj;
import com.learnersacademy.model.SubjectObj;
import com.learnersacademy.model.TeacherObj;

public class LearnersAcadBOImpl implements LearnersAcadBO{

	@Override
	public boolean userLogin(String username, String password) throws BusinessException {
		boolean bool=false; 
		//System.out.println("user "+username+username.isEmpty()+" pwd "+password+password.isEmpty());
		if(!username.isEmpty() && !password.isEmpty()){
			if(username.matches("^[A-Za-z0-9._]+$") && password.matches("^[A-Za-z0-9]+$")) {
				LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
				daoObject.userLogin(username, password);
				bool=true;
				return bool;
			}
			else {throw new BusinessException("Invalid Credentials");}
			
		}
		else {throw new BusinessException("Username or Password Cannot be Null");}
		
	}

	@Override
	public List<SubjectObj> listSubject() throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		List<SubjectObj> subjectList=daoObject.listSubject();
		return subjectList;
	}

	@Override
	public List<ClassObj> listClasses() throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		List<ClassObj> classList=daoObject.listClasses();
		return classList;	
		}

	@Override
	public List<TeacherObj> listTeacher() throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		List<TeacherObj> teacherList=daoObject.listTeacher();
		return teacherList;	
	}

	@Override
	public List<StudentObj> listStudents()  throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		List<StudentObj> studentList=daoObject.listStudents();
		return studentList;	
	}

	@Override
	public boolean deleteSubject(Integer subjectID) throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		return daoObject.deleteSubject(subjectID);
	
	}

	@Override
	public boolean deleteClass(Integer classID) throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		return daoObject.deleteClass(classID);	
		}

	@Override
	public boolean deleteTeacher(Integer teacherID) throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		return daoObject.deleteTeacher(teacherID);	
			}

	@Override
	public boolean deleteStudent(Integer studentID) throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		return daoObject.deleteStudent(studentID);	
			}

	@Override
	public boolean addSubject(SubjectObj subject) throws BusinessException {
		if(subject.getSubName().isEmpty()||subject.getSubLang().isEmpty()) {
			throw new BusinessException("Subject Name or Language Cannot be Empty");
		}
		else {
			if(subject.getSubName().matches("^[A-Za-z\\S]+$")) {
				
				if(subject.getSubLang().matches("^[A-Za-z]+$")) {
					LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
					return daoObject.addSubject(subject);
				
				}
				else {
					throw new BusinessException("Subject Language should only have alphabets, No White Spaces Allowed");
				}

			}
			else {
				throw new BusinessException("Subject Name should only have alphabets and spaces");
			}
			
		}
	}

	@Override
	public boolean addClass(ClassObj cls) throws BusinessException {
		if(cls.getStandard()==null || cls.getDivision().isEmpty()) {
			throw new BusinessException("Subject Name or Language Cannot be Empty");
		}
		else {
			if(cls.getStandard()>0||cls.getStandard()<12) {
				
				if(cls.getDivision().matches("[A-D]{1}")) {
					LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
					return daoObject.addClass(cls);
				
				}
				else {
					throw new BusinessException("Pls choose a division from A,B,C,D");
				}

			}
			else {
				throw new BusinessException("Standard Should be betweeen 1 and 12");
			}
			
		}
	}

	@Override
	public boolean addTeacher(TeacherObj teacher) throws BusinessException {
		if(teacher.getTeacherName().isEmpty()||teacher.getTeacherCategory().isEmpty()||teacher.getExperience()==null) {
			throw new BusinessException("Teacher Name, Category or Experience Cannot be Empty");
		}
		else {
			if(teacher.getTeacherName().matches("^[A-Za-z\\s]+$")) {
				
				if(teacher.getTeacherCategory().matches("^[A-Za-z]+$")) {
					LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
					return daoObject.addTeacher(teacher);
				
				}
				else {
					throw new BusinessException("Teacher Category should only have alphabets, No White Spaces Allowed");
				}

			}
			else {
				throw new BusinessException("Teacher Name should only have alphabets and spaces");
			}
			
		}
	}

	@Override
	public boolean addStudent(StudentObj student) throws BusinessException {
		if(student.getStudentName().isEmpty()|| student.getStudentDOB()==null) {
			throw new BusinessException("Student Name or DoB Cannot be Empty");
		}
		else {
			if(student.getStudentName().matches("^[A-Za-z\\S]+$")) {
				
					LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
					return daoObject.addStudent(student);
							
			}
			else {
				throw new BusinessException("Teacher Name should only have alphabets and spaces");
			}
			
		}

	}

	@Override
	public boolean updSubject(SubjectObj subject) throws BusinessException {
		if(subject.getSubName().matches("^[A-Za-z\\s]+$")||subject.getSubName().isEmpty()) {
			
			if(subject.getSubLang().matches("^[A-Za-z]+$")||subject.getSubLang().isEmpty()) {
				LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
				return daoObject.updSubject(subject);
			
			}
			else {
				throw new BusinessException("Subject Language should only have alphabets, No White Spaces Allowed");
			}

		}
		else {
			throw new BusinessException("Subject Name should only have alphabets and spaces");
		}

	}

	
	@Override
	public boolean updTeacher(TeacherObj teacher) throws BusinessException {
		if(teacher.getTeacherName().isEmpty()&&teacher.getTeacherCategory().isEmpty()&&teacher.getExperience()==null) {
			throw new BusinessException("Teacher Name, Category and Experience Cannot be Empty. No parameter to be updated");
		}
		else {
			if(teacher.getTeacherName().matches("^[A-Za-z\\s]+$")||teacher.getTeacherName().isEmpty()) {
				
				if(teacher.getTeacherCategory().matches("^[A-Za-z]+$")||teacher.getTeacherCategory().isEmpty()) {
					LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
					return daoObject.updTeacher(teacher);
				
				}
				else {
					throw new BusinessException("Teacher Category should only have alphabets, No White Spaces Allowed");
				}

			}
			else {
				throw new BusinessException("Teacher Name should only have alphabets and spaces");
			}
			
		}
	}

	@Override
	public boolean updStudent(StudentObj student) throws BusinessException {
		if(student.getStudentName().isEmpty() && student.getStudentDOB()==null && student.getClassID()==null) {
			throw new BusinessException("Student Name, DoB and ClassID all Cannot be Empty. no updation required.");
		}
		else {
			if(student.getStudentName().matches("^[A-Za-z\\s]+$") ||student.getStudentName().isEmpty()) {
				
					LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
					return daoObject.updStudent(student);
							
			}
			else {
				throw new BusinessException("Student Name should only have alphabets and spaces");
			}
			
		}
	}

	@Override
	public List<Object> generateReport(ClassObj cls) throws BusinessException {
		LearnersAcadDAOImpl daoObject=new LearnersAcadDAOImpl();
		return daoObject.generateReport(cls);
	}

}
