package com.treinador.activity;

import java.util.Calendar;
import java.util.Date;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import com.treinador.R;
import com.treinador.adapter.ExerciseTypeAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageButton;

public class CalendarActivities extends Activity{

	private ImageButton btn_athletes;
	private ImageButton btn_graphics;
	private ImageButton btn_lists;
	private CalendarPickerView calendar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_activities);
		
		//tem que colocar estas linhas abaixo
		final Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 1);

		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.YEAR, -1);

		calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
		calendar.init(lastYear.getTime(), nextYear.getTime()) //
			.inMode(SelectionMode.SINGLE) //
		    .withSelectedDate(new Date());
		//até aqui
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage("Gráficos em breve!").setNeutralButton("OK", null);
		
		btn_athletes = (ImageButton) findViewById(R.id.btn_athletes);
		btn_graphics = (ImageButton) findViewById(R.id.btn_graphics);
		btn_lists = (ImageButton) findViewById(R.id.btn_lists);
		calendar = (CalendarPickerView) findViewById(R.id.calendar_view);

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
				//CalendarActivities.this.finish();				
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
				
		calendar.setOnDateSelectedListener(new OnDateSelectedListener() {
			
			@Override
			public void onDateUnselected(Date date) {
				
			}
			
			@Override
			public void onDateSelected(Date date) {
				Intent intentNew = new Intent(CalendarActivities.this, AgendaList.class);
				intentNew.putExtra("dataLongMiliseconds", (Long)calendar.getSelectedDate().getTime());
				CalendarActivities.this.startActivity(intentNew);
				//CalendarActivities.this.finish();
				
			}
		});
		
		mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {
				if(pos==0){
					Intent intentNew = new Intent(CalendarActivities.this, SizeList.class);
					CalendarActivities.this.startActivity(intentNew);
					//CalendarActivities.this.finish();	
					
				}else if (pos==1) {
					Intent intentNew = new Intent(CalendarActivities.this, ExerciseTypeList.class);
					CalendarActivities.this.startActivity(intentNew);
					//CalendarActivities.this.finish();					
				}else if (pos==2) {
					Intent intentNew = new Intent(CalendarActivities.this, MarkList.class);
					CalendarActivities.this.startActivity(intentNew);
					//CalendarActivities.this.finish();	
					
				}else if (pos==3) {
					Intent intentNew = new Intent(CalendarActivities.this, MuscleList.class);
					CalendarActivities.this.startActivity(intentNew);
					//CalendarActivities.this.finish();
				}
				
			}
		});

	}
	

	

}
