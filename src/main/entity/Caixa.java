package main.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Caixa {

	private LocalDateTime Data_abertura;

	private LocalDateTime Data_fechamento;
	
	private String Data_abertura_string;
	
	private String Data_fechamento_string;

	private String Valor_string;

	private String Nome_Funcionario;
	
	private float Valor_final;

	private String Funcionario_Cpf;
	
	public String getNome_Funcionario() {
		return Nome_Funcionario;
	}

	public void setNome_Funcionario(String nome_Funcionario) {
		Nome_Funcionario = nome_Funcionario;
	}

	private long Id_caixa;
	
	public long getId_caixa() {
		return Id_caixa;
	}

	public void setId_caixa(long id_caixa) {
		Id_caixa = id_caixa;
	}

	public String getData_abertura_string() {
		 return Data_abertura_string;
	}

	public void setData_abertura_string(String data_abertura_string) {
		Data_abertura_string = data_abertura_string;
	}

	public String getData_fechamento_string() {
		return Data_fechamento_string;
	}

	public void setData_fechamento_string(String data_fechamento_string) {
		Data_fechamento_string = data_fechamento_string;
	}

	public String getValor_string() {
		return "R$ "+ String.format("%.02f", Valor_final);
	}

	public void setValor_string(String valor_string) {
		Valor_string = valor_string;
	}

	
	public LocalDateTime getData_abertura() {
		return Data_abertura;
	}

	public void setData_abertura(LocalDateTime data_abertura) {
		Data_abertura = data_abertura;
	}

	public LocalDateTime getData_fechamento() {
		return Data_fechamento;
	}

	public void setData_fechamento(LocalDateTime data_fechamento) {
		Data_fechamento = data_fechamento;
	}

	public float getValor_final() {
		return Valor_final;
	}

	public void setValor_final(float valor_final) {
		Valor_final = valor_final;
	}

	public String getFuncionario_Cpf() {
		return Funcionario_Cpf;
	}

	public void setFuncionario_Cpf(String funcionario_Cpf) {
		Funcionario_Cpf = funcionario_Cpf;
	}

	public Caixa(LocalDateTime data_abertura, LocalDateTime data_fechamento, float valor_final,
			String funcionario_Cpf) {
		super();
		Data_abertura = data_abertura;
		Data_fechamento = data_fechamento;
		Valor_final = valor_final;
		Funcionario_Cpf = funcionario_Cpf;
	}

	public Caixa() {
		
	}
	
}
