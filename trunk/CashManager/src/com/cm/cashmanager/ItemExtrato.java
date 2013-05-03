package com.cm.cashmanager;

public class ItemExtrato {

	private String texto; 
	private int iconeRid; 
	private int dateRid;
	private Double valor; 

	public ItemExtrato() { 
		this("", -1, -1, 0.0d); 
	} 
	public ItemExtrato(String texto, int iconeRid, int dateRid, Double valor) { 
		this.texto = texto; 
		this.iconeRid = iconeRid; 
		this.dateRid = dateRid;
		this.valor = valor;
	} 
	public int getIconeRid() { 
		return iconeRid; 
	} 
	public void setIconeRid(int iconeRid) { 
		this.iconeRid = iconeRid; 
	} 
	public String getTexto() { 
		return texto; 
	} 
	public void setTexto(String texto) { 
		this.texto = texto; 
	}
	public int getDateRid() {
		return dateRid;
	}
	public void setDateRid(int dateRid) {
		this.dateRid = dateRid;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	} 	
	
}
