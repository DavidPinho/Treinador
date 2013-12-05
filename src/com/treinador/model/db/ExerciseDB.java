package com.treinador.model.db;

import java.util.ArrayList;

import com.treinador.model.Exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ExerciseDB extends Dao {

	protected ExerciseDB(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	
	
	public long insert(Exercise exercise){
		ContentValues c=new ContentValues();
		
		c.put(DataBase.EXERCISE_ATHLETE_ID, exercise.getIdAthlete());
		c.put(DataBase.EXERCISE_EXERCISE_TYPE_ID, exercise.getIdExerciseType());
		c.put(DataBase.EXERCISE_DATE, exercise.getDate());
		c.put(DataBase.EXERCISE_DURATION, exercise.getDuration());
		c.put(DataBase.EXERCISE_INSTRUCTIONS, exercise.getInstructions());
		c.put(DataBase.EXERCISE_REPETITIONS, exercise.getRepetitions());
		c.put(DataBase.EXERCISE_WEIGHT, exercise.getWeight());
		
		long idExercise = db.getDB().insert(DataBase.TB_EXERCISE, null, c);		

		return idExercise;
	}
	
	public ArrayList<Exercise> getAll(){
		 
	    Cursor c = db.getDB().query(DataBase.TB_EXERCISE, null, null, null, null, null, null);
	    
	    ArrayList<Exercise> exercises = new ArrayList<Exercise>();
	    
	    for(int i= 0;i<c.getCount();i++){
	    	c.moveToPosition(i);
	    	
	    	Exercise exercise = new Exercise();
	    	
	    	exercise.setDate(c.getString(c.getColumnIndex(DataBase.EXERCISE_DATE)));
	    	exercise.setDuration(c.getFloat(c.getColumnIndex(DataBase.EXERCISE_DURATION)));
	    	exercise.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.EXERCISE_ATHLETE_ID)));
	    	exercise.setIdExercise(c.getInt(c.getColumnIndex(DataBase.EXERCISE_ID)));
	    	exercise.setIdExerciseType(c.getInt(c.getColumnIndex(DataBase.EXERCISE_EXERCISE_TYPE_ID)));
	    	exercise.setInstructions(c.getString(c.getColumnIndex(DataBase.EXERCISE_INSTRUCTIONS)));
	    	exercise.setRepetitions(c.getInt(c.getColumnIndex(DataBase.EXERCISE_REPETITIONS)));
	    	exercise.setWeight(c.getFloat(c.getColumnIndex(DataBase.EXERCISE_WEIGHT)));
	    	
	    	
	    	exercises.add(exercise);
	    }
	    
	    return exercises;		
	}
	
	public void delete(int id){
		db.getDB().delete(DataBase.TB_EXERCISE, DataBase.EXERCISE_ID+"=?", new String[] {Integer.toString(id)});
	}
	
	public void update(Exercise exercise){
		
		ContentValues c  = new ContentValues();
		
		c.put(DataBase.EXERCISE_EXERCISE_TYPE_ID, exercise.getIdExerciseType());
		c.put(DataBase.EXERCISE_DATE, exercise.getDate());
		c.put(DataBase.EXERCISE_DURATION, exercise.getDuration());
		c.put(DataBase.EXERCISE_INSTRUCTIONS, exercise.getInstructions());
		c.put(DataBase.EXERCISE_REPETITIONS, exercise.getRepetitions());
		c.put(DataBase.EXERCISE_WEIGHT, exercise.getWeight());		
		
		db.getDB().update(DataBase.TB_EXERCISE, c, DataBase.EXERCISE_ID+"=?", new String[] {Integer.toString(exercise.getIdExercise())});
	}
	
	
	public Exercise getOne(int id){
		
		  Cursor c = db.getDB().query(DataBase.TB_EXERCISE, null, DataBase.EXERCISE_ID+"=?", new String[] {Integer.toString(id)}, null, null, null);
		    
		    Exercise exercise = new Exercise();
		    
		    for(int i= 0;i<c.getCount();i++){
		    	c.moveToPosition(i);
		    		    	
		    	exercise.setDate(c.getString(c.getColumnIndex(DataBase.EXERCISE_DATE)));
		    	exercise.setDuration(c.getFloat(c.getColumnIndex(DataBase.EXERCISE_DURATION)));
		    	exercise.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.EXERCISE_ATHLETE_ID)));
		    	exercise.setIdExercise(c.getInt(c.getColumnIndex(DataBase.EXERCISE_ID)));
		    	exercise.setIdExerciseType(c.getInt(c.getColumnIndex(DataBase.EXERCISE_EXERCISE_TYPE_ID)));
		    	exercise.setInstructions(c.getString(c.getColumnIndex(DataBase.EXERCISE_INSTRUCTIONS)));
		    	exercise.setRepetitions(c.getInt(c.getColumnIndex(DataBase.EXERCISE_REPETITIONS)));
		    	exercise.setWeight(c.getFloat(c.getColumnIndex(DataBase.EXERCISE_WEIGHT)));
		    	
		    } 	
		   		    
		    return exercise;	
		
	}
	
	

}
