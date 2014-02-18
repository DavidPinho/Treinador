package com.treinador.activity;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.model.ExerciseType;
import com.treinador.model.db.ExerciseTypeDB;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterExerciseType extends Activity {
	
	EditText description;
	Button btn_create;
	
	private boolean update=false;
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_exercise_type);
		
		description = (EditText) findViewById(R.id.txt_description_exercise_type);
		btn_create = (Button) findViewById(R.id.btn_register);
		
		ExerciseType exerciseType = (ExerciseType) getIntent().getSerializableExtra("exerciseType");
		update=false;
		if(exerciseType!=null){
			fillFields(exerciseType);
			update=true;
			id = exerciseType.getIdExerciseType();
		}
		
		
		btn_create.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				
				ExerciseType exerciseType = new ExerciseType();
				exerciseType.setDescription(description.getText().toString());
				
				ExerciseTypeDB exerciseTypeDB = new ExerciseTypeDB(getApplicationContext());
				if (update){	
					exerciseType.setIdExerciseType(id);
					exerciseTypeDB.update(exerciseType);
				}
				else
					exerciseTypeDB.insert(exerciseType);
							
				Intent intent = new Intent(RegisterExerciseType.this, ExerciseTypeList.class);
				RegisterExerciseType.this.startActivity(intent);
				//RegisterExerciseType.this.finish();
			
			}
		});
	}
	

	
	private void fillFields(ExerciseType et ){
		description.setText(et.getDescription());
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipo_exercicio, menu);
		return true;
	}
	

}
