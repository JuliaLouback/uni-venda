package main.entity;

public class Telefone {
	
	private int Id_telefone;
	
	private long Telefones;

	public int getId_telefone() {
		return Id_telefone;
	}

	public void setId_telefone(int id_telefone) {
		Id_telefone = id_telefone;
	}

	public long getTelefones() {
		return Telefones;
	}

	public void setTelefones(long telefones) {
		Telefones = telefones;
	}

	public Telefone(int id_telefone, long telefones) {
		super();
		Id_telefone = id_telefone;
		Telefones = telefones;
	}
	
	
}