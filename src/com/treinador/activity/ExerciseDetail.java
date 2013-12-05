package com.treinador.activity;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.model.Athlete;
import com.treinador.model.Exercise;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ExerciseDetail extends Activity {
	
	ImageButton btn_calendar;
	ImageButton btn_athletes;
	ImageButton btn_graphics;
	ImageButton btn_lists;
	TextView lbl_exercise;
	TextView lbl_instructions;
	TextView lbl_date;
	TextView lbl_weight;
	TextView lbl_repetitions;
	TextView lbl_duration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_detail);
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage("Gráficos em breve!").setNeutralButton("OK", null);
		
		btn_athletes = (ImageButton) (findViewById(R.id.btn_athletes));
		btn_calendar = (ImageButton) (findViewById(R.id.btn_calendar));
		btn_graphics = (ImageButton) (findViewById(R.id.btn_graphics));
		btn_lists = (ImageButton) (findViewById(R.id.btn_lists));
		lbl_date = (TextView) (findViewById(R.id.lbl_date_value));
		lbl_duration = (TextView) (findViewById(R.id.lbl_duration_exercise_detail_value));
		lbl_exercise = (TextView) (findViewById(R.id.lbl_description_value));
		lbl_instructions = (TextView) (findViewById(R.id.lbl_instructions_value));
		lbl_repetitions = (TextView) (findViewById(R.id.lbl_repetitions_exercise_detail_value));
		lbl_weight = (TextView) (findViewById(R.id.lbl_weight_exercise_detail_value));
		
		Exercise exerciseAux = (Exercise) getIntent().getSerializableExtra("exercise");
		
		lbl_date.setText(exerciseAux.getDate());
		lbl_duration.setText(Float.toString(exerciseAux.getDuration()));
		lbl_exercise.setText(Integer.toString(exerciseAux.getIdExercise()));
		lbl_instructions.setText(exerciseAux.getInstructions());
		lbl_repetitions.setText(Integer.toString(exerciseAux.getRepetitions()));
		lbl_weight.setText(Float.toString(exerciseAux.getWeight()));
		
		ActionItem size = new ActionItem();
		size.setTitle("Medida");
		size.setIcon(getResources().getDrawable(R.drawable.ic_size));
		
		ActionItem exercise = new ActionItem();
		exercise.setTitle("Exercício");
		exercise.setIcon(getResources().getDrawable(R.drawable.ic_exercise));
		
		ActionItem mark = new ActionItem();
		mark.setTitle("Marco");
		mark.setIcon(getResources().getDrawable(R.drawable.ic_mark));

		ActionItem muscle = new ActionItem();
		muscle.setTitle("Músculo");
		muscle.setIcon(getResources().getDrawable(R.drawable.ic_muscle));

		
		final QuickAction mQuickAction  = new QuickAction(this);
		 
		mQuickAction.addActionItem(size);
		mQuickAction.addActionItem(exercise);
		mQuickAction.addActionItem(mark);
		mQuickAction.addActionItem(muscle);
		
		btn_calendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intentNew = new Intent(ExerciseDetail.this, CalendarActivities.class);
				ExerciseDetail.this.startActivity(intentNew);
				ExerciseDetail.this.finish();	
				
			}
		});
		
		btn_athletes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(ExerciseDetail.this, AthleteList.class);
				ExerciseDetail.this.startActivity(intentNew);
				ExerciseDetail.this.finish();				
			}
		});
		
		
		btn_graphics.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				alert.show();
				
			}
		});
		
		
		btn_lists.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mQuickAction.show(v);
				
			}
		});
		
		mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {
				if(pos==0){
					
				}else if (pos==1) {
					Intent intentNew = new Intent(ExerciseDetail.this, ExerciseTypeList.class);
					ExerciseDetail.this.startActivity(intentNew);
					ExerciseDetail.this.finish();
				}else if (pos==2) {
					
				}else if (pos==3) {
					Intent intentNew = new Intent(ExerciseDetail.this, MuscleList.class);
					ExerciseDetail.this.startActivity(intentNew);
					ExerciseDetail.this.finish();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercise_detail, menu);
		return true;
	}

}
