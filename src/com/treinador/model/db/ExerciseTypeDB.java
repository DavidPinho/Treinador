package com.treinador.model.db;

import java.util.ArrayList;
import java.util.List;

import com.treinador.model.ExerciseType;
import com.treinador.model.Mark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ExerciseTypeDB extends Dao {

	public ExerciseTypeDB(Context ctx) {
		super(ctx);
	}
	
	public long insert (ExerciseType et){
		ContentValues c = new ContentValues();

		c.put(DataBase.EXERCISE_TYPE_ID, et.getIdExerciseType());
		c.put(DataBase.EXERCISE_TYPE_NAME, et.getDescription());

		long idExerciseType = db.getDB().insert(DataBase.TB_EXERCISE_TYPE, null, c);

		return idExerciseType;

	}

	public List<ExerciseType> getAll(){

		Cursor c = db.getDB().query(DataBase.TB_EXERCISE_TYPE, null, null, null, null, null, null);

		List<ExerciseType> exercises = new ArrayList<ExerciseType>();

		for(int i= 0;i<c.getCount();i++){
			c.moveToPosition(i);

			ExerciseType et = new ExerciseType();
			
			et.setIdExerciseType(c.getInt(c.getColumnIndex(DataBase.EXERCISE_TYPE_ID))); 
			et.setDescription(c.getString(c.getColumnIndex(DataBase.EXERCISE_TYPE_NAME)));

			exercises.add(et);
		}

		return exercises;

	}
	
	
	public void delete(int id){
		db.getDB().delete(DataBase.TB_EXERCISE_TYPE, DataBase.EXERCISE_TYPE_ID+"=?", new String[] {Integer.toString(id)});
	}
	
	public void update(ExerciseType et){
		
		ContentValues c  = new ContentValues();
		
		c.put(DataBase.EXERCISE_TYPE_NAME, et.getDescription());
		
		db.getDB().update(DataBase.TB_EXERCISE_TYPE, c, DataBase.EXERCISE_EXERCISE_TYPE_ID+"=?", new String[] {Integer.toString(et.getIdExerciseType())});
	}
	
	
	public ExerciseType getOne(int id){
		
		  Cursor c = db.getDB().query(DataBase.TB_EXERCISE_TYPE, null, DataBase.EXERCISE_TYPE_ID+"=?", new String[] {Integer.toString(id)}, null, null, null);
		    
		   ExerciseType et = new ExerciseType();
		    
		    for(int i= 0;i<c.getCount();i++){
		    	c.moveToPosition(i);
		    	
		    	et.setIdExerciseType(c.getInt(c.getColumnIndex(DataBase.EXERCISE_TYPE_ID))); 
				et.setDescription(c.getString(c.getColumnIndex(DataBase.EXERCISE_TYPE_NAME)));	
		    	
		    } 	
		   		    
		    return et;
		
	}

}
