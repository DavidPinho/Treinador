package com.treinador.adapter;

import java.util.ArrayList;
import java.util.List;

import com.treinador.R;
import com.treinador.model.Athlete;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AthleteAdapter extends ArrayAdapter<Athlete> {
	
	  LayoutInflater inflater;
	  private ArrayList<Athlete> athletes;
	  Context ctx;
	  int resourceId;
	  

	public AthleteAdapter(Context context, int resource, ArrayList<Athlete> athletesList) {
		super(context, resource, athletesList);
		this.inflater = LayoutInflater.from(context);
	    this.resourceId = resource;
		ctx = context;
		this.athletes= athletesList;
	}
	
	
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	  View view = convertView;
          if (view == null) {
              view = inflater.inflate(R.layout.list_view_adapter_athlete, parent, false);
          }
  
        Athlete athlete = getAthlete(position);
  
       
        TextView nome = (TextView) view.findViewById(R.id.lbl_nome_atleta_lista);
        CheckBox cb = (CheckBox) view.findViewById(R.id.cb_atleta_lista);  
        cb.setTag(position);
        cb.setOnCheckedChangeListener(myCheckChangList);
        cb.setChecked(false);
        
        
        
        nome.setText(athlete.getName());
              
  
        return view;
    }
    
    OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			getItem((Integer)buttonView.getTag()).setSelected(isChecked);
			
		}
	};
    
    public Athlete getAthlete(int position){
    	return ((Athlete)getItem(position));
    }
    
    public 	ArrayList<Athlete> getAllSelectedAthletes(){
    	ArrayList<Athlete> list = new ArrayList<Athlete>();
    	for(Athlete at : athletes){
    		if(at.getSelected())
    			list.add(at);
    	}
    	
    	return list;
    }


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return athletes.size();
	}


	@Override
	public Athlete getItem(int position) {
		// TODO Auto-generated method stub
		return athletes.get(position);
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	} 
	
	 public ArrayList<Athlete> getAthletes() {
			return athletes;
		}


	public void setAthletes(ArrayList<Athlete> athletes) {
		this.athletes = athletes;
	}
	
	public void restoreList(){
		athletes.clear();
		
	}

}
