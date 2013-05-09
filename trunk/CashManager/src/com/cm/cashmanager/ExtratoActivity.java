package com.cm.cashmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ExtratoActivity extends Activity {

	private Button buttonMenu;  
	private Button buttonAdd;
	private Button buttonExcluir;

	private View layoutMenu;
	private View layoutPrincipal;

	private ListView listViewMenu;
	private ListView listview;	

	private ExtratoAdapter adapter;

	/** usado para saber a largura da tela*/
	private Display display;

	private View fakeLayout;

	private AnimationListener AL;

	// Values for after the animation
	private int oldLeft;
	private int oldTop;
	private int newleft;
	private int newTop;
	private int screenWidth;    
	private int animToPostion; 
	// TODO change the name of the animToPostion for a better explanation.

	private boolean menuOpen = false;

	/** Called when the activity is first created. */  
	@Override  
	public void onCreate(Bundle savedInstanceState) {  

		super.onCreate(savedInstanceState);  

		setContentView(R.layout.extrato); 


		buttonMenu = (Button)findViewById(R.id.buttonMenu);  
		buttonAdd = (Button)findViewById(R.id.ButtonAdd);  
		buttonExcluir = (Button)findViewById(R.id.ButtonExcluir);  

		layoutMenu = (View) findViewById(R.id.layoutMenu);  
		layoutPrincipal = (View) findViewById(R.id.layoutPrincipal);

		/** menu principal*/
		listview = (ListView) findViewById(R.id.listPrincipal);

		final GestureDetector gestureDetector = new GestureDetector(new MyGestureDetector());
		View.OnTouchListener gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event); 
			}};

			listview.setOnTouchListener(gestureListener);

			ItemExtrato carro = new ItemExtrato("Carro", R.drawable.car, R.drawable.number1, 500d);
			ItemExtrato ingles = new ItemExtrato("Ingles", R.drawable.car, R.drawable.number2, 400d);

			List<ItemExtrato> itens = new ArrayList<ItemExtrato>();
			itens.add(carro);
			itens.add(ingles);

			adapter = new ExtratoAdapter(this, itens);

			listview.setAdapter(adapter);

			registerForContextMenu(listview);	
			/** menu principal*/

			listViewMenu=(ListView)findViewById(R.id.listViewMenu);
			fakeLayout = (View)findViewById(R.id.fake_layout);

			listViewMenu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.menuOptions)));
			
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
					
					if(menuOpen == true){
						
						animSlideLeft();
					}					
				}
			});				
				

			/** usado para realizar a animação*/
			display =  getWindowManager().getDefaultDisplay();

			screenWidth = display.getWidth();

			int calcAnimationPosition = (screenWidth /3);

			// Value where the onTop Layer has to animate
			// also the max width of the layout underneath 
			// Set Layout params for subLayout according to calculation
			animToPostion = screenWidth - calcAnimationPosition;

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(animToPostion, RelativeLayout.LayoutParams.FILL_PARENT);
			layoutMenu.setLayoutParams(params);

			buttonAdd.setOnClickListener(new View.OnClickListener() {  

				@Override  
				public void onClick(View v) { 				

					Intent intent = new Intent(v.getContext(), AddItemActivity.class);
					startActivity(intent);

				}  
			}); 

			buttonExcluir.setOnClickListener(new View.OnClickListener() {  

				@Override  
				public void onClick(View v) { 


				}  
			}); 

			buttonMenu.setOnClickListener(new View.OnClickListener() {  

				@Override  
				public void onClick(View v) { 
					if(menuOpen == false){    
						animSlideRight();
					} else if (menuOpen == true) {
						animSlideLeft();
					}
				}  
			});  

			AL = new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					buttonMenu.setClickable(false);
					layoutPrincipal.setEnabled(false);
				}           
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}               
				@Override
				public void onAnimationEnd(Animation animation) {
					if(menuOpen == true) {
						Log.d("", "Open");              
						layoutPrincipal.layout(oldLeft, oldTop, oldLeft + layoutPrincipal.getMeasuredWidth(), oldTop + layoutPrincipal.getMeasuredHeight() );
						menuOpen = false;
						buttonMenu.setClickable(true);
						layoutPrincipal.setEnabled(true);
					} else if(menuOpen == false) {
						Log.d("","FALSE");
						layoutPrincipal.layout(newleft, newTop, newleft + layoutPrincipal.getMeasuredWidth(), newTop + layoutPrincipal.getMeasuredHeight() );                    
						layoutPrincipal.setEnabled(true);
						menuOpen = true;
						buttonMenu.setClickable(true);
					}
				}
			};
	} 

	public void animSlideRight(){

		fakeLayout.setVisibility(View.VISIBLE);
		newleft = layoutPrincipal.getLeft() + animToPostion;
		newTop = layoutPrincipal.getTop();    
		TranslateAnimation slideRight = new TranslateAnimation(0,newleft,0,0);
		slideRight.setDuration(500);   
		slideRight.setFillEnabled(true);   
		slideRight.setAnimationListener(AL);    
		layoutPrincipal.startAnimation(slideRight);           
	}

	public void animSlideLeft() {

		fakeLayout.setVisibility(View.GONE);
		oldLeft = layoutPrincipal.getLeft() - animToPostion;
		oldTop = layoutPrincipal.getTop();        
		TranslateAnimation slideLeft = new TranslateAnimation(newleft,oldLeft,0,0);
		slideLeft.setDuration(500);   
		slideLeft.setFillEnabled(true);   
		slideLeft.setAnimationListener(AL);    
		layoutPrincipal.startAnimation(slideLeft);                
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

	private void onUpSwipe() {

	}

	private void onDownSwipe() {

	}

	private void onLeftSwipe() {

		if(menuOpen == true){    
			animSlideLeft();
		} 

	}

	private void onRightSwipe() {		

		if(menuOpen == false){    
			animSlideRight();
		} 

	}

	private class MyGestureDetector extends SimpleOnGestureListener {

		private static final int SWIPE_MIN_DISTANCE = 120;
		private static final int SWIPE_MAX_OFF_PATH = 200;
		private static final int SWIPE_THRESHOLD_VELOCITY = 200;

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2,
				float velocityX, float velocityY) {
			try {
				float diffAbs = Math.abs(e1.getY() - e2.getY());
				float diffX = e1.getX() - e2.getX();
				float diffY = e1.getY() - e2.getY();

				//swipe vertical
				if (diffAbs > SWIPE_MAX_OFF_PATH){
					if (diffY > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
						ExtratoActivity.this.onUpSwipe();
					} else if (-diffY > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
						ExtratoActivity.this.onDownSwipe();
					}
				}


				//swipe horizontal
				else{

					if (diffX > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						ExtratoActivity.this.onLeftSwipe();						
					} else if (-diffX > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
						ExtratoActivity.this.onRightSwipe();
					}
				}

			} catch (Exception e) {
				Log.e("YourActivity", "Error on gestures");
			}
			return false;
		}
	}
} 