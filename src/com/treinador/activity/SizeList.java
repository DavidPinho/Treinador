package com.treinador.activity;

import java.util.ArrayList;
import java.util.List;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.adapter.MuscleAdapter;
import com.treinador.adapter.SizeAdapter;
import com.treinador.model.Muscle;
import com.treinador.model.Size;
import com.treinador.model.db.SizeDB;

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

public class SizeList extends Activity {

	ListView listView;
	SizeAdapter adapter;	
	ImageButton btn_calendar;
	ImageButton btn_athletes;
	ImageButton btn_graphics;
	ImageButton btn_lists;
	ArrayList<Size> sizes;
	SizeDB sizeDB;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_size);
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setMessage("Gráficos em breve!").setNeutralButton("OK", null);
		sizeDB = new SizeDB(getApplicationContext());
		
		sizes = (ArrayList<Size>) sizeDB.getAll(AthleteList.athleteSelected.getIdAthlete());
		sizes = (ArrayList<Size>) sortList(sizes);
		
		
			
		adapter = new SizeAdapter(this, R.layout.list_view_adapter_size, sizes);
		
		listView = (ListView) findViewById(R.id.lv_size);
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
				Intent intentNew = new Intent(SizeList.this, CalendarActivities.class);
				SizeList.this.startActivity(intentNew);
				SizeList.this.finish();	
				
			}
		});
		
		btn_athletes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(SizeList.this, AthleteList.class);
				SizeList.this.startActivity(intentNew);
				SizeList.this.finish();				
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
					/*
					Intent intentNew = new Intent(SizeList.this, SizeList.class);
					SizeList.this.startActivity(intentNew);
					SizeList.this.finish();
					*/
				}else if (pos==1) {
					Intent intentNew = new Intent(SizeList.this, ExerciseTypeList.class);
					SizeList.this.startActivity(intentNew);
					SizeList.this.finish();
				}else if (pos==2) {
					Intent intentNew = new Intent(SizeList.this, MarkList.class);
					SizeList.this.startActivity(intentNew);
					SizeList.this.finish();	

					
				}else if (pos==3) {
					Intent intentNew = new Intent(SizeList.this, MuscleList.class);
					SizeList.this.startActivity(intentNew);
					SizeList.this.finish();
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
			Intent intentNew = new Intent(SizeList.this, RegisterSize.class);
			SizeList.this.startActivity(intentNew);
			SizeList.this.finish();	
		}
	    return true;
	} 

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.lv_size) {
		String[] menuItems = getResources().getStringArray(R.array.options_list_2); 
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  
	  String[] menuItems = getResources().getStringArray(R.array.options_list_2);
	  String menuItemName = menuItems[menuItemIndex];
	  
	  Size size = sizes.get(info.position);
	  
	  if(menuItemName.equals(menuItems[0])){
		  Intent intentNew = new Intent(SizeList.this, RegisterSize.class);
			intentNew.putExtra("size", size);
			SizeList.this.startActivity(intentNew);
			SizeList.this.finish();
		  
	  }else if (menuItemName.equals(menuItems[1])) {
		  	sizeDB.delete(size.getIdSize());
			adapter.clear();
			ArrayList<Size> sizeDelete = (ArrayList<Size>) sizeDB.getAll(AthleteList.athleteSelected.getIdAthlete());	
			sizeDelete = (ArrayList<Size>) sortList(sizeDelete);
			adapter.restoreList();				
			adapter.addAll(sizeDelete);
	        }
	  
	  return true;
	}
	
	
	public  List<Size> sortList(List<Size> sizes2){
		Size aux;
		for(int i = 0; i<sizes2.size(); i++){
			for(int j = 0; j<(sizes2.size() - 1); j++){ 
				String date1 = sizes2.get(j).getDate();
				String dias = date1.substring(0, 2);
				int diasDate1 = Integer.parseInt(dias);
				diasDate1 = diasDate1 + (Integer.parseInt(date1.substring(3, 5))*30);
				diasDate1 = diasDate1 + (Integer.parseInt(date1.substring(6, 10))*365);
				
				String date2 = sizes2.get(j+1).getDate();
				int diasDate2 = Integer.parseInt(date2.substring(0, 2));
				diasDate2 = diasDate2 + (Integer.parseInt(date2.substring(3, 5))*30);
				diasDate2 = diasDate2 + (Integer.parseInt(date2.substring(6, 10))*365);
				if(diasDate1>diasDate2){
					aux = sizes2.get(j);
					sizes2.add(j, sizes2.get(j+1));
					sizes2.remove(j+1);
					sizes2.add(j+1, aux);
					sizes2.remove(j+2);
				} 
			}
		}
		
		return sizes2;		
	}
	

}
