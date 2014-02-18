package com.treinador.activity;

import java.util.ArrayList;
import java.util.List;

import com.treinador.R;
import com.treinador.adapter.AthleteAdapter;
import com.treinador.model.Athlete;
import com.treinador.model.db.AthleteDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AthleteList extends Activity{
	
	ListView  listView;	
	AthleteDB athletedb;
	List<Athlete> athletesList;
	AthleteAdapter adapter;	
	public static Athlete athleteSelected;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_athlete);
		athleteSelected=null;
		athletedb = new AthleteDB(getApplicationContext());
		athletesList = athletedb.getAll();
		
		if(athletesList.isEmpty()){
			Intent intentNew = new Intent(AthleteList.this, RegisterAthlete.class);
			AthleteList.this.startActivity(intentNew);
			//AthleteList.this.finish();
		}
		
		adapter = new AthleteAdapter(this, R.layout.list_view_adapter_athlete,(ArrayList<Athlete>) athletesList);
		
		listView = (ListView) findViewById(R.id.lv_athlete);
		listView.setAdapter(adapter);
		registerForContextMenu(listView);
		
		

		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}
	
	//show menu automatic when initialized layout
	/*@Override
	public void onAttachedToWindow() {
	    super.onAttachedToWindow();
	    openOptionsMenu();
	}*/
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   
		if(item.getItemId()== R.id.action_create){
			Intent intentNew = new Intent(AthleteList.this, RegisterAthlete.class);
			AthleteList.this.startActivity(intentNew);
			//AthleteList.this.finish();	
		}
	    return true;
	} 

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.lv_athlete) {
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
	  
	  Athlete athlete = athletesList.get(info.position);
	  
	  if(menuItemName.equals(menuItems[0])){
		  Intent intentNew = new Intent(AthleteList.this, CalendarActivities.class);
		  athleteSelected = athlete;
		  AthleteList.this.startActivity(intentNew);
		 // AthleteList.this.finish();
		 return false;
		  
	  }else if (menuItemName.equals(menuItems[1])) {
		  		Intent intentNew = new Intent(AthleteList.this, RegisterAthlete.class);
				intentNew.putExtra("athlete", athlete);
				AthleteList.this.startActivity(intentNew);
				//AthleteList.this.finish();
				return false;
				
	        }else if (menuItemName.equals(menuItems[2])) {
				
	        	athletedb.delete(athlete.getIdAthlete());
				adapter.clear();
				ArrayList<Athlete> atheletes = (ArrayList<Athlete>) athletedb.getAll();	
				adapter.restoreList();				
				adapter.addAll(atheletes);
				
				return true;
			}
	  
	  return true;
	}
	


}
