package com.treinador.activity;

import com.treinador.R;
import com.treinador.R.layout;
import com.treinador.R.menu;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainIntroduction extends Activity {
	
	 private final int SPLASH_DISPLAY_LENGHT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_mark);
		getActionBar().hide();
		
		new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainIntroduction.this,RegisterAthlete.class);
                MainIntroduction.this.startActivity(mainIntent);
                MainIntroduction.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.introducao, menu);
		return true;
	}

}
