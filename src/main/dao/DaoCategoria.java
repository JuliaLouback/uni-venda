package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.Categoria;
import main.entity.Categoria;
import main.entity.Fornecedor;
import main.repository.CNXJDBC;
import main.util.ShowAlert;

public class DaoCategoria {

	private final String SQL_SELECIONA_CATEGORIA = "SELECT * FROM Categoria";

	private final String SQL_INSERE_CATEGORIA = "INSERT INTO Categoria (Nome) VALUES (?);";
		
	private final String SQL_ALTERA_CATEGORIA = "UPDATE Categoria SET Nome =? WHERE Id_categoria = ?;";
	
	private final String SQL_EXCLUI_CATEGORIA = "DELETE FROM Categoria  WHERE Id_categoria = ?;";
	
	public ArrayList<Categoria> listarCategoria() {
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();

		Categoria Categoria;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CATEGORIA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Categoria = new Categoria();
				Categoria.setId_categoria(rs.getInt("ID_CATEGORIA"));
				Categoria.setNome(rs.getString("NOME"));
				listaCategorias.add(Categoria);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaCategorias;
	}
	
	
	public Categoria listarCategoria(long id) {
			
		Categoria Categoria = new Categoria();
		String SQL_SELECIONA_CATEGORIA_ID = "SELECT * FROM Categoria WHERE Id_categoria = '"+id+"';";

		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CATEGORIA_ID); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Categoria.setId_categoria(rs.getInt("ID_CATEGORIA"));
				Categoria.setNome(rs.getString("NOME"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
			
		return Categoria;
	}
	 
	public boolean inserirCategoria(Categoria Categoria) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_CATEGORIA);) {
			pst.setString(1, Categoria.getNome());
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Categoria já cadastrada!");
		}
		
		return false;
	}
			
	public boolean alterarCategoria(Categoria Categoria) {
			
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_CATEGORIA);) {
			pst.setString(1, Categoria.getNome());
			pst.setInt(2, Categoria.getId_categoria());
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Categoria já cadastrada!");
		}
		
		return false;
	}
	
	public boolean excluirCategoria(int id) {
			
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_CATEGORIA);) {
			pst.setInt(1, id);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Categoria não pode ser excluída porque esta associada a um ou vários Produtos!");
		}
		
		return false;
	}
}
