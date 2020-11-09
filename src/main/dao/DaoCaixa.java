package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import main.entity.Caixa;
import main.entity.Categoria;
import main.entity.Cliente;
import main.entity.Venda;
import main.repository.CNXJDBC;
import main.util.ShowAlert;

public class DaoCaixa {

	private final String SQL_INSERE_CAIXA = "INSERT INTO Caixa (Data_abertura,Funcionario_Cpf) VALUES (?,?);";
	
	private final String SQL_SELECIONA_CAIXA = "SELECT Id_caixa, FORMAT(Data_abertura, 'dd/MM/yyyy, hh:mm:ss ') as 'Data_abertura', FORMAT(Data_fechamento, 'dd/MM/yyyy, hh:mm:ss ') as 'Data_fechamento', Funcionario_Cpf, Valor_final FROM Caixa";
		
	private final String SQL_ALTERA_CAIXA = "UPDATE Caixa SET Valor_final = ? WHERE Funcionario_Cpf = ? and Data_fechamento is null;";
	
	private final String SQL_FECHAR_CAIXA = "UPDATE Caixa SET Data_fechamento = ? WHERE Funcionario_Cpf = ? and Data_fechamento is null;";

	
	private PreparedStatement pst = null;

	public boolean inserirCaixa(Caixa caixa) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_CAIXA);) {
			pst.setObject(1, caixa.getData_abertura());
			pst.setString(2, caixa.getFuncionario_Cpf());
			
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("ei");
			}
			
			return true;
		} catch (SQLException e) {
	    	return false;
		}	
	}
	
	public boolean alterarCaixa(Caixa caixa) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_CAIXA);) {
			pst.setFloat(1, caixa.getValor_final());
			pst.setString(2, caixa.getFuncionario_Cpf());
			
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("ei");
			}
			
			return true;
		} catch (SQLException e) {
	    	return false;
		}	
	}
	
	public boolean fecharCaixa(Caixa caixa) {
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_FECHAR_CAIXA);) {
			pst.setObject(1, caixa.getData_fechamento());
			pst.setString(2, caixa.getFuncionario_Cpf());
			pst.execute();
			
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("juliaaal");
			}
			return true;
		} catch (SQLException e) {
	    	new ShowAlert().erroAlert("Categoria já cadastrada!");
		}
		
		return false;
	}
	
	public boolean listarUmCaixa(String Cpf) {
		
		String SQL_SELECIONA_UM_CAIXA = "SELECT * FROM Caixa WHERE Funcionario_Cpf = '"+Cpf+"' and Data_fechamento is null";
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_UM_CAIXA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				System.out.println(rs.getString("Data_fechamento"));
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());			
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return false;
	}
	
	public boolean listarUmCaixaData(String Cpf) {
		
		String SQL_SELECIONA_CAIXA_DATA = "SELECT * FROM Caixa WHERE CONVERT(date, Data_abertura) = CONVERT(date, '"+LocalDate.now()+"') and Funcionario_Cpf = '"+Cpf+"';";
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CAIXA_DATA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				System.out.println(rs.getString("Data_fechamento"));
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());			
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return false;
	}
	
	public Caixa listarUmCaixaCpf(String Cpf) {
		
		String SQL_SELECIONA_UM_CAIXA = "SELECT * FROM Caixa WHERE Funcionario_Cpf = '"+Cpf+"' and Data_fechamento is null";
		
		Caixa caixa = new Caixa();
		
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_UM_CAIXA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				System.out.println(rs.getFloat("Valor_final"));
				caixa.setValor_final(rs.getFloat("Valor_final"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());			
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return caixa;
	}
	
	public ArrayList<Caixa> listarCaixa() {
		ArrayList<Caixa> listaCaixa = new ArrayList<Caixa>();

		Caixa caixa;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_CAIXA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				caixa = new Caixa();
				caixa.setFuncionario_Cpf(rs.getString("FUNCIONARIO_CPF"));
				caixa.setId_caixa(rs.getInt("ID_CAIXA"));

				String funcionario = new DaoFuncionario().listarUmFuncionario(rs.getString("FUNCIONARIO_CPF"));
				caixa.setNome_Funcionario(funcionario);
				caixa.setData_abertura_string(rs.getString("Data_abertura"));
				caixa.setData_fechamento_string(rs.getString("Data_fechamento"));

				caixa.setValor_final(rs.getFloat("VALOR_FINAL"));
				
				listaCaixa.add(caixa);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		

		return listaCaixa;
	}
	
}
