package main.entity;

public class Telefone {
	
	private int Id_telefone;
	
	private String Telefones;
	
	private String Tipo;

	public int getId_telefone() {
		return Id_telefone;
	}

	public void setId_telefone(int id_telefone) {
		Id_telefone = id_telefone;
	}

	public String getTelefones() {
		return Telefones;
	}

	public void setTelefones(String telefones) {
		Telefones = telefones;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public Telefone(int id_telefone, String telefones) {
		super();
		Id_telefone = id_telefone;
		Telefones = telefones;
	}
	
	public Telefone() {
	}
	
	
}
