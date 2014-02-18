package com.treinador.activity;

import java.util.Date;
import java.util.List;

import net.londatiga.android.NewQAAdapter;

import com.treinador.R;
import com.treinador.R.id;
import com.treinador.model.Exercise;
import com.treinador.model.ExerciseType;
import com.treinador.model.Muscle;
import com.treinador.model.db.ExerciseDB;
import com.treinador.model.db.ExerciseTypeDB;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class RegisterExercise extends Activity{
	
	private EditText instructions;
	private EditText weight;
	private EditText repetitionsNumber;
	private EditText duration;
	private Spinner spn_exercise_type;
	private Button btn_create;	
	
	private boolean update=false;
	
	private List<ExerciseType> labels;
	private ArrayAdapter<ExerciseType> dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_exercise);
		
		ScrollView sv = (ScrollView)findViewById(R.id.sv_register_exercise); 
		sv.setVerticalScrollBarEnabled(false); 
		sv.setHorizontalScrollBarEnabled(false);

		instructions  = (EditText) findViewById(R.id.txt_row2Instructions__ExerciseRegister);
		weight = (EditText) findViewById(R.id.txt_row2Weight__ExerciseRegister);
		repetitionsNumber = (EditText) findViewById(R.id.txt_row2Repetitions__ExerciseRegister);
		duration = (EditText) findViewById(R.id.txt_row2Duration__ExerciseRegister);
		btn_create = (Button) findViewById(R.id.btn_Register__ExerciseRegister);
		spn_exercise_type = (Spinner) findViewById(R.id.spn_exercise_type);
		
		labels = new ExerciseTypeDB(getApplicationContext()).getAll();		
		dataAdapter = new ArrayAdapter<ExerciseType>(this, android.R.layout.simple_list_item_1, labels);		
		spn_exercise_type.setAdapter(dataAdapter);
		
		final Exercise exercise = (Exercise) getIntent().getSerializableExtra("exercise");

		update=false;
		if(exercise!=null){

			instructions.setText(exercise.getInstructions());
			weight.setText(Double.toString(exercise.getWeight()));
			repetitionsNumber.setText(Integer.toString(exercise.getRepetitions()));
			duration.setText(Double.toString(exercise.getDuration()));		
			
			ExerciseTypeDB exerciseTypeDB = new ExerciseTypeDB(getApplicationContext());
			
			SpinnerAdapter sa = spn_exercise_type.getAdapter();
			for(int i=0; i < spn_exercise_type.getAdapter().getCount(); i++){
				if( sa.getItem(i).toString().equals(exerciseTypeDB.getOne(exercise.getIdExerciseType()).getDescription()) ){
					spn_exercise_type.setSelection(i);
				}
			}
			
			update=true;
		}

		
		btn_create.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {

				ExerciseDB exerciseDB = new ExerciseDB(getApplicationContext());
				
				if (update){	

					try {
						exercise.setDuration(Double.parseDouble(duration.getText().toString()));
					} catch (Exception e) {
						exercise.setDuration(0.0);
					}
					
					exercise.setInstructions(instructions.getText().toString());
					
					try {
						exercise.setRepetitions(Integer.parseInt(repetitionsNumber.getText().toString()));
					} catch (Exception e) {
						exercise.setRepetitions(0);
					}
					
					try {
						exercise.setWeight(Double.parseDouble(weight.getText().toString()));
					} catch (Exception e) {
						exercise.setWeight(0.0);
					}
					exercise.setIdExerciseType( ((ExerciseType)spn_exercise_type.getSelectedItem()).getIdExerciseType() );
					
					exerciseDB.update(exercise);
				}
				else{
					Exercise exercise = new Exercise();
					
					exercise.setDate(AgendaList.getDate());
					
					try {
						exercise.setDuration(Double.parseDouble(duration.getText().toString()));
					} catch (Exception e) {
						exercise.setDuration(0.0);
					}
					
					exercise.setInstructions(instructions.getText().toString());
					
					try {
						exercise.setRepetitions(Integer.parseInt(repetitionsNumber.getText().toString()));
					} catch (Exception e) {
						exercise.setRepetitions(0);
					}
					
					try {
						exercise.setWeight(Double.parseDouble(weight.getText().toString()));
					} catch (Exception e) {
						exercise.setWeight(0.0);
					}
					
					exercise.setIdExerciseType( ((ExerciseType)spn_exercise_type.getSelectedItem()).getIdExerciseType() );
					exercise.setIdAthlete(AthleteList.athleteSelected.getIdAthlete());
					
					exerciseDB.insert(exercise);
				}
							
				Intent intent = new Intent(RegisterExercise.this, AgendaList.class);				
				RegisterExercise.this.startActivity(intent);
				//RegisterExercise.this.finish();
			
			}
		});
		

	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registers_exercise, menu);
		return true;
	}
	

}
