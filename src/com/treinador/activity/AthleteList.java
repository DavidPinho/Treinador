package com.treinador.activity;

import java.util.ArrayList;
import java.util.List;

import com.treinador.R;
import com.treinador.adapter.AthleteAdapter;
import com.treinador.model.Athlete;
import com.treinador.model.db.AthleteDB;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
	List<Athlete> athletesList;
	AthleteAdapter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_athlete);
		
		athletedb = new AthleteDB(getApplicationContext());
		athletesList = athletedb.getAll();
		adapter = new AthleteAdapter(this, R.layout.list_view_adapter_athlete,(ArrayList<Athlete>) athletesList);
		
		btn_new = (Button)findViewById(R.id.btn_new_athlete);
		btn_delete = (Button)findViewById(R.id.btn_remove_athlete);
		btn_new.setOnClickListener(new View.OnClickListener() {			
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
				
				for(Athlete athlete : adapter.getAllAthletes()){
					if(athlete.getSelected()){
						athletedb.delete(athlete.getIdAthlete());
					}
				}
				adapter.clear();
				ArrayList<Athlete> atheletes = (ArrayList<Athlete>) athletedb.getAll();	
				adapter.restoreList();
				
				adapter.addAll(atheletes);
				//adapter.notifyDataSetChanged();
				
				
				
			}
		});
		
	
		
		listView = (ListView) findViewById(R.id.lv_athlete);
		listView.setAdapter(adapter);
		
		
	}
	
	

}
