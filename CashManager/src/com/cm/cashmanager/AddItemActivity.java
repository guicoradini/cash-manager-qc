package com.cm.cashmanager;

import java.util.Calendar;

import android.app.DatePickerDialog.OnDateSetListener;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddItemActivity extends FragmentActivity  {

	/** usado para saber a largura da tela*/
	private Display display;	

	private EditText valorData;

	private int mYear;
	private int mMonth;
	private int mDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_item);

		valorData = (EditText) findViewById(R.id.valorData);

		valorData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				showDatePicker();

			}
		});


		/** usado para realizar a animação*/
		display =  getWindowManager().getDefaultDisplay();

		Point screenDimensions = new Point();

		display.getSize(screenDimensions);

		getWindow().setLayout(screenDimensions.x - 100, screenDimensions.y - 400);
	}

	private void showDatePicker() {

		DatePickerFragment date = new DatePickerFragment();
		/**
		 * Set Up Current Date Into dialog
		 */
		Calendar calender = Calendar.getInstance();
		Bundle args = new Bundle();
		args.putInt("year", calender.get(Calendar.YEAR));
		args.putInt("month", calender.get(Calendar.MONTH));
		args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		date.setArguments(args);
		/**
		 * Set Call back to capture selected date
		 */
		date.setCallBack(ondate);
		date.show(getSupportFragmentManager(), "Date Picker");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item, menu);
		return true;
	}

	OnDateSetListener ondate = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			
			updateDisplay();
			
		}
	};

	private void updateDisplay() {
		valorData.setText(new StringBuilder().append(mDay).append("/").append(mMonth + 1).append("/").append(mYear));
	}

}
