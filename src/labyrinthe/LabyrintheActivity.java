package labyrinthe;

import java.util.List;









import donnee.Bloc;
import donnee.Boule;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LabyrintheActivity extends Activity {
    /* the number of life */
    private TextView texte;
	  public final static String DONNEE = "DONNEE";

	// Identifiant de la bo�te de dialogue de victoire
	public static final int VICTORY_DIALOG = 0;
	// Identifiant de la bo�te de dialogue de d�faite
	public static final int DEFEAT_DIALOG = 1;

	// Le moteur graphique du jeu
	private LabyrintheView mView = null;
	// Le moteur physique du jeu
	private LabyrintheEngine mEngine = null;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mView = new LabyrintheView(this);
		setContentView(mView);

		mEngine = new LabyrintheEngine(this);

		Boule b = new Boule();
		mView.setBoule(b);
		mEngine.setBoule(b);

		List<Bloc> mList = mEngine.buildLabyrinthe();
		mView.setBlocks(mList);
	}
	

	
	@Override
	protected void onResume() {
		super.onResume();
		mEngine.resume();
	} 

	@Override
	protected void onPause() {
		super.onStop();
		mEngine.stop();
	}

	public Dialog onCreateDialog (int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case VICTORY_DIALOG:
			builder.setCancelable(false)
			.setMessage("Congratulation !")
			.setTitle("you find the way!")
			.setNeutralButton("Go to the menu", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent result = new Intent();
			        result.putExtra(DONNEE, 0);
		        setResult(RESULT_OK, result);
			        finish();
			        }
			});
			break;

		case DEFEAT_DIALOG:
			builder.setCancelable(false)
			.setMessage("Game over")
			.setTitle("Game over")
			.setNeutralButton("Go to the menu", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent result = new Intent();
			        result.putExtra(DONNEE, 1);
			        setResult(RESULT_OK, result);
			        finish();
			        				
				}
			});
		}
		return builder.create();
	}

	
	@Override
	public void onPrepareDialog (int id, Dialog box) {
		// A chaque fois qu'une bo�te de dialogue est lanc�e, on arr�te le moteur physique
		mEngine.stop();
	}
}