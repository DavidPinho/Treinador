package com.treinador.adapter;

import java.util.ArrayList;
import java.util.Date;

import com.treinador.R;
import com.treinador.model.Athlete;
import com.treinador.model.Exercise;
import com.treinador.model.Muscle;
import com.treinador.model.db.ExerciseTypeDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AgendaAdapter extends ArrayAdapter<Exercise>{
	
	 LayoutInflater inflater;
	  private ArrayList<Exercise> exercises;
	  Context ctx;
	  int resourceId;
	  ExerciseTypeDB typeDB;

	public AgendaAdapter(Context context, int resource, ArrayList<Exercise> exerciseList) {
		super(context, resource, exerciseList);
		this.inflater = LayoutInflater.from(context);
	    this.resourceId = resource;
		ctx = context;
		this.exercises= exerciseList;
		typeDB = new ExerciseTypeDB(context);
		// TODO Auto-generated constructor stub
	}
	
	 public View getView(int position, View convertView, ViewGroup parent) {
	    	
	   	  View view = convertView;
	         if (view == null) {
	             view = inflater.inflate(R.layout.list_view_adapter_agenda, parent, false);
	         }
	 
	       Exercise exercise = getExercise(position);	 
	      
	      
	       TextView name = (TextView) view.findViewById(R.id.lbl_name_exercise_agenda);
	       TextView date = (TextView) view.findViewById(R.id.lbl_date_exercise_agenda);
	       
	       name.setText(typeDB.getOne(exercise.getIdExerciseType()).getDescription());
	       //typeDB.getOne(exercise.getIdExerciseType()).getDescription()
	       date.setText(new Date(exercise.getDate()).toString());
	 
	       return view;
	   }
		 
		 
			public int getCount() {
				// TODO Auto-generated method stub
				return exercises.size();
			}

			
			public Exercise getExercise(int position) {
				// TODO Auto-generated method stub
				return exercises.get(position);
			}

}
