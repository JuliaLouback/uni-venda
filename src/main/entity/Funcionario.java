package main.entity;

import java.time.LocalDate;

public class Funcionario {

	private String Cpf;
	
	private String Email;
	
	private String Nome;
	
	private String Cargo;
	
	private LocalDate Data_nascimento;
	
	private float Salario;

	private String Status;
	
	private String Senha;
	
	private long Id_endereco;

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public LocalDate getData_nascimento() {
		return Data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		Data_nascimento = data_nascimento;
	}

	public float getSalario() {
		return Salario;
	}

	public void setSalario(float salario) {
		Salario = salario;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public long getId_endereco() {
		return Id_endereco;
	}

	public void setId_endereco(long id_endereco) {
		Id_endereco = id_endereco;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	@Override
	public String toString() {
		return Nome;
	}
	
	
}
