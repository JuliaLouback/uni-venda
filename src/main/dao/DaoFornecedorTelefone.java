package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.entity.FornecedorTelefone;
import main.repository.CNXJDBC;

public class DaoFornecedorTelefone {
	
	private final String SQL_INSERE_FORNECEDOR_TELEFONE = "INSERT INTO Fornecedor_Telefone (Fornecedor_Cnpj,Telefone_Id_telefone) VALUES (?,?);";
	
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
}
