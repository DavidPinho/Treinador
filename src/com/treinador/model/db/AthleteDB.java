package com.treinador.model.db;




import java.util.ArrayList;
import java.util.List;

import com.treinador.model.Athlete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class AthleteDB extends Dao{

	public AthleteDB(Context ctx) {
		super(ctx);
	}
	
	public long insert(Athlete a){
		ContentValues c=new ContentValues();
		//c.put(DataBase.ATLETA_ID, a.getIdAtleta());
		c.put(DataBase.ATHLETE_NAME, a.getName());
		c.put(DataBase.ATHLETE_GENDER, a.getGender());
		c.put(DataBase.ATHLETE_BIRTHDATE, a.getBirthDate());
		
		long idatleta = db.getDB().insert(DataBase.TB_ATHLETE, null, c);
		

		return idatleta;
	}
	
	public List<Athlete> getAll(){
				 
	    Cursor c = db.getDB().query(DataBase.TB_ATHLETE, null, null, null, null, null, null);
	    
	    List<Athlete> atletas = new ArrayList<Athlete>();
	    
	    for(int i= 0;i<c.getCount();i++){
	    	c.moveToPosition(i);
	    	
	    	Athlete atleta = new Athlete();
	    	
	    	atleta.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.ATHLETE_ID)));
	    	atleta.setName(c.getString(c.getColumnIndex(DataBase.ATHLETE_NAME)));
	    	atleta.setBirthDate(c.getString(c.getColumnIndex(DataBase.ATHLETE_BIRTHDATE)));
	    	atleta.setGender(c.getString(c.getColumnIndex(DataBase.ATHLETE_GENDER)));
	    	
	    	atletas.add(atleta);
	    }
	    
	    return atletas;
		
	}

}
