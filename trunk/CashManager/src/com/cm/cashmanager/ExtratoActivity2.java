package com.cm.cashmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ExtratoActivity2 extends Activity {

	private Button buttonMenu;  

	private View layoutMenu;
	private View layoutPrincipal;

	private ListView listViewMenu;

	private String itensMenu[]={"Android","iPhone","BlackBerry","AndroidPeople"};
	
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

		setContentView(R.layout.extrato_teste);  

		buttonMenu = (Button)findViewById(R.id.buttonMenu);  

		layoutMenu = (View) findViewById(R.id.layoutMenu);  
		layoutPrincipal = (View) findViewById(R.id.layoutPrincipal);
		
		/** menu principal*/
		ListView listview = (ListView) findViewById(R.id.listPrincipal);
		
		ItemExtrato carro = new ItemExtrato("Carro", R.drawable.car, R.drawable.number1);
		ItemExtrato ingles = new ItemExtrato("Ingles", R.drawable.car, R.drawable.number2);

		List<ItemExtrato> itens = new ArrayList<ItemExtrato>();
		itens.add(carro);
		itens.add(ingles);
		
		adapter = new ExtratoAdapter(this, itens);

		listview.setAdapter(adapter);
		
		registerForContextMenu(listview);	
		/** menu principal*/

		listViewMenu=(ListView)findViewById(R.id.listViewMenu);
		fakeLayout = (View)findViewById(R.id.fake_layout);

		listViewMenu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , itensMenu));

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

		/** listener usado quando o menu esta aberto e ocorre um evento de touch na tela principal, esconde o menu*/
		layoutPrincipal.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					if (menuOpen == true) {
						animSlideLeft();
					}
				}

				return false;
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
} 