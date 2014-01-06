package com.treinador.activity;

import java.util.ArrayList;
import java.util.List;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.model.Muscle;
import com.treinador.model.Size;
import com.treinador.model.db.MuscleDB;
import com.treinador.model.db.SizeDB;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

public class RegisterSize extends Activity {

	ExpandableListView expListView;
	
	String[] muscles = {"Musculo 1"};
	Spinner spn_muscle;
	EditText txt_value;
	EditText txt_date;
	Button btn_register;
	
	MuscleDB muscleDB;
	SizeDB sizeDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_size);
		muscleDB = new MuscleDB(getApplicationContext());
		sizeDB = new SizeDB(getApplicationContext());
		spn_muscle = (Spinner) findViewById(R.id.spn_muscle);
		txt_value = (EditText) findViewById(R.id.txt_value);
		txt_date = (EditText) findViewById(R.id.txt_date);
		btn_register = (Button) findViewById(R.id.btn_register_size);
			
		List<Muscle> labels = muscleDB.getAll();
		ArrayAdapter<Muscle> dataAdapter = new ArrayAdapter<Muscle>(this,android.R.layout.simple_list_item_1, labels);		
		spn_muscle.setAdapter(dataAdapter);
		
		
		btn_register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Size s = new Size();
				s.setDate(txt_date.getText().toString());
				s.setIdAthlete(AthleteList.athleteSelected.getIdAthlete());
				s.setIdMuscle(((Muscle)spn_muscle.getSelectedItem()).getIdMuscle());
				s.setSizeValue(Float.parseFloat(txt_value.getText().toString()));
				
				sizeDB.insert(s);
				//TODO implement update
				
				Intent newIntent = new Intent(RegisterSize.this, SizeList.class);
				RegisterSize.this.startActivity(newIntent);
				RegisterSize.this.finish();			
				
				
			}
		});
		
		 
		 

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registers_size, menu);
		return true;
	}

}
