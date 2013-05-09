package com.cm.cashmanager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainCenterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = new Intent(this, ExtratoActivity.class);
		
		startActivity(intent);
	}

}
