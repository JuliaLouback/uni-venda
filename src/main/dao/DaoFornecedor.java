package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.Fornecedor;
import main.repository.CNXJDBC;

public class DaoFornecedor {

	private final String SQL_INSERE_FORNECEDOR = "INSERT INTO Fornecedor (Cnpj,Nome_empresa,Email,Inscricao_estadual,Inscricao_municipal,"
			+ "Endereco_Id_endereco) VALUES (?,?,?,?,?,?);";
	
	private final String SQL_SELECIONA_FORNECEDOR = "SELECT * FROM Fornecedor";
	
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
	
	public ArrayList<Fornecedor> listarFornecedores() {
		ArrayList<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();

		Fornecedor fornecedor;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_FORNECEDOR); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				fornecedor = new Fornecedor();
				fornecedor.setCnpj(rs.getString("CNPJ"));
				fornecedor.setEmail(rs.getString("EMAIL"));
				fornecedor.setNome_empresa(rs.getString("NOME_EMPRESA"));
				fornecedor.setInscricao_estadual(rs.getLong("INSCRICAO_ESTADUAL"));
				fornecedor.setInscricao_municipal(rs.getLong("INSCRICAO_MUNICIPAL"));
				fornecedor.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				listaFornecedores.add(fornecedor);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaFornecedores;
	}
}
