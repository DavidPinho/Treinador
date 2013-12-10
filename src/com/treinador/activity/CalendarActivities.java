package com.treinador.activity;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import com.treinador.R;
import com.treinador.adapter.ExerciseTypeAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageButton;

public class CalendarActivities extends Activity{

	
	ImageButton btn_calendar;
	ImageButton btn_athletes;
	ImageButton btn_graphics;
	ImageButton btn_lists;
	CalendarView calendar;
	
	
	 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_activities);
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage("Gráficos em breve!").setNeutralButton("OK", null);
		
		btn_athletes = (ImageButton) (findViewById(R.id.btn_athletes));
		btn_calendar = (ImageButton) (findViewById(R.id.btn_calendar));
		btn_graphics = (ImageButton) (findViewById(R.id.btn_graphics));
		btn_lists = (ImageButton) (findViewById(R.id.btn_lists));
		calendar = (CalendarView) (findViewById(R.id.calendar_activities));
		
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
		
		btn_athletes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(CalendarActivities.this, AthleteList.class);
				CalendarActivities.this.startActivity(intentNew);
				CalendarActivities.this.finish();				
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
					Intent intentNew = new Intent(CalendarActivities.this, SizeList.class);
					CalendarActivities.this.startActivity(intentNew);
					CalendarActivities.this.finish();	
					
				}else if (pos==1) {
					Intent intentNew = new Intent(CalendarActivities.this, ExerciseTypeList.class);
					CalendarActivities.this.startActivity(intentNew);
					CalendarActivities.this.finish();					
				}else if (pos==2) {
					Intent intentNew = new Intent(CalendarActivities.this, MarkList.class);
					CalendarActivities.this.startActivity(intentNew);
					CalendarActivities.this.finish();	
					
				}else if (pos==3) {
					Intent intentNew = new Intent(CalendarActivities.this, MuscleList.class);
					CalendarActivities.this.startActivity(intentNew);
					CalendarActivities.this.finish();
				}
				
			}
		});
		
		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				Intent intentNew = new Intent(CalendarActivities.this, AgendaList.class);
				intentNew.putExtra("dataLongMiliseconds", (Long)calendar.getDate());
				CalendarActivities.this.startActivity(intentNew);
				CalendarActivities.this.finish();
				
			}
		});

	}
	

}
