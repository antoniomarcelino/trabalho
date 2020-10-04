package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	private static Connection con;
      
	private ConexaoBD () {
	}
	
	public static Connection obterConexao() {
		if(con == null)
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				con =  DriverManager.getConnection(""
						+ "jdbc:mysql://localhost/sge?useTimezone=true&serverTimezone=UTC", "root", "");
			} catch (SQLException | ClassNotFoundException e) {
				
				throw new RuntimeException(e);
			}
		
		return con;
	}
}
