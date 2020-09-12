package main.entity;

public class Cliente {

	private String Cpf;
	
	private String Email;
	
	private String Nome;

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

	@Override
	public String toString() {
		return "Cliente [Cpf=" + Cpf + ", Email=" + Email + ", Nome=" + Nome + "]";
	}
	
	
	
}
