package in.mypackage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import in.mypackage.dto.Student;
import in.mypackage.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao  {
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement pstmt = null;
	
	
	@Override
	public String addStudent(String sname, Integer sage, String saddress, Integer salary) {
		
		String sqlInsertQuery = "insert into student(sname, sage, saddress, salary)values(?,?,?,?)";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
			}
			if(pstmt != null) {
				
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddress);
				pstmt.setInt(4, salary);
				
				int rowAffected = pstmt.executeUpdate();
				
				if(rowAffected == 1) {
					return "success";
				}
			}
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		Student student = null;
		String sqlSelectQuery = "select sid, sname, sage, saddress, salary from student where sid=?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlSelectQuery);
			}
			if(pstmt != null) {	
				pstmt.setInt(1, sid);
			}
			if(pstmt != null) {	
				resultSet = pstmt.executeQuery();
			}
			if(resultSet != null) {
				
				if(resultSet.next()) {
					student = new Student();
					
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddress(resultSet.getString(4));
					student.setSalary(resultSet.getInt(5));
					
					return student;
				}	
			}
						
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
			
		return student;
	}

	@Override
	public String updateStudent(Student student) {
		//Student student = null;
		String sqlUpdateQuery = "update student set sname=?, sage=?, saddress=?, salary=? where sid=?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
			if(pstmt != null) {	
				
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());
				pstmt.setInt(4, student.getSalary());
				pstmt.setInt(5, student.getSid());
				
				int rowAffected = pstmt.executeUpdate();
				
				System.out.println(rowAffected);
				
				if(rowAffected == 1){
					return "success";
				}
			}
						
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return "Failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "delete from student where sid=?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlDeleteQuery);
			}
			if(pstmt != null) {
				pstmt.setInt(1,	sid);
				
				int rowAffected = pstmt.executeUpdate();
				
				if(rowAffected == 1) {
					return "success";
				}
			}
			
		}catch(SQLException | IOException e) {
			
			e.printStackTrace();	
		}
		
		return "Failed";
	}

}
