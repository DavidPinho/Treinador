package com.treinador.activity;

import java.util.List;

import com.treinador.R;
import com.treinador.model.Athlete;
import com.treinador.model.db.AthleteDB;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterAthlete extends Activity {

	TextView lbl_name;
	EditText txt_name;
	
	TextView lbl_birthDate;
	EditText txt_birthDate;
	
	TextView lbl_gender;
	Spinner spn_gender;
	
	Button btn_create;
	
	private boolean update=false;
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_athlete);
		
		lbl_name = (TextView) findViewById(R.id.lbl_name);
		txt_name = (EditText) findViewById(R.id.txt_name);
		lbl_birthDate = (TextView) findViewById(R.id.lbl_birthDate);
		txt_birthDate = (EditText) findViewById(R.id.txt_birthDate);
		lbl_gender = (TextView) findViewById(R.id.lbl_gender);
		spn_gender = (Spinner) findViewById(R.id.spn_gender);
		btn_create = (Button) findViewById(R.id.btn_register);
		
		Athlete athlete = (Athlete) getIntent().getSerializableExtra("athlete");
		update=false;
		if(athlete!=null){
			fillFields(athlete);
			update=true;
			id = athlete.getIdAthlete();
		}
		
		
		btn_create.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				
				Athlete athlete = new Athlete();
				athlete.setName(txt_name.getText().toString());
				athlete.setGender(spn_gender.getSelectedItem().toString());
				athlete.setBirthDate(txt_birthDate.getText().toString());
				
				
				AthleteDB athleteDB = new AthleteDB(getApplicationContext());
				if (update){	
					athlete.setIdAthlete(id);
					athleteDB.update(athlete);
				}
				else
					athleteDB.insert(athlete);
							
				Intent intent = new Intent(RegisterAthlete.this,AthleteList.class);
				RegisterAthlete.this.startActivity(intent);
				RegisterAthlete.this.finish();
			
			}
		});
		
	}
	
	private void clearFields(){
		txt_name.setText("");
		txt_birthDate.setText("");
		spn_gender.setSelection(0);
	}
	
	private void fillFields(Athlete a ){
		txt_name.setText(a.getName());
		txt_birthDate.setText(a.getBirthDate());
		if(a.getGender().equals("Masculino"))
			spn_gender.setSelection(0);
		else
			spn_gender.setSelection(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_atleta, menu);
		return true;
	}

}
