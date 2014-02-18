package com.treinador.activity;

import com.treinador.R;
import com.treinador.mask.Mask;
import com.treinador.model.Mark;
import com.treinador.model.Muscle;
import com.treinador.model.Size;
import com.treinador.model.db.MarkDB;
import com.treinador.model.db.MuscleDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterMark extends Activity{
	
	
	EditText txt_description;
	EditText txt_initial_date;
	Button btn_register;
	int id;
	boolean update;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_mark);		
		
		btn_register = (Button) findViewById(R.id.btn_buttonRegister_MarkRegister);
		txt_description = (EditText) findViewById(R.id.txt_row2Description_MarkRegister);
		txt_initial_date = (EditText) findViewById(R.id.txt_row2Date_MarkRegister);
		txt_initial_date.addTextChangedListener(Mask.insert("##/##/####", txt_initial_date));
		
		Mark mark = (Mark) getIntent().getSerializableExtra("mark");
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		update=false;
		if(mark !=null){
			txt_description.setText(mark.getDescription());
			txt_initial_date.setText(mark.getInitialDate());
			id= mark.getIdMark();
			update=true;
		}
		
		

		btn_register.setOnClickListener(new OnClickListener() {

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
						dia = Integer.parseInt(txt_initial_date.getText().toString().substring(0, 2));
						mes = Integer.parseInt(txt_initial_date.getText().toString().substring(3, 5));
						ano = Integer.parseInt(txt_initial_date.getText().toString().substring(6, 10));
					}catch(Exception err){
						alert.setMessage(erroData).setNeutralButton("OK", null);
						s = true;//continua while
						alert.show();
					}
					if(!s){
						if(txt_initial_date.getText().toString().length() != 10){
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
							Mark m = new Mark();
							m.setDescription(txt_description.getText().toString());
							m.setInitialDate(Mask.unmask(txt_initial_date.getText().toString()));
							m.setFinalDate("");
							m.setIdAthlete(AthleteList.athleteSelected.getIdAthlete());
							
							MarkDB markDB = new MarkDB(getApplicationContext());
							if(update){
								m.setIdMark(id);
								markDB.update(m);
							}else{
								markDB.insert(m);
							}
							Intent intent = new Intent(RegisterMark.this, MarkList.class);
							RegisterMark.this.startActivity(intent);	
							//RegisterMark.this.finish();
						}
				
					}
			}
		});

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registers_mark, menu);
		return true;
	}
	
	

}
