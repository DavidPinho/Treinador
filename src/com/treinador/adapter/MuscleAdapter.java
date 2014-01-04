package com.treinador.adapter;

import java.util.ArrayList;
import java.util.List;

import com.treinador.R;
import com.treinador.model.Athlete;
import com.treinador.model.Muscle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MuscleAdapter  extends ArrayAdapter<Muscle>{
	
	
	  LayoutInflater inflater;
	  private ArrayList<Muscle> muscles;
	  Context ctx;
	  int resourceId;

	public MuscleAdapter(Context context, int resource, ArrayList<Muscle> muscleList) {
		super(context, resource,muscleList);
		this.inflater = LayoutInflater.from(context);
	    this.resourceId = resource;
		ctx = context;
		this.muscles= muscleList;
	}
	
	
	 public View getView(int position, View convertView, ViewGroup parent) {
	    	
   	  View view = convertView;
         if (view == null) {
             view = inflater.inflate(R.layout.list_view_adapter_muscle, parent, false);
         }
 
       Muscle muscle = getMuscle(position);
 
      
       TextView name = (TextView) view.findViewById(R.id.lbl_name_muscle);
 
       name.setText(muscle.getDescription());         
 
       return view;
   }
	 
	 
		public int getCount() {
			return muscles.size();
		}

		
		public Muscle getMuscle(int position) {
			return muscles.get(position);
		}

	//TODO this method name can be different, how "updateList", because this do not restore nothing
		public void restoreList(){
			muscles.clear();
		}

}
