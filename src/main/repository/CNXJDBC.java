package main.repository;
import java.sql.*;

public class CNXJDBC {

	
	public Connection conexaoBanco() {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=uni_venda;";
		
		try {
			
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			return DriverManager.getConnection(connectionUrl,"sa", "juliaerikajulio");
			
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	
}
