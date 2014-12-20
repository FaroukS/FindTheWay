package com.example.findtheway;


import com.example.findtheway.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainMenu extends Activity {
	  private Button mPasserelle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
	    mPasserelle = (Button) findViewById(R.id.levelg);

    
    mPasserelle.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	    	  // Le premier param�tre est le nom de l'activit� actuelle
		        // Le second est le nom de l'activit� de destination
		        Intent secondeActivite = new Intent(MainMenu.this, labyrinthe.LabyrintheActivity.class);
		        
		    

		        // Puis on lance l'intent !
		        startActivity(secondeActivite);
		      }
		    });
		}
	      

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
