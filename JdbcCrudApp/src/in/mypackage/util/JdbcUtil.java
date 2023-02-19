package in.mypackage.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class JdbcUtil {
	static Connection connection = null;
	static ResultSet resultSet = null;
	static PreparedStatement pstmt = null;
	
	private JdbcUtil() {}
	
	static{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public static Connection getJdbcConnection() throws SQLException, IOException{
		
		try {
			String url="jdbc:mysql://localhost:3306/practicedb";
			String username = "root";
			String password = "KMNK";
			connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connected db successfully\n");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	
	
}
