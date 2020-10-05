package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.FuncionarioTelefone;
import main.repository.CNXJDBC;

public class DaoFuncionarioTelefone {
	
	private final String SQL_INSERE_FUNCIONARIO_TELEFONE = "INSERT INTO Funcionario_Telefone (Funcionario_Cpf,Telefone_Id_telefone) VALUES (?,?);";
	
	private final String SQL_EXCLUI_FUNCIONARIO_TELEFONE = "DELETE FROM Funcionario_Telefone WHERE Telefone_Id_telefone = ?;";
	
	private PreparedStatement pst = null;

	public void inserirFuncionarioTelefone(FuncionarioTelefone funcionario_telefone) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_FUNCIONARIO_TELEFONE);) {
			pst.setString(1, funcionario_telefone.getCpf());
			pst.setString(2, Long.toString(funcionario_telefone.getId_telefone()));
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
	
	
	public ArrayList<Long> listarFuncionarioTelefone(String Cpf) {
		
		ArrayList<Long> listaFuncionarioTelefone = new ArrayList<Long>();
		String SQL_SELECIONA_Funcionario_TELEFONE = "SELECT * FROM Funcionario_Telefone WHERE Funcionario_Cpf = '"+Cpf+"';";

		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_Funcionario_TELEFONE); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				listaFuncionarioTelefone.add(rs.getLong("TELEFONE_ID_TELEFONE"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaFuncionarioTelefone;
	}
	
	public void excluirFuncionarioTelefone(Long id_telefone) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_FUNCIONARIO_TELEFONE);) {
			pst.setLong(1, id_telefone);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
}
