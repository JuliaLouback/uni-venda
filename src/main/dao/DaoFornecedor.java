package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.entity.Fornecedor;
import main.repository.CNXJDBC;

public class DaoFornecedor {

	private final String SQL_INSERE_FORNECEDOR = "INSERT INTO Fornecedor (Cnpj,Nome_empresa,Email,Inscricao_estadual,Inscricao_municipal,"
			+ "Endereco_Id_endereco) VALUES (?,?,?,?,?,?);";
	
	private PreparedStatement pst = null;

	public void inserirFornecedor(Fornecedor fornecedor) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_FORNECEDOR);) {
			pst.setString(1, fornecedor.getCnpj());
			pst.setString(2, fornecedor.getNome_empresa());
			pst.setString(3, fornecedor.getEmail());
			pst.setString(4, Long.toString(fornecedor.getInscricao_estadual()));
			pst.setString(5, Long.toString(fornecedor.getInscricao_municipal()));
			pst.setString(6, Long.toString(fornecedor.getId_endereco()));
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
}
