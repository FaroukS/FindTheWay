package com.example.findtheway;



import labyrinthe.LabyrintheActivity;
import time.Chronometre;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenu extends Activity {
	  private Button mPasserelle ;
	  public  int min = 0;
		public  int seconde = 0;
	    private  TextView texte ;
	    public   Chronometre chronometre=new Chronometre(0, min, seconde);
	    private  TextView  time ;
	    public CountDownTimer chrono = null;
	    private SharedPreferences sharedPreferences;
	    private String nbOfLife="nb";

		/* the number of life */

		public int life = 7;
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

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
       
        
    }
    public void onStart(){
    	super.onStart();
    	if(life > 0){

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
		}else{
	    	Toast.makeText(this, "You don't have life anymore, you have to wait.",  Toast.LENGTH_SHORT).show();;
	    }
       
    	time = (TextView) findViewById(R.id.time);
   	 	texte = (TextView) findViewById(R.id.nbVie);

        while(life<7){

        	chrono = new CountDownTimer(60000, 1000) {

        	     public void onTick(long millisUntilFinished) {
        	         time.setText(" " + millisUntilFinished / 1000);

        	     }

        	     public void onFinish() {
        	         time.setText("00:00");

        	     }
        	  }.start();
        	  life++;
     			texte.setText( "X "+ life);

        }
    }
        
    public void onResume(){
    	super.onResume();
    	sharedPreferences = getSharedPreferences("life", MODE_PRIVATE);
   	 	life = sharedPreferences.getInt(nbOfLife, 7);
    }
   
    public void onPause(){
    	super.onPause();
    	SharedPreferences.Editor editor = sharedPreferences.edit();
    	editor.putInt(nbOfLife, life);
    	editor.commit();
    }

   
	
    public void reloadLife(){
    	 /*reloading the life */
    	int min = 0;
		int seconde = 0;
		long millis =  java.lang.System.currentTimeMillis();
        Chronometre chronometre = new Chronometre(0, min, seconde);
			time = (TextView) findViewById(R.id.time);

        while(life<7){
            while(chronometre.getMinutes().getValue() < 2){
            	try {
    				Thread.sleep(millis);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            	chronometre.increment();
 				time.setText( chronometre.toString());
            }
            life++;
            texte = (TextView) findViewById(R.id.nbVie);
				texte.setText( "X "+ life);
            chronometre.getMinutes().setValue(0);
            chronometre.getSeconds().setValue(0);
            }
    	
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
