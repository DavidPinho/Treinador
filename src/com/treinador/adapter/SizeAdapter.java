package com.treinador.adapter;

import java.util.ArrayList;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.model.Size;
import com.treinador.model.db.MuscleDB;

import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SizeAdapter extends ArrayAdapter<Size>{


	  LayoutInflater inflater;
	  private ArrayList<Size> sizes;
	  Context ctx;
	  int resourceId;
	  MuscleDB muscleDB =new MuscleDB(getContext());

	public SizeAdapter(Context context, int resource, ArrayList<Size> sizeList) {
		super(context, resource, sizeList);
		this.inflater = LayoutInflater.from(context);
	    this.resourceId = resource;
		ctx = context;
		this.sizes= sizeList;
	}
	
	
	 public View getView(int position, View convertView, ViewGroup parent) {
		 
	  	    	
 	  View view = convertView;
       if (view == null) {
           view = inflater.inflate(R.layout.list_view_adapter_size, parent, false);
       }
     
     
     Size size = getSize(position);

    
     TextView name = (TextView) view.findViewById(R.id.lbl_name_size);     
     name.setText(muscleDB.getOne(size.getIdMuscle()).getDescription()); 
     
     TextView value = (TextView) view.findViewById(R.id.lbl_value_size);
     value.setText(Double.toString(size.getSizeValue()));
     
     TextView date =  (TextView) view.findViewById(R.id.lbl_date_size);
     date.setText(size.getDate());
     
     

     return view;
 }
	 
	 
		public int getCount() {
			// TODO Auto-generated method stub
			return sizes.size();
		}

		
		public Size getSize(int position) {
			// TODO Auto-generated method stub
			return sizes.get(position);
		}

		public void restoreList(){
			sizes.clear();
			
		}
	
}
