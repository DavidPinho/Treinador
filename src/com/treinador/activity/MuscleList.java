package com.treinador.activity;

import java.util.ArrayList;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.adapter.AthleteAdapter;
import com.treinador.adapter.MuscleAdapter;
import com.treinador.model.Athlete;
import com.treinador.model.Muscle;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class MuscleList extends Activity {

	
	ListView listView;
	MuscleAdapter adapter;	
	ImageButton btn_calendar;
	ImageButton btn_athletes;
	ImageButton btn_graphics;
	ImageButton btn_lists;
	ArrayList<Muscle> muscles;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_muscle);
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setTitle("Alerta").setMessage("Selecione apenas 1 atleta!").setNeutralButton("OK", null);
		Muscle m = new Muscle();
		m.setDescription("Bíceps");
		Muscle m2 = new Muscle();
		m2.setDescription("Tríceps");
		muscles = new ArrayList<Muscle>();
		muscles.add(m);
		muscles.add(m2);
		
		adapter = new MuscleAdapter(this, R.layout.list_view_adapter_muscle, muscles);
		
		listView = (ListView) findViewById(R.id.lv_muscle);
		listView.setAdapter(adapter);
		
		
		btn_athletes = (ImageButton) (findViewById(R.id.btn_athletes));
		btn_calendar = (ImageButton) (findViewById(R.id.btn_calendar));
		btn_graphics = (ImageButton) (findViewById(R.id.btn_graphics));
		btn_lists = (ImageButton) (findViewById(R.id.btn_lists));
		
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
				Intent intentNew = new Intent(MuscleList.this, CalendarActivities.class);
				MuscleList.this.startActivity(intentNew);
				MuscleList.this.finish();	
				
			}
		});
		
		btn_athletes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(MuscleList.this, AthleteList.class);
				MuscleList.this.startActivity(intentNew);
				MuscleList.this.finish();				
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
					
				}else if (pos==2) {
					
				}else if (pos==3) {
					/*Intent intentNew = new Intent(MuscleList.this, MuscleList.class);
					MuscleList.this.startActivity(intentNew);
					MuscleList.this.finish();*/
				}
				
			}
		});

	}
	

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   

		if(item.getItemId()== R.id.action_create){
			Intent intentNew = new Intent(MuscleList.this, RegisterMuscle.class);
			MuscleList.this.startActivity(intentNew);
			MuscleList.this.finish();	
		}
	    return true;
	} 


}
