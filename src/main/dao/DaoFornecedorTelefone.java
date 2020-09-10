package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.Fornecedor;
import main.entity.FornecedorTelefone;
import main.repository.CNXJDBC;

public class DaoFornecedorTelefone {
	
	private final String SQL_INSERE_FORNECEDOR_TELEFONE = "INSERT INTO Fornecedor_Telefone (Fornecedor_Cnpj,Telefone_Id_telefone) VALUES (?,?);";
	
	private final String SQL_EXCLUI_FORNECEDOR_TELEFONE = "DELETE FROM Fornecedor_Telefone WHERE Telefone_Id_telefone = ?;";
	
	private PreparedStatement pst = null;

	public void inserirFornecedorTelefone(FornecedorTelefone fornecedor_telefone) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_FORNECEDOR_TELEFONE);) {
			pst.setString(1, fornecedor_telefone.getCnpj());
			pst.setString(2, Long.toString(fornecedor_telefone.getId_telefone()));
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
	
	
	public ArrayList<Long> listarFornecedorTelefone(String Cnpj) {
		
		ArrayList<Long> listaFornecedorTelefone = new ArrayList<Long>();
		String SQL_SELECIONA_FORNECEDOR_TELEFONE = "SELECT * FROM Fornecedor_Telefone WHERE Fornecedor_Cnpj = '"+Cnpj+"';";

		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_FORNECEDOR_TELEFONE); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				listaFornecedorTelefone.add(rs.getLong("TELEFONE_ID_TELEFONE"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaFornecedorTelefone;
	}
	
	public void excluirFornecedorTelefone(Long id_telefone) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_FORNECEDOR_TELEFONE);) {
			pst.setLong(1, id_telefone);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
}
}
