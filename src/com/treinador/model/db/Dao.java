
package com.treinador.model.db;

import android.content.Context;

public abstract class Dao {
	protected DataBase db;
	protected Context ctx;
	
	protected Dao(Context ctx){
		db=DataBase.getInstance(ctx);
	}
	
}
