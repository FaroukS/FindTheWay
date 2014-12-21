package com.example.findtheway;



import labyrinthe.LabyrintheActivity;

import com.example.findtheway.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainMenu extends Activity {
	  private Button mPasserelle ;

	    private  TextView texte;
	    private  TextView time;

		/* the number of life */

		private int life = 7;


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
		        startActivityForResult(secondeActivite, 1);
		      }
		    });
		}

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == 1) {
          // On vérifie aussi que l'opération s'est bien déroulée
          if (resultCode == RESULT_OK) {
            // On affiche le bouton qui a été choisi
        	 if( data.getIntExtra(LabyrintheActivity.DONNEE,0)==1){
        		 if(life>0){
     				texte = (TextView) findViewById(R.id.nbVie);
     				life--;
     				texte.setText( "X "+ life);
     			}   
        	 }
          }
        }
    }
	
    public MainMenu() {
		super();
		// TODO Auto-generated constructor stub
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
