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
	
	ListView listView;
	List<String> athletes = new ArrayList<String>();
	AthleteDB athletedb;
	Button btn_new;
	Button btn_delete;
	Button btn_update_list_athlete;
	List<Athlete> athletesList;
	AthleteAdapter adapter;	
	
	protected void onCreate(Bundle savedInstanceState) {
		final AlertDialog.Builder alert = new AlertDialog.Builder(this).setTitle("Alerta").setMessage("Selecione apenas 1 atleta!").setNeutralButton("OK", null);  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_athlete);
		//s
		
		athletedb = new AthleteDB(getApplicationContext());
		athletesList = athletedb.getAll();
		adapter = new AthleteAdapter(this, R.layout.list_view_adapter_athlete,(ArrayList<Athlete>) athletesList);
		
		//btn_new = (Button)findViewById(R.id.btn_new_athlete);
		//btn_delete = (Button)findViewById(R.id.btn_remove_athlete);
		//btn_update_list_athlete = (Button)findViewById(R.id.btn_edit_athlete);
		
		/*btn_new.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(AthleteList.this, RegisterAthlete.class);
				AthleteList.this.startActivity(intentNew);
				AthleteList.this.finish();		
			}
		});
		
		btn_delete.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				for(Athlete athlete : adapter.getAllSelectedAthletes()){
					if(athlete.getSelected())
						athletedb.delete(athlete.getIdAthlete());
				}
				adapter.clear();
				ArrayList<Athlete> atheletes = (ArrayList<Athlete>) athletedb.getAll();	
				adapter.restoreList();				
				adapter.addAll(atheletes);
			}
		});
		
		btn_update_list_athlete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ArrayList<Athlete> list=adapter.getAllSelectedAthletes();
				if(list.size()>1)
					alert.show();
				else if(list.size()==1){
					Intent intentNew = new Intent(AthleteList.this, RegisterAthlete.class);
					intentNew.putExtra("athlete", list.get(0));
					AthleteList.this.startActivity(intentNew);
					AthleteList.this.finish();						
				}
					
				
			}
		});
		*/
	
		
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
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   

		if(item.getItemId()== R.id.action_create){
			Intent intentNew = new Intent(AthleteList.this, RegisterAthlete.class);
			AthleteList.this.startActivity(intentNew);
			AthleteList.this.finish();	
		}
	    return true;
	} 

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.lv_athlete) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    menu.setHeaderTitle(athletesList.get(info.position).getName());
	    String[] menuItems = getResources().getStringArray(R.array.gender);
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	  }
	}

}
