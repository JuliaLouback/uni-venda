package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.Pagamento;
import main.repository.CNXJDBC;
import main.util.ShowAlert;

public class DaoPagamento {
	
	private final String SQL_SELECIONA_PAGAMENTO = "SELECT * FROM Pagamento";

	private final String SQL_INSERE_PAGAMENTO = "INSERT INTO Pagamento (Nome) VALUES (?);";
		
	private final String SQL_ALTERA_PAGAMENTO = "UPDATE Pagamento SET Nome =? WHERE Id_pagamento = ?;";
	
	private final String SQL_EXCLUI_PAGAMENTO= "DELETE FROM Pagamento  WHERE Id_pagamento = ?;";
	
	public ArrayList<Pagamento> listarPagamento() {
		ArrayList<Pagamento> listaPagamentos = new ArrayList<Pagamento>();

		Pagamento Pagamento;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_PAGAMENTO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Pagamento = new Pagamento();
				Pagamento.setId_pagamento(rs.getInt("ID_PAGAMENTO"));
				Pagamento.setNome(rs.getString("NOME"));
				listaPagamentos.add(Pagamento);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaPagamentos;
	}
	
	
	public Pagamento listarPagamento(long id) {
			
		Pagamento Pagamento = new Pagamento();
		String SQL_SELECIONA_PAGAMENTO_ID = "SELECT * FROM Pagamento WHERE Id_pagamento = '"+id+"';";

		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_PAGAMENTO_ID); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Pagamento.setId_pagamento(rs.getInt("ID_PAGAMENTO"));
				Pagamento.setNome(rs.getString("NOME"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
			
		return Pagamento;
	}
	 
	public boolean inserirPagamento(Pagamento Pagamento) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_PAGAMENTO);) {
			pst.setString(1, Pagamento.getNome());
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Pagamento já cadastrado!");
		}
		
		return false;
	}
			
	public boolean alterarPagamento(Pagamento Pagamento) {
			
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_PAGAMENTO);) {
			pst.setString(1, Pagamento.getNome());
			pst.setInt(2, Pagamento.getId_pagamento());
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Pagamento já cadastrado!");
		}
		
		return false;
	}
	
	public boolean excluirPagamento(int id) {
			
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_PAGAMENTO);) {
			pst.setInt(1, id);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Pagamento não pode ser excluído porque esta associado a uma ou várias Vendas!");
		}
		
		return false;
	}
}
