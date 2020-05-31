package com.learnersacademy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.OracleConnection;
import com.exception.BusinessException;
import com.learnersacademy.model.ClassObj;
import com.learnersacademy.model.StudentObj;
import com.learnersacademy.model.SubjectObj;
import com.learnersacademy.model.TeacherObj;

public class LearnersAcadDAOImpl implements LearnersAcadDAO{

	@Override
	public boolean userLogin(String username, String password) throws BusinessException {
		Boolean bool=false;
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="select 1 from learnersacademy.tab_user where username=? and password=?";
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			System.out.println(password);
			ResultSet result=preparedStatement.executeQuery();
			if(result.next()) {
				bool=true;
				return bool;
			}
			else {
				throw new BusinessException("Username and Password dont match"); 
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	}

	@Override
	public List<SubjectObj> listSubject() throws BusinessException {
		List<SubjectObj> subList=new ArrayList<SubjectObj>();
		
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="select sub_name,sub_id,sub_lang,class_id,teacher_id from learnersacademy.tab_subject";
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			
			ResultSet result=preparedStatement.executeQuery();
			
			while(result.next()) {
				SubjectObj subject=new SubjectObj();
				System.out.println(result.getInt("sub_id"));
				subject.setClassId(result.getInt("class_id"));
				subject.setSubjectID(result.getInt("sub_id"));
				subject.setSubLang(result.getString("sub_lang"));
				subject.setSubName(result.getString("sub_name"));
				subject.setTeacherId(result.getInt("teacher_id"));
				subList.add(subject);
			}
			
			return subList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	}

	@Override
	public List<ClassObj> listClasses() throws BusinessException {
		List<ClassObj> classList=new ArrayList<ClassObj>();
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="select class_id,division,std from learnersacademy.tab_class";
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			
			ResultSet result=preparedStatement.executeQuery();
			
			while(result.next()) {
				ClassObj cls=new ClassObj();
				
				cls.setClassID(result.getInt("class_id"));
				cls.setDivision(result.getString("division"));
				cls.setStandard(result.getInt("std"));
				classList.add(cls);
			}
			
			return classList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	}

	@Override
	public List<TeacherObj> listTeacher() throws BusinessException {
		List<TeacherObj> teacherList=new ArrayList<TeacherObj>();
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="select teacher_id,teacher_name,teacher_category,experience from learnersacademy.tab_teacher";
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			
			ResultSet result=preparedStatement.executeQuery();
			
			while(result.next()) {
				TeacherObj teacher=new TeacherObj();
				
				teacher.settID(result.getInt("teacher_id"));
				teacher.setTeacherCategory(result.getString("teacher_category"));
				teacher.setTeacherName(result.getString("teacher_name"));
				teacher.setExperience(result.getInt("experience"));
				
				teacherList.add(teacher);
					}
			
			return teacherList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	}

	@Override
	public List<StudentObj> listStudents() throws BusinessException {
		List<StudentObj> studentList=new ArrayList<StudentObj>();
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="select student_id,student_name,student_dob,class_id from learnersacademy.tab_student";
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			
			ResultSet result=preparedStatement.executeQuery();
			
			while(result.next()) {
				StudentObj student=new StudentObj();
				
				student.setStudentId(result.getInt("student_id"));
				student.setClassID(result.getInt("class_id"));
				student.setStudentDOB(result.getDate("student_dob"));
				student.setStudentName(result.getString("student_name"));
				
				studentList.add(student);
			
			}
			
			return studentList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	}

	@Override
	public boolean deleteSubject(Integer subjectID) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
		String sql="delete from learnersacademy.tab_subject where sub_id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, subjectID);
		int c=preparedStatement.executeUpdate();
		if(c!=1) {
			throw new BusinessException("Deletion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	}

	@Override
	public boolean deleteClass(Integer classID) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql = "{call la_update_record_pkg.del_class(?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setInt(1, classID);
		
			callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);

			callableStatement.execute();
			Integer success_code=callableStatement.getInt(3);
			String err_msg=callableStatement.getString(2);
		if(success_code!=1) {
			throw new BusinessException("Deletion Failed. "+err_msg);
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}

	@Override
	public boolean deleteTeacher(Integer teacherID) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql = "{call la_update_record_pkg.del_teacher(?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setInt(1, teacherID);
		
			callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);

			callableStatement.execute();
			Integer success_code=callableStatement.getInt(3);
			String err_msg=callableStatement.getString(2);
			
		if(success_code!=1) {
			throw new BusinessException("Deletion Failed ."+err_msg);
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}

	@Override
	public boolean deleteStudent(Integer studentID) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
		String sql="delete from learnersacademy.tab_student where student_id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, studentID);
		int c=preparedStatement.executeUpdate();
		if(c!=1) {
			throw new BusinessException("Deletion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}

	@Override
	public boolean addSubject(SubjectObj subject) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
		String sql="insert into learnersacademy.tab_subject (sub_name,sub_lang) values (?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, subject.getSubName());
		preparedStatement.setString(2, subject.getSubLang());
		int c=preparedStatement.executeUpdate();
		if(c!=1) {
			throw new BusinessException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}

	@Override
	public boolean addClass(ClassObj cls) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
		String sql="Insert into learnersacademy.tab_class (std,division) values(?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, cls.getStandard());
		preparedStatement.setString(2, cls.getDivision());
		int c=preparedStatement.executeUpdate();
		if(c!=1) {
			throw new BusinessException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}



	@Override
	public boolean addTeacher(TeacherObj teacher) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
		String sql="Insert into learnersacademy.tab_teacher(teacher_name,teacher_category,experience) values(?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, teacher.getTeacherName());
		preparedStatement.setString(2, teacher.getTeacherCategory());
		preparedStatement.setInt(3, teacher.getExperience());
		int c=preparedStatement.executeUpdate();
		if(c!=1) {
			throw new BusinessException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}


	@Override
	public boolean addStudent(StudentObj student) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		{
		String sql="Insert into learnersacademy.tab_student(student_name,student_dob) values(?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, student.getStudentName());
		preparedStatement.setDate(2, student.getStudentDOB());
		int c=preparedStatement.executeUpdate();
		if(c!=1) {
			throw new BusinessException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}

	@Override
	public boolean updSubject(SubjectObj subject) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call la_update_record_pkg.upd_subject(?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setInt(2, subject.getSubjectID());
			callableStatement.setString(1,subject.getSubName());
			callableStatement.setString(3,subject.getSubLang());
			callableStatement.setInt(4,subject.getClassId());
			callableStatement.setInt(5,subject.getTeacherId());

			callableStatement.registerOutParameter(7, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);

			callableStatement.execute();

			Integer success_code=callableStatement.getInt(7);
			String err_msg=callableStatement.getString(6);
			
			if(success_code==1) {
				return true;
			}
			else {
				throw new BusinessException(err_msg);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact sysadmin with error message "+e);
		}
		}
	

	
	@Override
	public boolean updTeacher(TeacherObj teacher) throws BusinessException {
		try(Connection connection=OracleConnection.getConnection())
		
		{
		String sql="update learnersacademy.tab_teacher tt set tt.teacher_name=nvl(?,tt.teacher_name),"
				+ "tt.teacher_category=nvl(?,tt.teacher_category),tt.experience=nvl(?,tt.experience) " + 
				"where tt.teacher_id=?";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, teacher.getTeacherName());
		
			preparedStatement.setString(2, teacher.getTeacherCategory());
		
		preparedStatement.setInt(3, teacher.getExperience());
		preparedStatement.setInt(4, teacher.gettID());
		
		int c=preparedStatement.executeUpdate();
		if(c!=1) {
			throw new BusinessException("Insertion Failed");
		}
		else {
			return true;
		}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}	}

	@Override
	public boolean updStudent(StudentObj student) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "{call la_update_record_pkg.upd_student(?,?,?,?,?,?)}";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setInt(1, student.getStudentId());
			callableStatement.setString(2,student.getStudentName());
			callableStatement.setDate(3, student.getStudentDOB() );
			callableStatement.setInt(4,student.getClassID());

			callableStatement.registerOutParameter(6, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);

			callableStatement.execute();

			Integer success_code=callableStatement.getInt(6);
			String err_msg=callableStatement.getString(5);
			
			if(success_code==1) {
				return true;
			}
			else {
				throw new BusinessException(err_msg);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured kindly contact sysadmin with error message "+e);
		}
		}

	@Override
	public List<Object> generateReport(ClassObj cls) throws BusinessException {
		List<Object> reportList=new ArrayList<Object>();
		
		
		try(Connection connection=OracleConnection.getConnection())
		{
			String sql="select sub_name,sub_id,sub_lang,class_id,teacher_id from learnersacademy.tab_subject where class_id=?";
			PreparedStatement  preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, cls.getClassID());
			ResultSet resultSubject=preparedStatement.executeQuery();
			
			
			while(resultSubject.next()) {
				SubjectObj subject=new SubjectObj();
				subject.setClassId(resultSubject.getInt("class_id"));
				subject.setSubjectID(resultSubject.getInt("sub_id"));
				subject.setSubLang(resultSubject.getString("sub_lang"));
				subject.setSubName(resultSubject.getString("sub_name"));
				subject.setTeacherId(resultSubject.getInt("teacher_id"));
				reportList.add(subject);
			}
			
			String sql2="select teacher_id,teacher_name,teacher_category,experience from learnersacademy.tab_teacher tt where "
					+ "tt.teacher_id in (select ts.teacher_id from learnersacademy.tab_subject ts where ts.class_id=?)";
			PreparedStatement  preparedStatement2=connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, cls.getClassID());
			ResultSet resultTeacher=preparedStatement2.executeQuery();
			
			while(resultTeacher.next()) {
				TeacherObj teacher=new TeacherObj();
				
				teacher.settID(resultTeacher.getInt("teacher_id"));
				teacher.setTeacherCategory(resultTeacher.getString("teacher_category"));
				teacher.setTeacherName(resultTeacher.getString("teacher_name"));
				teacher.setExperience(resultTeacher.getInt("experience"));
				reportList.add(teacher);
					}
			String sql3="select student_id,student_name,student_dob,class_id from learnersacademy.tab_student where class_id=?";
			PreparedStatement  preparedStatement3=connection.prepareStatement(sql3);
			preparedStatement3.setInt(1, cls.getClassID());
			ResultSet resultStudent=preparedStatement3.executeQuery();
			
			while(resultStudent.next()) {
				StudentObj student=new StudentObj();
				
				student.setStudentId(resultStudent.getInt("student_id"));
				student.setClassID(resultStudent.getInt("class_id"));
				student.setStudentDOB(resultStudent.getDate("student_dob"));
				student.setStudentName(resultStudent.getString("student_name"));
				reportList.add(student);
			
			}

			
			return reportList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message "+e);
		}
	}

}
