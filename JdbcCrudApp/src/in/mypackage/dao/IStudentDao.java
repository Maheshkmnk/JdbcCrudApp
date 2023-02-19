package in.mypackage.dao;


import in.mypackage.dto.Student;

public interface IStudentDao {
	
	public String addStudent(String sname, Integer sage, String saddress, Integer salary);
	
	public Student searchStudent(Integer sid);
	
	public String updateStudent(Student student);
	
	public String deleteStudent(Integer sid);
}
