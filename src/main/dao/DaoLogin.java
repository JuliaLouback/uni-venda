package main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import main.entity.Funcionario;
import main.repository.CNXJDBC;
import main.util.ShowAlert;

public class DaoLogin {

	private final String SQL_ALTERA_FUNCIONARIO = "UPDATE Funcionario SET Senha = ? WHERE Cpf = ?;";
	
	public Funcionario verificarFuncionario(String Email, String Senha) {
		
		Funcionario funcionario = new Funcionario();
		
		String SQL_SELECIONA_FUNCIONARIO = "SELECT * FROM Funcionario WHERE Email = '"+Email+"' and Senha = '"+Senha+"';";

		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_FUNCIONARIO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setEmail(rs.getString("EMAIL"));
				funcionario.setCargo(rs.getString("CARGO"));
				funcionario.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				funcionario.setStatus(rs.getString("STATUS"));
				funcionario.setSalario(rs.getFloat("SALARIO"));
				funcionario.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
			
		return funcionario;
	}
	
	public Funcionario verificarFuncionarioData(String Email, String Senha, LocalDate Data) {
		System.out.println(Data);
		System.out.println(Date.valueOf(Data));
		Funcionario funcionario = new Funcionario();
		
		String SQL_SELECIONA_FUNCIONARIO = "SELECT * FROM Funcionario WHERE Email = '"+Email+"' and Senha = '"+Senha+"' and Data_nascimento = '"+Date.valueOf(Data)+"';";

		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_FUNCIONARIO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				funcionario.setCpf(rs.getString("CPF"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
			
		return funcionario;
	}
	
	public boolean alterarFuncionario(Funcionario funcionario) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_FUNCIONARIO);) {
			pst.setString(1, funcionario.getSenha());
			pst.setString(2, funcionario.getCpf());
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("CPF já cadastrado!");
		}
		
		return false;
	}
	
	
}
