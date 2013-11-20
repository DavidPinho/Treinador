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
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_athlete);
		
		btn_new = (Button)findViewById(R.id.btn_novo_atleta);
		btn_new.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNew = new Intent(AthleteList.this, RegisterAthlete.class);
				AthleteList.this.startActivity(intentNew);
				AthleteList.this.finish();		
			}
		});
		
		
		athletedb = new AthleteDB(getApplicationContext());
		List<Athlete> athletesList = athletedb.getAll();
		for(int i = 0 ; i < athletesList.size(); i++)
			athletes.add(athletesList.get(i).getName());
		
		ArrayAdapter<Athlete> adapter = new AthleteAdapter(this, R.layout.list_view_adapter_athlete, athletesList);
		
		listView = (ListView) findViewById(R.id.lv_atleta);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				
				switch (position) {
				case 0:
					String mensagem ="Vc esta nesta pagina";
					Toast.makeText(AthleteList.this, mensagem, Toast.LENGTH_SHORT);
					break;
				case 1:
					//Intent changeActivity = new Intent(AthleteList.this,MainActivity.class);
					//startActivityForResult(changeActivity, 1);
					
					break;

				default:
					break;
				}
				
			}
			
			
		});
		
	}
	
	

}
