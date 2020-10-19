package main.entity;

public class Produto {

	private long Id_produto;
	
	private String Nome;
	
	private float Valor_unitario;
	
	private String Unidade_medida;
	
	private int Estoque_minimo;
	
	private int Estoque_maximo;
	
	private int Estoque_atual;

	private float Peso_bruto;
	
	private float Peso_liquido;
	
	private String Fornecedor_Cnpj;
	
	private long Id_categoria;

	public long getId_produto() {
		return Id_produto;
	}

	public void setId_produto(long id_produto) {
		Id_produto = id_produto;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public float getValor_unitario() {
		return Valor_unitario;
	}

	public void setValor_unitario(float valor_unitario) {
		Valor_unitario = valor_unitario;
	}

	public String getUnidade_medida() {
		return Unidade_medida;
	}

	public void setUnidade_medida(String unidade_medida) {
		Unidade_medida = unidade_medida;
	}

	public int getEstoque_minimo() {
		return Estoque_minimo;
	}

	public void setEstoque_minimo(int estoque_minimo) {
		Estoque_minimo = estoque_minimo;
	}

	public int getEstoque_maximo() {
		return Estoque_maximo;
	}

	public void setEstoque_maximo(int estoque_maximo) {
		Estoque_maximo = estoque_maximo;
	}

	public float getPeso_bruto() {
		return Peso_bruto;
	}

	public void setPeso_bruto(float peso_bruto) {
		Peso_bruto = peso_bruto;
	}

	public float getPeso_liquido() {
		return Peso_liquido;
	}

	public void setPeso_liquido(float peso_liquido) {
		Peso_liquido = peso_liquido;
	}

	public String getFornecedor_Cnpj() {
		return Fornecedor_Cnpj;
	}

	public void setFornecedor_Cnpj(String fornecedor_Cnpj) {
		Fornecedor_Cnpj = fornecedor_Cnpj;
	}

	public long getId_categoria() {
		return Id_categoria;
	}

	public void setId_categoria(long id_categoria) {
		Id_categoria = id_categoria;
	}
	
	public int getEstoque_atual() {
		return Estoque_atual;
	}

	public void setEstoque_atual(int estoque_atual) {
		Estoque_atual = estoque_atual;
	}

	@Override
	public String toString() {
		return Nome;
	}
	
}
