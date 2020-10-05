package main.entity;

public class FuncionarioTelefone {

	private long Id_telefone;

	private String Cpf;

	public long getId_telefone() {
		return Id_telefone;
	}

	public void setId_telefone(long id_telefone) {
		Id_telefone = id_telefone;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public FuncionarioTelefone() {}
}
