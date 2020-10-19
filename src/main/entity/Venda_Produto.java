package main.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class Venda_Produto {

	private Produto Produto;
	
	private Integer Quantidade;
	
	private String Nome;

	private Float Valor_Total;
	
	private long Venda_Id_Venda;

	public long getVenda_Id_Venda() {
		return Venda_Id_Venda;
	}

	public void setVenda_Id_Venda(long venda_Id_Venda) {
		Venda_Id_Venda = venda_Id_Venda;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		this.Produto = produto;
	}

	public Integer getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		Quantidade = quantidade;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
	public Float getValor_Total() {
		return Valor_Total;
	}

	public void setValor_Total(Float valor_Total) {
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat decimalFormat = new DecimalFormat("##.##", symbols);
		float twoDigitsF = Float.valueOf(decimalFormat.format(valor_Total)); 
		
		Valor_Total = twoDigitsF;
	}

	public Venda_Produto() {
		
	}
	public Venda_Produto(Produto produto, Integer quantidade, Float valor) {
		super();
		this.Produto = produto;
		Quantidade = quantidade;
		Nome = produto.getNome();
		Valor_Total = valor;
	}


}
