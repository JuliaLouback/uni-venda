package main.entity;

public class Pagamento {

	private int Id_pagamento;
	
	private String Nome;

	public int getId_pagamento() {
		return Id_pagamento;
	}

	public void setId_pagamento(int id_pagamento) {
		Id_pagamento = id_pagamento;
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
