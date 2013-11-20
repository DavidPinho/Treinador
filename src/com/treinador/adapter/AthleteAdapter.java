package com.treinador.adapter;

import java.util.List;

import com.treinador.R;
import com.treinador.model.Athlete;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class AthleteAdapter extends ArrayAdapter<Athlete> {
	
	  private final LayoutInflater inflater;
	  private final int resourceId;

	public AthleteAdapter(Context context, int resource, List<Athlete> atletas) {
		super(context, resource, atletas);
		this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
		// TODO Auto-generated constructor stub
	}
	
	
    public View getView(int position, View convertView, ViewGroup parent) {
  
        Athlete athlete = getItem(position);
  
        convertView = inflater.inflate(resourceId, parent, false);
        
       
        TextView nome = (TextView) convertView.findViewById(R.id.lbl_nome_atleta_lista);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.cb_atleta_lista);
  
        nome.setText(athlete.getName());
       
        cb.setChecked(false);
        
  
        return convertView;
    } 

}
