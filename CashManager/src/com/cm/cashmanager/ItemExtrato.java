package com.cm.cashmanager;

public class ItemExtrato {

	private String texto; 
	private int iconeRid; 
	private int dateRid;

	public ItemExtrato() { 
		this("", -1, -1); 
	} 
	public ItemExtrato(String texto, int iconeRid, int dateRid) { 
		this.texto = texto; 
		this.iconeRid = iconeRid; 
		this.dateRid = dateRid;
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
	
}
