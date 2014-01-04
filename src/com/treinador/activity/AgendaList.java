package com.treinador.activity;

import java.util.ArrayList;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.adapter.AgendaAdapter;
import com.treinador.adapter.MuscleAdapter;
import com.treinador.model.Athlete;
import com.treinador.model.Exercise;
import com.treinador.model.Muscle;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class AgendaList extends Activity {
	
	ListView listView;
	AgendaAdapter adapter;	
	ImageButton btn_calendar;
	ImageButton btn_athletes;
	ImageButton btn_graphics;
	ImageButton btn_lists;
	ArrayList<Exercise> exercises;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_agenda);
		
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage("Gráficos em breve!").setNeutralButton("OK", null);
		Exercise m = new Exercise();
		m.setInstructions("Supino Reto");
		m.setDate("10/12/2013");
		m.setDuration(30);
		m.setRepetitions(10);
		m.setWeight(40);
		m.setIdExercise(1);
		Exercise m2 = new Exercise();
		m2.setInstructions("Rosca Direta");
		m2.setDate("10/12/2013");
		m2.setDuration(60);
		m2.setRepetitions(12);
		m2.setWeight(35);
		m2.setIdExercise(2);
		exercises = new ArrayList<Exercise>();
		exercises.add(m);
		exercises.add(m2);
		
		adapter = new AgendaAdapter(this, R.layout.list_view_adapter_agenda, exercises);
		
		listView = (ListView) findViewById(R.id.lv_agenda);
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
		
		
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
				Intent intentNew = new Intent(AgendaList.this, CalendarActivities.class);
				AgendaList.this.startActivity(intentNew);
				AgendaList.this.finish();	
				
			}
		});
		
		btn_athletes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(AgendaList.this, AthleteList.class);
				AgendaList.this.startActivity(intentNew);
				AgendaList.this.finish();				
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
					Intent intentNew = new Intent(AgendaList.this, SizeList.class);
					AgendaList.this.startActivity(intentNew);
					AgendaList.this.finish();	
					
				}else if (pos==1) {
					Intent intentNew = new Intent(AgendaList.this, ExerciseTypeList.class);
					AgendaList.this.startActivity(intentNew);
					AgendaList.this.finish();
				}else if (pos==2) {
					Intent intentNew = new Intent(AgendaList.this, MarkList.class);
					AgendaList.this.startActivity(intentNew);
					AgendaList.this.finish();
					
				}else if (pos==3) {
					Intent intentNew = new Intent(AgendaList.this, MuscleList.class);
					AgendaList.this.startActivity(intentNew);
					AgendaList.this.finish();
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
			Intent intentNew = new Intent(AgendaList.this, RegisterExercise.class);
			AgendaList.this.startActivity(intentNew);
			AgendaList.this.finish();	
		}
	    return true;
	} 

	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.lv_agenda) {
	    //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	   // menu.setHeaderTitle(athletesList.get(info.position).getName());
		String[] menuItems = getResources().getStringArray(R.array.options_list); 
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  
	  String[] menuItems = getResources().getStringArray(R.array.options_list);
	  String menuItemName = menuItems[menuItemIndex];
	  
	  Exercise exercise = exercises.get(info.position);
	  
	  if(menuItemName.equals(menuItems[0])){
		  Intent intentNew = new Intent(AgendaList.this, ExerciseDetail.class);
		  intentNew.putExtra("exercise", exercise);
		  AgendaList.this.startActivity(intentNew);
		  AgendaList.this.finish();
		  
	  }else if (menuItemName.equals(menuItems[1])) {
		  		
	        }else if (menuItemName.equals(menuItems[2])) {
				
			}
	  
	  return true;
	}
}
