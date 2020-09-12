package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.Cliente;
import main.repository.CNXJDBC;

public class DaoCliente {

	private final String SQL_INSERE_CLIENTE = "INSERT INTO Cliente (Cpf,Nome,Email) VALUES (?,?,?);";
	
	private final String SQL_SELECIONA_CLIENTE = "SELECT * FROM Cliente";
	
	private final String SQL_ALTERA_CLIENTE = "UPDATE Cliente SET Nome =?, Email = ? WHERE Cpf = ?;";
	
	private final String SQL_EXCLUI_CLIENTE = "DELETE FROM Cliente  WHERE Cpf = ?;";
		
	private PreparedStatement pst = null;

	public void inserirCliente(Cliente cliente) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_CLIENTE);) {
			pst.setString(1, cliente.getCpf());
			pst.setString(2, cliente.getNome());
			pst.setString(3, cliente.getEmail());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
	}
	
	public ArrayList<Cliente> listarCliente() {
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

		Cliente cliente;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CLIENTE); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setCpf(rs.getString("CPF"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setEmail(rs.getString("EMAIL"));
				listaClientes.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaClientes;
	}
	
	public void alterarCliente(Cliente cliente) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_CLIENTE);) {
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getCpf());
			
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
	}
	
   public void excluirCliente(String cpf) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_CLIENTE);) {
			pst.setString(1, cpf);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
	}

}
