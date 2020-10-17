package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.entity.Funcionario;
import main.repository.CNXJDBC;

public class DaoLogin {

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
}
