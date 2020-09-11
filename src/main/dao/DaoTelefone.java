package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.entity.Endereco;
import main.entity.Telefone;
import main.repository.CNXJDBC;

public class DaoTelefone {

	private final String SQL_INSERE_TELEFONE = "INSERT INTO Telefone (Telefones, Tipo) VALUES (?,?);";
	
	private final String SQL_ALTERA_TELEFONE = "UPDATE Telefone SET Telefones = ?"
			+ "  WHERE Id_telefone = ?;";
	
	private final String SQL_EXCLUI_TELEFONE = "DELETE FROM Telefone"
			+ "  WHERE Id_telefone = ?;";
	
	private PreparedStatement pst = null;
	
	public long inserirTelefone(Telefone telefone) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_TELEFONE,Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, telefone.getTelefones());
			pst.setString(2, telefone.getTipo());
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
	
	public Telefone listarTelefone(Long id_telefone) {

		String SQL_SELECIONA_TELEFONE = "SELECT * FROM Telefone WHERE Id_telefone = "+id_telefone+";";
		
		Telefone telefone = new Telefone();
		
		try (Connection conn = new CNXJDBC().conexaoBanco();PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_TELEFONE); ResultSet rs = pst.executeQuery();) {
			
			while (rs.next()) {
				telefone.setId_telefone((rs.getInt("ID_TELEFONE")));
				telefone.setTelefones((rs.getString("TELEFONES")));
				telefone.setTipo(rs.getString("TIPO"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return telefone;
	}
	
	public void excluiTelefone(Long id_telefone) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_TELEFONE);) {
			pst.setLong(1, id_telefone);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
	}
	
    public void alterarTelefone(Telefone telefone) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_TELEFONE);) {
			pst.setString(1, telefone.getTelefones());
			pst.setLong(2, telefone.getId_telefone());
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
	}
}
