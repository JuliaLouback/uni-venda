package main.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Venda {

	private LocalDateTime Data_venda;
	
	private float Valor_total;
	
	private String Funcionario_Cpf;
	
	private String Cliente_Cpf;
	
	private LocalDate Data_v;

	private LocalTime Hora_v;
	
	private String Data_string;
	
	private String Valor_string;

	private String Nome_Cliente;
	
	private String Nome_Funcionario;

	private int Id_venda;
	
	private LocalDate Data_Local;
	
	public LocalDate getData_Local() {
		return Data_Local;
	}

	public void setData_Local(LocalDate data_Local) {
		Data_Local = data_Local;
	}

	public int getId_venda() {
		return Id_venda;
	}

	public void setId_venda(int id_venda) {
		Id_venda = id_venda;
	}

	public String getNome_Funcionario() {
		return Nome_Funcionario;
	}

	public void setNome_Funcionario(String nome_Funcionario) {
		Nome_Funcionario = nome_Funcionario;
	}

	public String getData_v() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	     String dataFormatada = Data_v.format(formatter);
		 return dataFormatada;
	}
	
	public String getData_string() {
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	     String data = Data_v.format(formatter);
	     
	     DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
	     String hora = Hora_v.format(formatter1);
	     
		 return data +" "+ hora;
	}

	public void setData_v(LocalDate data) {
		Data_v = data;
	}
	
	public Venda() {
		
	}

	public LocalDateTime getData_venda() {
		return Data_venda;
	}

	public void setData_venda(LocalDateTime data_venda) {
		Data_venda = data_venda;
	}

	public float getValor_total() {
		return Valor_total;
	}

	public void setValor_total(float valor_total) {
		Valor_total = valor_total;
	}

	public String getFuncionario_Cpf() {
		return Funcionario_Cpf;
	}

	public void setFuncionario_Cpf(String funcionario_Cpf) {
		Funcionario_Cpf = funcionario_Cpf;
	}

	public String getCliente_Cpf() {
		return Cliente_Cpf;
	}

	public void setCliente_Cpf(String cliente_Cpf) {
		Cliente_Cpf = cliente_Cpf;
	}

	public LocalTime getHora_v() {
		return Hora_v;
	}

	public void setHora_v(LocalTime hora_v) {
		Hora_v = hora_v;
	}

	public String getNome_Cliente() {
		return Nome_Cliente;
	}

	public void setNome_Cliente(String nome_Cliente) {
		Nome_Cliente = nome_Cliente;
	}

	public String getValor_string() {
		return "R$ "+ String.format("%.02f", Valor_total);
	}

	public void setValor_string(String valor_string) {
		Valor_string = valor_string;
	}

	
	
}
