package main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.Funcionario;
import main.repository.CNXJDBC;

public class DaoFuncionario {
	private final String SQL_INSERE_FUNCIONARIO = "INSERT INTO Funcionario (Cpf,Nome,Email,Cargo,Data_nascimento,Status,Salario,"
			+ "Endereco_Id_endereco) VALUES (?,?,?,?,?,?,?,?);";
	
	private final String SQL_SELECIONA_FUNCIONARIO = "SELECT * FROM Funcionario";
	
	private final String SQL_ALTERA_FUNCIONARIO = "UPDATE Funcionario SET Nome=?, Email = ?, Cargo = ?, Data_nascimento = ?, Status = ?, Salario = ?"
			+ "  WHERE Cpf = ?;";
	
	private final String SQL_EXCLUI_FUNCIONARIO = "DELETE FROM Funcionario  WHERE Cpf = ?;";
		
	private PreparedStatement pst = null;

	public void inserirFuncionario(Funcionario funcionario) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_FUNCIONARIO);) {
			pst.setString(1, funcionario.getCpf());
			pst.setString(2, funcionario.getNome());
			pst.setString(3, funcionario.getEmail());
			pst.setString(4, funcionario.getCargo());
			pst.setDate(5, Date.valueOf(funcionario.getData_nascimento()));
			pst.setString(6, funcionario.getStatus());
			pst.setFloat(7, funcionario.getSalario());
			pst.setString(8, Long.toString(funcionario.getId_endereco()));
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
	
	public ArrayList<Funcionario> listarFuncionarios() {
		ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		Funcionario funcionario;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_FUNCIONARIO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setEmail(rs.getString("EMAIL"));
				funcionario.setCargo(rs.getString("CARGO"));
				funcionario.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				funcionario.setStatus(rs.getString("STATUS"));
				funcionario.setSalario(rs.getFloat("SALARIO"));
				funcionario.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				listaFuncionarios.add(funcionario);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaFuncionarios;
	}
	
	public void alterarFuncionario(Funcionario funcionario) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_FUNCIONARIO);) {
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getEmail());
			pst.setString(3, funcionario.getCargo());
			pst.setDate(4, Date.valueOf(funcionario.getData_nascimento()));
			pst.setString(5, funcionario.getStatus());
			pst.setFloat(6, funcionario.getSalario());
			pst.setString(7, funcionario.getCpf());
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
	}
	
   public void excluirFuncionario(String cpf) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_FUNCIONARIO);) {
			pst.setString(1, cpf);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
	}
}
