package com.treinador.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.adapter.MarkAdapter;
import com.treinador.model.Mark;
import com.treinador.model.db.MarkDB;


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

public class MarkList extends Activity {

	ListView listView;
	ArrayList<Mark> marks;
	MarkAdapter adapter;
	MarkDB markDB;
	
	
	ImageButton btn_calendar;
	ImageButton btn_athletes;
	ImageButton btn_graphics;
	ImageButton btn_lists;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_mark);
		
		markDB = new MarkDB(getApplicationContext());
		
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage("Gráficos em breve!").setNeutralButton("OK", null);
		
		
		marks = (ArrayList<Mark>) markDB.getAll();
		
		
		adapter = new MarkAdapter(this, R.layout.list_view_adapter_mark, (ArrayList<Mark>) marks);
		
		listView = (ListView) findViewById(R.id.lv_mark);
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
				Intent intentNew = new Intent(MarkList.this, CalendarActivities.class);
				MarkList.this.startActivity(intentNew);
				MarkList.this.finish();	
				
			}
		});
		
		btn_athletes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(MarkList.this, AthleteList.class);
				MarkList.this.startActivity(intentNew);
				MarkList.this.finish();				
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
					Intent intentNew = new Intent(MarkList.this, SizeList.class);
					MarkList.this.startActivity(intentNew);
					MarkList.this.finish();	
					
				}else if (pos==1) {
					Intent intentNew = new Intent(MarkList.this, ExerciseTypeList.class);
					MarkList.this.startActivity(intentNew);
					MarkList.this.finish();
				}else if (pos==2) {
					/*
					Intent intentNew = new Intent(MarkList.this, MarkList.class);
					MarkList.this.startActivity(intentNew);
					MarkList.this.finish();
					*/	
					
				}else if (pos==3) {
					Intent intentNew = new Intent(MarkList.this, MuscleList.class);
					MarkList.this.startActivity(intentNew);
					MarkList.this.finish();
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
			Intent intentNew = new Intent(MarkList.this, RegisterMark.class);
			MarkList.this.startActivity(intentNew);
			MarkList.this.finish();	
		}
	    return true;
	} 
	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.lv_mark) {
		String[] menuItems = {"Finalizar", "Atualizar", "Excluir"};
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  
	  String[] menuItems = {"Finalizar", "Atualizar", "Excluir"};
	  String menuItemName = menuItems[menuItemIndex];
	  
	  Mark mark = marks.get(info.position);
	  
	  
	  if(menuItemName.equals(menuItems[0])){
		  if(mark.getFinalDate()==null || mark.getFinalDate().equals("")){
			  SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
			  mark.setFinalDate(simpleDate.format(new Date(System.currentTimeMillis())));
			  markDB.update(mark);
		  }
		  else{
			  AlertDialog.Builder alert2 = new AlertDialog.Builder(this).setMessage("Marco já finalizado!").setNeutralButton("OK", null);
			  alert2.show();
		  }		  
		  
	  }else if(menuItemName.equals(menuItems[1])){
		    Intent intentNew = new Intent(MarkList.this, RegisterMark.class);
			intentNew.putExtra("mark", mark);
			MarkList.this.startActivity(intentNew);
			MarkList.this.finish();
		  
	  }else if (menuItemName.equals(menuItems[2])) {
		  	markDB.delete(mark.getIdMark());
			adapter.clear();
			ArrayList<Mark> marksDelete = (ArrayList<Mark>) markDB.getAll();	
			adapter.restoreList();				
			adapter.addAll(marksDelete);
	        }
	  
	  return true;
	}

	
	
	

}
