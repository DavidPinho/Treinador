package com.treinador.model.db;

import java.util.ArrayList;
import java.util.List;

import com.treinador.model.Size;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SizeDB extends Dao{

	public SizeDB(Context ctx) {
		super(ctx);
	}

	public long insert (Size s){
		ContentValues c = new ContentValues();

		c.put(DataBase.SIZE_ATHLETE_ID, s.getIdAthlete());
		//TODO the date will be a String?
		c.put(DataBase.SIZE_DATE, s.getDate());
		//c.put(DataBase.SIZE_ID, s.getIdSize());
		c.put(DataBase.SIZE_MUSCLE_ID, s.getIdMuscle());
		c.put(DataBase.SIZE_VALUE, s.getSizeValue());

		long idSize = db.getDB().insert(DataBase.TB_SIZE, null, c);

		return idSize;

	}

	public List<Size> getAll(int id){

		Cursor c = db.getDB().query(DataBase.TB_SIZE, null, DataBase.SIZE_ATHLETE_ID+"=?",  new String[] {Integer.toString(id)}, null, null,DataBase.SIZE_MUSCLE_ID);

		List<Size> sizes = new ArrayList<Size>();

		for(int i= 0;i<c.getCount();i++){
			c.moveToPosition(i);

			Size size = new Size();

			size.setDate(c.getString(c.getColumnIndex(DataBase.SIZE_DATE)));
			size.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.SIZE_ATHLETE_ID)));
			size.setIdMuscle(c.getInt(c.getColumnIndex(DataBase.SIZE_MUSCLE_ID)));
			size.setIdSize(c.getInt(c.getColumnIndex(DataBase.SIZE_ID)));
			size.setSizeValue(c.getDouble(c.getColumnIndex(DataBase.SIZE_VALUE)));
		
			sizes.add(size);
		}

		return sizes;

	}
	
	
	public void delete(int id){
		db.getDB().delete(DataBase.TB_SIZE, DataBase.SIZE_ID+"=?", new String[] {Integer.toString(id)});
	}
	
	public void update(Size s){
		
		ContentValues c  = new ContentValues();
		c.put(DataBase.SIZE_ATHLETE_ID, s.getIdAthlete());
		c.put(DataBase.SIZE_DATE, s.getDate());
		c.put(DataBase.SIZE_MUSCLE_ID, s.getIdMuscle());
		c.put(DataBase.SIZE_VALUE, s.getSizeValue());
		
		db.getDB().update(DataBase.TB_SIZE, c, DataBase.SIZE_ID+"=?", new String[] {Integer.toString(s.getIdSize())});
	}
	
	
	public Size getOne(int id){
		
		  Cursor c = db.getDB().query(DataBase.TB_SIZE, null, DataBase.SIZE_ID+"=?", new String[] {Integer.toString(id)}, null, null, null);
		    
		  Size size = new Size();
		    
		  c.moveToPosition(0);
		    		    	
		  size.setDate(c.getString(c.getColumnIndex(DataBase.SIZE_DATE)));
		  size.setIdAthlete(c.getInt(c.getColumnIndex(DataBase.SIZE_ATHLETE_ID)));
		  size.setIdMuscle(c.getInt(c.getColumnIndex(DataBase.SIZE_MUSCLE_ID)));
		  size.setIdSize(c.getInt(c.getColumnIndex(DataBase.SIZE_ID)));
		  size.setSizeValue(c.getDouble(c.getColumnIndex(DataBase.SIZE_VALUE)));
		    	
		   		    
		 return size;
		
	}


}

