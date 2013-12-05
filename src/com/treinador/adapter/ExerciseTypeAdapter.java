package com.treinador.adapter;

import java.util.ArrayList;

import com.treinador.R;
import com.treinador.model.ExerciseType;
import com.treinador.model.Muscle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExerciseTypeAdapter extends ArrayAdapter<ExerciseType>{

	  LayoutInflater inflater;
	  private ArrayList<ExerciseType> exerciseTypes;
	  Context ctx;
	  int resourceId;
	  
	public ExerciseTypeAdapter(Context context, int resource, ArrayList<ExerciseType> exerciseTypeList) {
		super(context, resource, exerciseTypeList);
		this.inflater = LayoutInflater.from(context);
	    this.resourceId = resource;
		ctx = context;
		this.exerciseTypes= exerciseTypeList;
		// TODO Auto-generated constructor stub
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
    	
	   	  View view = convertView;
	         if (view == null) {
	             view = inflater.inflate(R.layout.list_view_adapter_type_exercise, parent, false);
	         }
	 
	       ExerciseType type = getExerciseType(position);		      
	       TextView name = (TextView) view.findViewById(R.id.lbl_name_type_exercise);	 
	       name.setText(type.getDescription());   
	       
	       return view;
	   }
		 
		 
			public int getCount() {
				// TODO Auto-generated method stub
				return exerciseTypes.size();
			}

			
			public ExerciseType getExerciseType(int position) {
				// TODO Auto-generated method stub
				return exerciseTypes.get(position);
			}



}
