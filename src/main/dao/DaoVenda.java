package main.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.entity.Categoria;
import main.entity.Endereco;
import main.entity.Funcionario;
import main.entity.Pagamento;
import main.entity.Produto;
import main.entity.Venda;
import main.entity.Venda_Pagamento;
import main.entity.Venda_Produto;
import main.repository.CNXJDBC;
import main.util.ShowAlert;

public class DaoVenda {

	private final String SQL_INSERE_VENDA = "INSERT INTO Venda (Data_venda,Valor_total,Funcionario_Cpf,Cliente_Cpf) VALUES (?,?,?,?);";

	private final String SQL_INSERE_VENDA_PRODUTO = "INSERT INTO Venda_Produto (Venda_Id_venda, Produto_Id_produto, Quantidade, Valor) VALUES (?,?,?,?);";

	private final String SQL_INSERE_VENDA_PAGAMENTO = "INSERT INTO Venda_Pagamento (Venda_Id_venda, Pagamento_Id_pagamento, Valor) VALUES (?,?,?);";

	private final String SQL_SELECIONA_VENDA = "SELECT * FROM Venda";
	
	private final String SQL_EXCLUI_VENDA = "DELETE FROM Venda  WHERE Id_venda = ?;";

	private final String SQL_EXCLUI_VENDAPRODUTO = "DELETE FROM Venda_Produto  WHERE Venda_Id_venda = ?;";

	private final String SQL_EXCLUI_VENDAPAGAMENTO = "DELETE FROM Venda_Pagamento  WHERE Venda_Id_venda = ?;";

	
	public long inserirVenda(Venda venda) {
		
		int id = 0;
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_VENDA,  Statement.RETURN_GENERATED_KEYS);) {
			pst.setObject(1, venda.getData_venda());
			pst.setFloat(2, venda.getValor_total());
			pst.setString(3, venda.getFuncionario_Cpf());
			pst.setString(4, venda.getCliente_Cpf());
			pst.executeUpdate();
			
			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
		return 0;
	}
	
	public boolean inserirVendaProduto(Venda_Produto venda_produto) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_VENDA_PRODUTO);) {
			pst.setLong(1, venda_produto.getVenda_Id_Venda());
			pst.setLong(2, venda_produto.getProduto().getId_produto());
			pst.setInt(3, venda_produto.getQuantidade());
			pst.setFloat(4, venda_produto.getValor_Total());
			
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("CPF já cadastrado!");
		}
		
		return false;
	}
	
	public boolean inserirVendaPagamento(Venda_Pagamento venda_pagamento) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_VENDA_PAGAMENTO);) {
			pst.setLong(1, venda_pagamento.getVenda_Id_Venda());
			pst.setLong(2, venda_pagamento.getPagamento().getId_pagamento());
			pst.setFloat(3, venda_pagamento.getValor());
			
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("CPF já cadastrado!");
		}
		
		return false;
	}
	
	public ArrayList<Venda> listarVenda() {
		ArrayList<Venda> listaVenda = new ArrayList<Venda>();

		Venda Venda;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_VENDA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Venda = new Venda();
				Venda.setCliente_Cpf(rs.getString("CLIENTE_CPF"));
				Venda.setId_venda(rs.getInt("ID_VENDA"));

				String cliente = new DaoCliente().listarUmCliente(rs.getString("CLIENTE_CPF"));
				Venda.setNome_Cliente(cliente);
				Venda.setData_v(rs.getDate("DATA_VENDA").toLocalDate());
				Venda.setHora_v(rs.getTime("DATA_VENDA").toLocalTime());
				Venda.setFuncionario_Cpf(rs.getString("FUNCIONARIO_CPF"));
				String funcionario = new DaoFuncionario().listarUmFuncionario(rs.getString("FUNCIONARIO_CPF"));
				Venda.setNome_Funcionario(funcionario);
				Venda.setData_Local(rs.getDate("DATA_VENDA").toLocalDate());
				
				Venda.setValor_total(rs.getFloat("VALOR_TOTAL"));
				
				listaVenda.add(Venda);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaVenda;
	}
	
	public ArrayList<Venda_Produto> listarVendaProduto(int Id_venda) {
		
		String SQL_SELECIONA_VENDA_PRODUTO = "SELECT * FROM Venda_Produto WHERE Venda_Id_Venda = '"+Id_venda+"'";

		ArrayList<Venda_Produto> listaVenda = new ArrayList<Venda_Produto>();

		Venda_Produto Venda;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_VENDA_PRODUTO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Venda = new Venda_Produto();
				Venda.setVenda_Id_Venda(Id_venda);
				 
				Produto produto = new DaoProduto().listarUmProduto(rs.getInt("PRODUTO_ID_PRODUTO"));
				Venda.setProduto(produto);
				Venda.setNome(produto.getNome());
				Venda.setQuantidade(rs.getInt("QUANTIDADE"));
				Venda.setValor_Total(rs.getFloat("VALOR"));
			
				listaVenda.add(Venda);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaVenda;
	}
	
	public ArrayList<Venda_Pagamento> listarVendaPagamento(int Id_venda) {
		
		String SQL_SELECIONA_VENDA_PAGAMENTO = "SELECT * FROM Venda_Pagamento WHERE Venda_Id_Venda = '"+Id_venda+"'";

		ArrayList<Venda_Pagamento> listaVenda = new ArrayList<Venda_Pagamento>();

		Venda_Pagamento Venda;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_VENDA_PAGAMENTO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Venda = new Venda_Pagamento();
				Venda.setVenda_Id_Venda(Id_venda);
				 
				Pagamento pagamento = new DaoPagamento().listarPagamento(rs.getLong("PAGAMENTO_ID_PAGAMENTO"));
				Venda.setNome(pagamento.getNome());
				Venda.setPagamento(pagamento);
				Venda.setValor(rs.getFloat("VALOR"));
			
				listaVenda.add(Venda);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaVenda;
	}
	
	public boolean excluirVenda(int id) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_VENDA);) {
			pst.setInt(1, id);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Categoria não pode ser excluída porque esta associada a um ou vários Produtos!");
		}
		
		return false;
	}
	
	public boolean excluirVendaProduto(int id) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_VENDAPRODUTO);) {
			pst.setInt(1, id);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Categoria não pode ser excluída porque esta associada a um ou vários Produtos!");
		}
		
		return false;
	}
	
	public boolean excluirVendaPagamento(int id) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_VENDAPAGAMENTO);) {
			pst.setInt(1, id);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Categoria não pode ser excluída porque esta associada a um ou vários Produtos!");
		}
		
		return false;
	}
	
}

