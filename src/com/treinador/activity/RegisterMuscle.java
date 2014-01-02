package com.treinador.activity;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.model.Muscle;
import com.treinador.model.db.MuscleDB;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterMuscle extends Activity {
	
	EditText txt_description;
	Button btn_register;
	int id;
	boolean update;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_muscle);

		btn_register = (Button) findViewById(R.id.btn_register);
		txt_description = (EditText) findViewById(R.id.txt_description);
		
		Muscle muscle = (Muscle) getIntent().getSerializableExtra("muscle");
		
		update=false;
		if(muscle !=null){
			txt_description.setText(muscle.getDescription());
			id= muscle.getIdMuscle();
			update=true;
		}
		
		

		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Muscle m = new Muscle();
				m.setDescription(txt_description.getText().toString());
				
				MuscleDB muscleDB = new MuscleDB(getApplicationContext());
				if(update){
					m.setIdMuscle(id);
					muscleDB.update(m);
				}else{
					muscleDB.insert(m);
				}
				Intent intent = new Intent(RegisterMuscle.this, MuscleList.class);
				RegisterMuscle.this.startActivity(intent);	
				RegisterMuscle.this.finish();
			}
		});

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registers_muscle, menu);
		return true;
	}

}
