package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.entity.Produto;
import main.repository.CNXJDBC;
import main.util.ShowAlert;

public class DaoProduto {

private final String SQL_INSERE_PRODUTO = "INSERT INTO Produto (Id_produto,Nome,Valor_unitario, Unidade_medida, Estoque_minimo, Estoque_maximo,Peso_bruto,Peso_liquido,"
		+ "Fornecedor_Cnpj, Categoria_Id_Categoria, Estoque_atual) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	
	private final String SQL_SELECIONA_PRODUTO = "SELECT * FROM Produto";
	
	private final String SQL_ALTERA_PRODUTO = "UPDATE Produto SET Nome =?, Valor_unitario = ?, Unidade_medida = ?, Estoque_minimo = ?, Estoque_maximo = ?, Peso_bruto = ?, "
			+ "Peso_liquido = ?, Fornecedor_Cnpj =?, Categoria_Id_Categoria =?,Estoque_atual =? WHERE Id_produto = ?;";
	
	private final String SQL_EXCLUI_PRODUTO = "DELETE FROM Produto WHERE Id_produto = ?;";
		
	private PreparedStatement pst = null;

	public boolean inserirProduto(Produto Produto) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_PRODUTO);) {
			pst.setLong(1, Produto.getId_produto());
			pst.setString(2, Produto.getNome());
			pst.setFloat(3, Produto.getValor_unitario());
			pst.setString(4, Produto.getUnidade_medida());
			pst.setInt(5, Produto.getEstoque_minimo());
			pst.setInt(6, Produto.getEstoque_maximo());
			pst.setFloat(7, Produto.getPeso_bruto());
			pst.setFloat(8, Produto.getPeso_liquido());
			pst.setString(9, Produto.getFornecedor_Cnpj());
			pst.setLong(10, Produto.getId_categoria());
			pst.setInt(11, Produto.getEstoque_atual());
			
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Produto já cadastrado!");
		}
		
		return false;
	}
	
	public ArrayList<Produto> listarProduto() {
		ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

		Produto Produto;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_PRODUTO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				Produto = new Produto();
				Produto.setId_produto(rs.getInt("ID_PRODUTO"));
				Produto.setNome(rs.getString("NOME"));
				Produto.setValor_unitario(rs.getFloat("VALOR_UNITARIO"));
				Produto.setUnidade_medida(rs.getString("UNIDADE_MEDIDA"));
				Produto.setEstoque_minimo(rs.getInt("ESTOQUE_MINIMO"));
				Produto.setEstoque_maximo(rs.getInt("ESTOQUE_MAXIMO"));
				Produto.setEstoque_atual(rs.getInt("ESTOQUE_ATUAL"));
				Produto.setPeso_bruto(rs.getFloat("PESO_BRUTO"));
				Produto.setPeso_liquido(rs.getFloat("PESO_LIQUIDO"));
				Produto.setId_categoria(rs.getInt("CATEGORIA_ID_CATEGORIA"));
				Produto.setFornecedor_Cnpj(rs.getString("FORNECEDOR_CNPJ"));
				
				listaProdutos.add(Produto);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaProdutos;
	}
	
	public boolean alterarProduto(Produto Produto) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_PRODUTO);) {
			pst.setString(1, Produto.getNome());
			pst.setFloat(2, Produto.getValor_unitario());
			pst.setString(3, Produto.getUnidade_medida());
			pst.setInt(4, Produto.getEstoque_minimo());
			pst.setInt(5, Produto.getEstoque_maximo());
			pst.setFloat(6, Produto.getPeso_bruto());
			pst.setFloat(7, Produto.getPeso_liquido());
			pst.setString(8, Produto.getFornecedor_Cnpj());
			pst.setLong(9, Produto.getId_categoria());
			pst.setInt(10, Produto.getEstoque_atual());
			pst.setLong(11, Produto.getId_produto());

			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Produto já cadastrado!");
		}
		
		return false;
	}
	
   public boolean excluirProduto(long id_produto) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_PRODUTO);) {
			pst.setLong(1, id_produto);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Produto não pode ser excluído porque esta associado a uma ou várias Vendas!");
		}
		
		return false;
	}

}
