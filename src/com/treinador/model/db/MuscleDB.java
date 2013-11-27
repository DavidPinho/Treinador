package com.treinador.model.db;

import java.util.ArrayList;
import java.util.List;

import com.treinador.model.Muscle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MuscleDB extends Dao{

	protected MuscleDB(Context ctx) {
		super(ctx);
	}

	public long insert (Muscle m){
		ContentValues c = new ContentValues();

		c.put(DataBase.MUSCLE_DESCRIPTION, m.getDescription());

		long idMuscle = db.getDB().insert(DataBase.TB_MUSCLE, null, c);

		return idMuscle;

	}

	public List<Muscle> getAll(){

		Cursor c = db.getDB().query(DataBase.TB_MUSCLE, null, null, null, null, null, null);

		List<Muscle> muscles = new ArrayList<Muscle>();

		for(int i= 0;i<c.getCount();i++){
			c.moveToPosition(i);

			Muscle muscle = new Muscle();

			muscle.setDescription(c.getString(c.getColumnIndex(DataBase.MUSCLE_DESCRIPTION)));
			muscle.setIdMuscle(c.getInt(c.getColumnIndex(DataBase.MUSCLE_ID)));
			
		
			muscles.add(muscle);
		}

		return muscles;

	}
	
	
	public void delete(int id){
		db.getDB().delete(DataBase.TB_MUSCLE, DataBase.MUSCLE_ID+"=?", new String[] {Integer.toString(id)});
	}
	
	public void update(Muscle m){
		
		ContentValues c  = new ContentValues();
		c.put(DataBase.MUSCLE_DESCRIPTION, m.getDescription());
		
		
		db.getDB().update(DataBase.TB_MUSCLE, c, DataBase.MUSCLE_ID+"=?", new String[] {Integer.toString(m.getIdMuscle())});
	}
	
	
	public Muscle getOne(int id){
		
		  Cursor c = db.getDB().query(DataBase.TB_MUSCLE, null, DataBase.MUSCLE_ID+"=?", new String[] {Integer.toString(id)}, null, null, null);
		    
		  Muscle muscle = new Muscle();
		    
		  c.moveToPosition(0);
		    		    	
		  muscle.setDescription(c.getString(c.getColumnIndex(DataBase.MUSCLE_DESCRIPTION)));
		  muscle.setIdMuscle(c.getInt(c.getColumnIndex(DataBase.MUSCLE_ID)));
		 
		   		    
		  return muscle;
		
	}
}
