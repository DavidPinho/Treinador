package com.treinador.activity;

import com.treinador.R;
import com.treinador.model.Mark;
import com.treinador.model.Muscle;
import com.treinador.model.db.MarkDB;
import com.treinador.model.db.MuscleDB;

import android.app.Activity;
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
	EditText txt_final_date;
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
		txt_final_date = (EditText) findViewById(R.id.txt_row2FinalDate_MarkRegister);
		
		Mark mark = (Mark) getIntent().getSerializableExtra("mark");
		
		update=false;
		if(mark !=null){
			txt_description.setText(mark.getDescription());
			txt_final_date.setText(mark.getFinalDate());
			txt_initial_date.setText(mark.getInitialDate());
			id= mark.getIdMark();
			update=true;
		}
		
		

		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Mark m = new Mark();
				m.setDescription(txt_description.getText().toString());
				m.setInitialDate(txt_initial_date.getText().toString());
				m.setFinalDate(txt_final_date.getText().toString());
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
				RegisterMark.this.finish();
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
