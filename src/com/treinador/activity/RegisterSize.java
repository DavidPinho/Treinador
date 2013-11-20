package com.treinador.activity;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class RegisterSize extends Activity {

	ExpandableListView expListView;
	
	String[] muscles = {"Musculo 1"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_size);
		
		 
		 expListView = (ExpandableListView)findViewById(R.id.expListView);

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registers_size, menu);
		return true;
	}

}
