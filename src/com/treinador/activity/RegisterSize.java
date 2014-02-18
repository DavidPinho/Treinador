package com.treinador.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.mask.Mask;
import com.treinador.model.Muscle;
import com.treinador.model.Size;
import com.treinador.model.db.MuscleDB;
import com.treinador.model.db.SizeDB;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

public class RegisterSize extends Activity {

	
	Spinner spn_muscle;
	EditText txt_value;
	EditText txt_date;
	Button btn_register;
	boolean update =false;
	MuscleDB muscleDB;
	SizeDB sizeDB;
	int id;
	
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
			
		txt_date.addTextChangedListener(Mask.insert("##/##/####", txt_date));
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);

		
		List<Muscle> labels = muscleDB.getAll();
		ArrayAdapter<Muscle> dataAdapter = new ArrayAdapter<Muscle>(this,android.R.layout.simple_list_item_1, labels);		
		spn_muscle.setAdapter(dataAdapter);
		
		
		Size s = (Size) getIntent().getSerializableExtra("size");
		update=false;
		if(s!=null){
			int position = 0;
			int i = 0;
			for (Muscle muscle : muscleDB.getAll()) {
				if(muscle.getIdMuscle()==s.getIdMuscle()){
					position = i;					
					break;					
				}
				i++;	
			} 
			
			update=true;
			id=s.getIdSize();
			spn_muscle.setSelection(position);
			txt_value.setText(Double.toString(s.getSizeValue()));
			txt_date.setText(s.getDate());
			
		}
		
		
		btn_register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
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
						dia = Integer.parseInt(txt_date.getText().toString().substring(0, 2));
						mes = Integer.parseInt(txt_date.getText().toString().substring(3, 5));
						ano = Integer.parseInt(txt_date.getText().toString().substring(6, 10));
					}catch(Exception err){
						alert.setMessage(erroData).setNeutralButton("OK", null);
						s = true;//continua while
						alert.show();
					}
					if(!s){
						if(txt_date.getText().toString().length() != 10){
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
						}
						
						//s=false
						if(s){
							Size size = new Size();
							size.setDate(Mask.unmask(txt_date.getText().toString()));
							size.setIdAthlete(AthleteList.athleteSelected.getIdAthlete());
							size.setIdMuscle(((Muscle)spn_muscle.getSelectedItem()).getIdMuscle());
							size.setSizeValue(Float.parseFloat(txt_value.getText().toString()));
							
							if(update){
								size.setIdSize(id);
								sizeDB.update(size);
							}else{
								sizeDB.insert(size);
							}
							
							Intent newIntent = new Intent(RegisterSize.this, SizeList.class);
							RegisterSize.this.startActivity(newIntent);
							//RegisterSize.this.finish();
						}
					}
				
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
