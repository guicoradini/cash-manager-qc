package com.cm.cashmanager;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExtratoAdapter extends BaseAdapter{

	private LayoutInflater mInflater; 
	private List<ItemExtrato> itens; 

	public ExtratoAdapter(Context context, List<ItemExtrato> itens) { 

		//Itens do listview 
		this.itens = itens; 
		//Objeto responsável por pegar o Layout do item. 
		mInflater = LayoutInflater.from(context);

	}

	public int getCount() { 
		return itens.size(); 
	} 

	public ItemExtrato getItem(int position) { 
		return itens.get(position); 
	} 

	public long getItemId(int position) { 
		return position; 
	}

	public View getView(int position, View view, ViewGroup parent) { 

		ItemSuporte itemHolder; //se a view estiver nula (nunca criada), inflamos o layout nela. 

		if (view == null) { 

			//infla o layout para podermos pegar as views 
			view = mInflater.inflate(R.layout.item_extrato, null); 

			//cria um item de suporte para não precisarmos sempre //inflar as mesmas informacoes 
			itemHolder = new ItemSuporte(); 

			itemHolder.txtTitle = ((TextView) view.findViewById(R.id.text)); 
			itemHolder.txtValor = ((TextView) view.findViewById(R.id.valor)); 
			itemHolder.imgIcon = ((ImageView) view.findViewById(R.id.imagemview)); 
			itemHolder.imgDate = ((ImageView) view.findViewById(R.id.imageDate));

			//define os itens na view; 
			view.setTag(itemHolder); 

		} 
		else { 
			
			//se a view já existe pega os itens. 
			itemHolder = (ItemSuporte) view.getTag(); 
		} 

		//pega os dados da lista //e define os valores nos itens. 
		ItemExtrato item = itens.get(position); 
		itemHolder.txtTitle.setText(item.getTexto()); 
		itemHolder.imgDate.setImageResource(item.getDateRid());
		itemHolder.imgIcon.setImageResource(item.getIconeRid()); 
		itemHolder.txtValor.setText(String.valueOf(item.getValor())); 

		//retorna a view com as informações 		
		return view; 
	} 
	/** * Classe de suporte para os itens do layout. */ 
	private class ItemSuporte { 
		ImageView imgIcon; 
		ImageView imgDate;
		TextView txtTitle; 
		TextView txtValor; 
	} 

}
