package main.entity;

public class Categoria {

	private int Id_categoria;
	
	private String Nome;

	public int getId_categoria() {
		return Id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		Id_categoria = id_categoria;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	@Override
	public String toString() {
		return Nome;
	}
	
	
}
