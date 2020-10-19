package main.entity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Venda_Pagamento {

	private Pagamento pagamento;
	
	private float Valor;
	
	private String Nome;
	
	private long Venda_Id_Venda;

	public long getVenda_Id_Venda() {
		return Venda_Id_Venda;
	}

	public void setVenda_Id_Venda(long venda_Id_Venda) {
		Venda_Id_Venda = venda_Id_Venda;
	}


	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		
		
		this.pagamento = pagamento;
	}

	public float getValor() {
		return Valor;
	}

	public void setValor(float valor) {
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat decimalFormat = new DecimalFormat("##.##", symbols);
		float twoDigitsF = Float.valueOf(decimalFormat.format(valor)); 
		
		Valor = twoDigitsF;
	}
	
	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
}
