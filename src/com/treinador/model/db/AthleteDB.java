package com.treinador.model.db;




import java.util.ArrayList;
import java.util.List;

import com.treinador.model.Athlete;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.Contacts.Data;


public class AthleteDB extends Dao{

	public AthleteDB(Context ctx) {
		super(ctx);
	}
	
	public long insert(Athlete a){
		ContentValues c=new ContentValues();
		
		c.put(DataBase.ATHLETE_NAME, a.getName());
		c.put(DataBase.ATHLETE_GENDER, a.getGender());
		c.put(DataBase.ATHLETE_BIRTHDATE, a.getBirthDate());
		
		long idatleta = db.getDB().insert(DataBase.TB_ATHLETE, null, c);
		

		return idatleta;
	}
	
	public List<Athlete> getAll(){
				 
	    Cursor c = db.getDB().query(DataBase.TB_ATHLETE, null, null, null, null, null, DataBase.ATHLETE_NAME);
	    
	    List<Athlete> athletes = new ArrayList<Athlete>();
	    
	    for(int i= 0;i<c.getCount();i++){
	    	c.moveToPosition(i);
	    	
	    	Athlete athlete = new Athlete();
	    	
	    	athlete.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.ATHLETE_ID)));
	    	athlete.setName(c.getString(c.getColumnIndex(DataBase.ATHLETE_NAME)));
	    	athlete.setBirthDate(c.getString(c.getColumnIndex(DataBase.ATHLETE_BIRTHDATE)));
	    	athlete.setGender(c.getString(c.getColumnIndex(DataBase.ATHLETE_GENDER)));
	    	
	    	athletes.add(athlete);
	    }
	    
	    return athletes;
		
	}
	
	public void delete(int id){
		db.getDB().delete(DataBase.TB_ATHLETE, DataBase.ATHLETE_ID+"=?", new String[] {Integer.toString(id)});
	}
	
	public void update(Athlete a){
		
		ContentValues c  = new ContentValues();
		c.put(DataBase.ATHLETE_NAME, a.getName());
		c.put(DataBase.ATHLETE_GENDER, a.getGender());
		c.put(DataBase.ATHLETE_BIRTHDATE, a.getBirthDate());
		db.getDB().update(DataBase.TB_ATHLETE, c, DataBase.ATHLETE_ID+"=?", new String[] {Integer.toString(a.getIdAthlete())});
	}
	
	
	public Athlete getOne(int id){
		
		  Cursor c = db.getDB().query(DataBase.TB_ATHLETE, null, DataBase.ATHLETE_ID+"=?", new String[] {Integer.toString(id)}, null, null, null);
		    
		    Athlete athlete = new Athlete();
		    
		    for(int i= 0;i<c.getCount();i++){
		    	c.moveToPosition(i);
		    		    	
		    	athlete.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.ATHLETE_ID)));
		    	athlete.setName(c.getString(c.getColumnIndex(DataBase.ATHLETE_NAME)));
		    	athlete.setBirthDate(c.getString(c.getColumnIndex(DataBase.ATHLETE_BIRTHDATE)));
		    	athlete.setGender(c.getString(c.getColumnIndex(DataBase.ATHLETE_GENDER)));
		    } 	
		   		    
		    return athlete;	
		
	}
	
	
	
	

}
