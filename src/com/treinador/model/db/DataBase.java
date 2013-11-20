package com.treinador.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DataBase {

	private boolean isExecuting;
	// classe para o banco de Dados
	public static final String TB_ATHLETE = "athlete",
			//TB_MUSCULO = "musculo", 
			


			ATHLETE_ID = "idathlete", ATHLETE_NAME = "name",
					ATHLETE_BIRTHDATE = "birthdate", ATHLETE_GENDER = "gender"
			
			//MUSCULO_ID = "idmusculo", MUSCULO_DESCRICAO = "descricao"
			;

	private static DataBase singleton;
	private final String[] CREATE_SQL = new String[] {

			
			"CREATE TABLE IF NOT EXISTS " + TB_ATHLETE+ "(" + ATHLETE_ID
					+ " integer primary key autoincrement," + "" + ATHLETE_NAME
					+ " text not null," + "" + ATHLETE_GENDER
					+ " text not null," + "" + ATHLETE_BIRTHDATE
					+ " text not null);",

			/*"CREATE TABLE IF NOT EXISTS " + TB_MUSCULO + "(" + MUSCULO_ID
					+ " integer primary key," + "" + MUSCULO_DESCRICAO + " text not null);",*/

					
			//inserindo atletas para teste
			"INSERT INTO "+ TB_ATHLETE + " VALUES('David Pinho', 'Masculino','1993-07-08')"
	};
	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;

	public DataBase(Context c) {
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
