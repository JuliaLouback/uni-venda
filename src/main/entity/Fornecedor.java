package main.entity;

public class Fornecedor {
	
	private String Nome_empresa; 
	
	private String Email;
	
	private String Cnpj;
	
	private long Inscricao_estadual;
	
	private long Inscricao_municipal;
	
	private long Id_endereco;
	
	
	public String getNome_empresa() {
		return Nome_empresa;
	}
	public void setNome_empresa(String nome_empresa) {
		Nome_empresa = nome_empresa;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCnpj() {
		return Cnpj;
	}
	public void setCnpj(String cnpj) {
		Cnpj = cnpj;
	}
	public long getInscricao_estadual() {
		return Inscricao_estadual;
	}
	public void setInscricao_estadual(long inscricao_estadual) {
		Inscricao_estadual = inscricao_estadual;
	}
	public long getInscricao_municipal() {
		return Inscricao_municipal;
	}
	public void setInscricao_municipal(long inscricao_municipal) {
		Inscricao_municipal = inscricao_municipal;
	}
	
	public long getId_endereco() {
		return Id_endereco;
	}
	public void setId_endereco(long id_endereco) {
		Id_endereco = id_endereco;
	}
	
	public Fornecedor(String nome_empresa, String email, String cnpj, long inscricao_estadual,
			long inscricao_municipal) {
		super();
		Nome_empresa = nome_empresa;
		Email = email;
		Cnpj = cnpj;
		Inscricao_estadual = inscricao_estadual;
		Inscricao_municipal = inscricao_municipal;
	}
	
	public Fornecedor () {
		
	}
	
	@Override
	public String toString() {
		return "Fornecedor [Nome_empresa=" + Nome_empresa + ", Email=" + Email + ", Cnpj=" + Cnpj
				+ ", Inscricao_estadual=" + Inscricao_estadual + ", Inscricao_municipal=" + Inscricao_municipal + "]";
	}
	
}
