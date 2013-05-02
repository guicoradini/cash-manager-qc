package com.cm.cashmanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class ExtratoActivity extends Activity{

	private ExtratoAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.extrato);

		ListView listview = (ListView) findViewById(R.id.list);

		ItemExtrato carro = new ItemExtrato("Carro", R.drawable.car, R.drawable.number1);
		ItemExtrato ingles = new ItemExtrato("Ingles", R.drawable.car, R.drawable.number2);

		List<ItemExtrato> itens = new ArrayList<ItemExtrato>();
		itens.add(carro);
		itens.add(ingles);

		adapter = new ExtratoAdapter(this, itens);

		listview.setAdapter(adapter);
		
		registerForContextMenu(listview);	

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(item.getTitle().equals("Calendário")){
			
		}       

		return true;
	}

	/** listener ao clicar no menu de contexto*/
	@Override  
	public boolean onContextItemSelected(MenuItem item) {
		return false;  

		/**
		Integer idConsulta = item.getItemId();

		final ConsultaAndroid consultaSelecionada;

		ItemListaConsulta itemSelecionado = adapterListView.getConsultaPorId(idConsulta);

		consultaSelecionada = itemSelecionado.getConsultaAndroid();
		*/

	}
	
	/** menu de opções, ao clicar em opções no celular*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try
		{

			MenuItem menuConfiguracoes = menu.add("Menu"); 

			menuConfiguracoes.setIcon(R.drawable.ic_launcher);

		}
		catch (Exception e) {

		}            
		return super.onCreateOptionsMenu(menu);        
	}

	/** menu de contexto ao pressionar e segurar sobre um linha ( consulta )*/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {	

		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

		ItemExtrato itemSelecionado = adapter.getItem(info.position);

		if(itemSelecionado != null){

			String data = "";			

			menu.setHeaderTitle("Data");

			menu.add(Menu.NONE, info.position, Menu.NONE, "Apagar");
			menu.add(Menu.NONE, info.position, Menu.NONE,"Editar");	


		}

	}
}
