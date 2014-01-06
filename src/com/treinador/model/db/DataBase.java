package com.treinador.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DataBase {

	private boolean isExecuting;
	// classe para o banco de Dados
	public static final String TB_ATHLETE = "athlete", 
			TB_MARK = "mark", TB_SIZE = "size", TB_MUSCLE = "muscle",
			TB_EXERCISE = "exercise", TB_EXERCISE_TYPE = "exercise_type",
							   
			

			
			ATHLETE_ID = "idathlete", ATHLETE_NAME = "name",
			ATHLETE_BIRTHDATE = "birthdate", ATHLETE_GENDER = "gender",
			
			MUSCLE_ID = "idmuscle", MUSCLE_DESCRIPTION = "description",
			
			SIZE_ID = "idsize", SIZE_ATHLETE_ID = "idathlete", SIZE_MUSCLE_ID = "idmuscle",
			SIZE_DATE = "date", SIZE_VALUE ="value",
			
			MARK_ID = "idmark", MARK_ATHLETE_ID = "idathlete", MARK_NAME = "name",
			MARK_INITIAL_DATE = "initial_date", MARK_FINAL_DATE = "final_date",
			
			EXERCISE_ID = "idexercise", EXERCISE_ATHLETE_ID = "idathlete", EXERCISE_EXERCISE_TYPE_ID = "idtype_exercise",
			EXERCISE_INSTRUCTIONS = "instructions", EXERCISE_WEIGHT="weight", EXERCISE_REPETITIONS = "repetitions",
			EXERCISE_DURATION = "duration", EXERCISE_DATE = "date",
			
			EXERCISE_TYPE_ID = "idexercise_type", EXERCISE_TYPE_NAME ="name"	;
	
	

	private static DataBase singleton;
	private final String[] CREATE_SQL = new String[] {

			
			"CREATE TABLE IF NOT EXISTS " + TB_ATHLETE+ "(" + ATHLETE_ID
					+ " integer not null primary key autoincrement," + "" + ATHLETE_NAME
					+ " text not null," + "" + ATHLETE_GENDER
					+ " text not null," + "" + ATHLETE_BIRTHDATE
					+ " text not null);",

			"CREATE TABLE IF NOT EXISTS " + TB_MUSCLE + "(" + MUSCLE_ID
					+ " integer not null primary key autoincrement," + "" + MUSCLE_DESCRIPTION + " text not null);",
					
			"CREATE TABLE IF NOT EXISTS " + TB_SIZE+ "(" + SIZE_ID
					+ " integer not null primary key autoincrement," + "" + SIZE_DATE
					+ " text not null," + "" + SIZE_VALUE
					+ " real not null," + "" + SIZE_ATHLETE_ID
					+ " integer not null," +""+ SIZE_MUSCLE_ID
					+ " integer not null, foreign key("+SIZE_ATHLETE_ID+") references "+TB_ATHLETE
					+ "("+SIZE_ATHLETE_ID+"), foreign key("+SIZE_MUSCLE_ID+") references "+TB_MUSCLE
					+ "("+SIZE_MUSCLE_ID+"));",			
					
			"CREATE TABLE IF NOT EXISTS " + TB_MARK+ "(" + MARK_ID
					+ " integer not null primary key autoincrement," + "" + MARK_NAME
					+ " text not null," + "" + MARK_INITIAL_DATE
					+ " text not null," + "" + MARK_FINAL_DATE
					+ " text not null," + "" + MARK_ATHLETE_ID
					+ " integer not null, foreign key("+MARK_ATHLETE_ID+") references "+TB_ATHLETE
					+ "("+MARK_ATHLETE_ID+"));",
					
			"CREATE TABLE IF NOT EXISTS " + TB_EXERCISE_TYPE + "(" + EXERCISE_TYPE_ID
					+ " integer not null primary key autoincrement," + "" + EXERCISE_TYPE_NAME + " text not null);",
					
			
			"CREATE TABLE IF NOT EXISTS " + TB_EXERCISE+ "(" + EXERCISE_ID
					+ " integer not null primary key autoincrement," + "" + EXERCISE_INSTRUCTIONS
					+ " text not null," + "" + EXERCISE_DATE
					+ " integer not null," + "" + EXERCISE_WEIGHT
					+ " real not null," + "" +EXERCISE_REPETITIONS
					+ " integer not null," + "" +EXERCISE_DURATION
					+ " real not null," + "" +EXERCISE_ATHLETE_ID
					+ " integer not null," +""+ EXERCISE_EXERCISE_TYPE_ID
					+ " integer not null, foreign key("+EXERCISE_ATHLETE_ID+") references "+TB_ATHLETE
					+ "("+SIZE_ATHLETE_ID+"), foreign key("+EXERCISE_EXERCISE_TYPE_ID+") references "+TB_EXERCISE_TYPE
					+ "("+EXERCISE_EXERCISE_TYPE_ID+"));"

					
	};
	
	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;

	private DataBase(Context c) {
		dbHelper = new SQLiteHelper(c, "treinador", 30, CREATE_SQL);
		setDB(dbHelper.getWritableDatabase());
	}

	public void setDB(SQLiteDatabase db) {
		this.db = db;
	}

	public SQLiteDatabase getDB() {
		return db;
	}

	public void close() {
		singleton = null;
		try {
			db.close();
		} catch (SQLiteException e) {

		}
	}

	public static void start(Context ctx) {
		if (singleton == null)
			singleton = new DataBase(ctx);

	}

	public static DataBase getInstance(Context ctx) {
		if (singleton == null)
			singleton = new DataBase(ctx);
		return singleton;
	}

	public boolean isExecuting() {
		return isExecuting;
	}

	public void setExecuting(boolean isExeciting) {
		this.isExecuting = isExeciting;
	}
	
	public static boolean isOppened(){
		if(DataBase.singleton==null)
			return false;
		
		return true;
	}
	

}
