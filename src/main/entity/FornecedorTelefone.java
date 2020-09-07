package main.entity;

public class FornecedorTelefone {
	
	private long Id_telefone;

	private String Cnpj;

	public long getId_telefone() {
		return Id_telefone;
	}

	public void setId_telefone(long id_telefone) {
		Id_telefone = id_telefone;
	}

	public String getCnpj() {
		return Cnpj;
	}

	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
	}
	
	public FornecedorTelefone() {
		
	}

}
