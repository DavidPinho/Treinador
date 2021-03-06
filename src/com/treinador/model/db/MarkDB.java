package com.treinador.model.db;

import java.util.ArrayList;
import java.util.List;

import com.treinador.model.Mark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MarkDB extends Dao {

	public MarkDB(Context ctx) {
		super(ctx);
	}

	public long insert (Mark m){
		ContentValues c = new ContentValues();

		
		c.put(DataBase.MARK_ATHLETE_ID, m.getIdAthlete());
		c.put(DataBase.MARK_NAME, m.getDescription());
		c.put(DataBase.MARK_INITIAL_DATE, m.getInitialDate());
		c.put(DataBase.MARK_FINAL_DATE, m.getFinalDate());

		long idMark = db.getDB().insert(DataBase.TB_MARK, null, c);

		return idMark;

	}

	public List<Mark> getAll(int athleteId){

		Cursor c = db.getDB().query(DataBase.TB_MARK, null,DataBase.MARK_ATHLETE_ID+"=?", new String[] {Integer.toString(athleteId)}, null, null, DataBase.MARK_NAME);

		List<Mark> marks = new ArrayList<Mark>();

		for(int i= 0;i<c.getCount();i++){
			c.moveToPosition(i);

			Mark mark = new Mark();

			mark.setIdMark(c.getInt(c.getColumnIndex(DataBase.MARK_ID)));
			mark.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.MARK_ATHLETE_ID)));
			mark.setDescription(c.getString(c.getColumnIndex(DataBase.MARK_NAME)));
			mark.setInitialDate(c.getString(c.getColumnIndex(DataBase.MARK_INITIAL_DATE)));
			mark.setFinalDate(c.getString(c.getColumnIndex(DataBase.MARK_FINAL_DATE)));

			marks.add(mark);
		}

		return marks;

	}
	
	
	public void delete(int id){
		db.getDB().delete(DataBase.TB_MARK, DataBase.MARK_ID+"=?", new String[] {Integer.toString(id)});
	}
	
	public void update(Mark m){
		
		ContentValues c  = new ContentValues();
		c.put(DataBase.MARK_NAME, m.getDescription());
		c.put(DataBase.MARK_INITIAL_DATE, m.getInitialDate());
		c.put(DataBase.MARK_FINAL_DATE, m.getFinalDate());
		db.getDB().update(DataBase.TB_MARK, c, DataBase.MARK_ID+"=?", new String[] {Integer.toString(m.getIdMark())});
	}
	
	
	public Mark getOne(int id){
		
		  Cursor c = db.getDB().query(DataBase.TB_MARK, null, DataBase.MARK_ID+"=?", new String[] {Integer.toString(id)}, null, null, null);
		    
		   Mark mark = new Mark();
		    
		    
		    	c.moveToPosition(0);
		    		    	
		    	mark.setIdMark(c.getInt(c.getColumnIndex(DataBase.MARK_ID)));
		    	mark.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.MARK_ATHLETE_ID)));
		    	mark.setDescription(c.getString(c.getColumnIndex(DataBase.MARK_NAME)));
		    	mark.setInitialDate(c.getString(c.getColumnIndex(DataBase.MARK_INITIAL_DATE)));
				mark.setFinalDate(c.getString(c.getColumnIndex(DataBase.MARK_FINAL_DATE)));
		    	
		    	
		   		    
		    return mark;
		
	}


}
