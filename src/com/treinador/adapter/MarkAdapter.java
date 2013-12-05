package com.treinador.adapter;

import java.util.ArrayList;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;
import com.treinador.model.Mark;
import com.treinador.model.Muscle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MarkAdapter extends ArrayAdapter<Mark> {

	LayoutInflater inflater;
	private ArrayList<Mark> marks;
	Context ctx;
	int resourceId;
	
	
	public MarkAdapter(Context context, int resource, ArrayList<Mark> marksList) {
		super(context, resource, marksList);
		this.inflater = LayoutInflater.from(context);
		this.marks = marksList;
		this.ctx = context;
		this.resourceId = resource;
	}
	
	

    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	  View view = convertView;
          if (view == null) {
              view = inflater.inflate(R.layout.list_view_adapter_mark, parent, false);
          }
          
        Mark mark = getMark(position);
       
        TextView name = (TextView) view.findViewById(R.id.lbl_name_mark);     
        
        name.setText(mark.getDescription());
              
  
        return view;
    }
    
   

	public int getCount() {
		// TODO Auto-generated method stub
		return marks.size();
	}

	
	public Mark getMark(int position) {
		// TODO Auto-generated method stub
		return marks.get(position);
	}


	
	

}
