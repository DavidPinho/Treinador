package com.treinador.activity;

import java.util.List;

import com.treinador.R;
import com.treinador.mask.Mask;
import com.treinador.model.Athlete;
import com.treinador.model.Mark;
import com.treinador.model.db.AthleteDB;
import com.treinador.model.db.MarkDB;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
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
		
		txt_birthDate.addTextChangedListener(Mask.insert("##/##/####", txt_birthDate));

		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
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
				
				//tratamento data
				boolean s;
				int dia = -1;
				int mes = -1;
				int ano = -1;
				String erroData = "Data informada inválida.";
				
					s = false;
					//tratando data
					dia = -1;
					mes = -1;
					ano = -1;
					
					//Se captura algum erro vai imediatamente para o catch, executa o que tem no catch
					//e continua o restante da execução após o catch.
					try{
						//a captura do endindex é a "posição-1"
						dia = Integer.parseInt(txt_birthDate.getText().toString().substring(0, 2));
						mes = Integer.parseInt(txt_birthDate.getText().toString().substring(3, 5));
						ano = Integer.parseInt(txt_birthDate.getText().toString().substring(6, 10));
					}catch(Exception err){
						alert.setMessage(erroData).setNeutralButton("OK", null);
						s = true;//continua while
						alert.show();
					}
					if(!s){
						if(txt_birthDate.getText().toString().length() != 10){
							alert.setMessage(erroData).setNeutralButton("OK", null);
							s = true;
							alert.show();

						}else if(dia > 31 || dia < 1){
							alert.setMessage("Dia inválido").setNeutralButton("OK", null);
							s = true;
							alert.show();

						} else if(mes > 12 || mes < 1){
							alert.setMessage("Mês inválido").setNeutralButton("OK", null);;
							s = true;
							alert.show();

						}else if(ano < 0){
							alert.setMessage("Ano inválido").setNeutralButton("OK", null);;
							s = true;
							alert.show();

						}else{
							Athlete athlete = new Athlete();
							athlete.setName(txt_name.getText().toString());
							athlete.setGender(spn_gender.getSelectedItem().toString());
							athlete.setBirthDate(Mask.unmask(txt_birthDate.getText().toString()));
							
							
							AthleteDB athleteDB = new AthleteDB(getApplicationContext());
							if (update){	
								athlete.setIdAthlete(id);
								athleteDB.update(athlete);
							}
							else
								athleteDB.insert(athlete);
										
							Intent intent = new Intent(RegisterAthlete.this,AthleteList.class);
							RegisterAthlete.this.startActivity(intent);
						}
				
					}
				
				
				//RegisterAthlete.this.finish();
			
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
