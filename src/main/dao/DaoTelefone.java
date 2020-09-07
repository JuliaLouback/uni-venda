package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.entity.Telefone;
import main.repository.CNXJDBC;

public class DaoTelefone {

	private final String SQL_INSERE_TELEFONE = "INSERT INTO Telefone (Telefones) VALUES (?);";
	
	private PreparedStatement pst = null;
	
	public long inserirTelefone(Telefone telefone) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_TELEFONE,Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, Long.toString(telefone.getTelefones()));
			pst.executeUpdate();
			
			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
		return 0;
	}
}
