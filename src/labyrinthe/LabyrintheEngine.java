package labyrinthe;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;













import donnee.Bloc.Type;
import donnee.Bloc;
import donnee.Boule;
import donnee.GrapheMat;
import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LabyrintheEngine {
    private Boule mBoule = null;
    private GrapheMat graphe =null ;
    private HashMap<Integer, Bloc> vertices = new HashMap<Integer, Bloc>();
    private int numberOfVectices = 0;
    public Boule getBoule() {
        return mBoule;
    }

    public void setBoule(Boule pBoule) {
        this.mBoule = pBoule;
    }

    // Le labyrinthe
    private List<Bloc> mBlocks = null;

    private LabyrintheActivity mActivity = null;

    private SensorManager mManager = null;
    private Sensor mAccelerometre = null;

    SensorEventListener mSensorEventListener = new SensorEventListener() {

        @SuppressWarnings("deprecation")
		@Override
        public void onSensorChanged(SensorEvent pEvent) {
            float x = pEvent.values[0];
            float y = pEvent.values[1];

            if(mBoule != null) {
                // On met à jour les coordonnées de la boule
                RectF hitBox = mBoule.putXAndY(x, y);

                // Pour tous les blocs du labyrinthe
                for(Bloc block : mBlocks) {
                    // On crée un nouveau rectangle pour ne pas modifier celui du bloc
                    RectF inter = new RectF(block.getRectangle());
                    if(inter.intersect(hitBox)) {
                        // On agit différement en fonction du type de bloc
                        switch(block.getType()) {
                        case CHEMIN:
                            break;

                        case DEPART:
                            break;

                        case ARRIVEE:
                            mActivity.showDialog(LabyrintheActivity.VICTORY_DIALOG);
                            break;
						default:
                            mActivity.showDialog(LabyrintheActivity.DEFEAT_DIALOG);

							break;
                        }
                        break;
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

        }
    };

    public LabyrintheEngine(LabyrintheActivity pView) {
        mActivity = pView;
        mManager = (SensorManager) mActivity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
        mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    // Remet à zéro l'emplacement de la boule
    public void reset() {
        mBoule.reset();
    }

    // Arrête le capteur
    public void stop() {
        mManager.unregisterListener(mSensorEventListener, mAccelerometre);
    }

    // Redémarre le capteur
    public void resume() {
        mManager.registerListener(mSensorEventListener, mAccelerometre, SensorManager.SENSOR_DELAY_GAME);
    }

    // Construit le labyrinthe
    @SuppressWarnings("unused")
	public List<Bloc> buildLabyrinthe() {
    	vertices.put(1, new Bloc(Type.CHEMIN, 10, 4));
    	vertices.put(2, new Bloc(Type.CHEMIN, 0, 10));
    	vertices.put(3, new Bloc(Type.CHEMIN, 8, 5));
    	vertices.put(4, new Bloc(Type.CHEMIN, 9, 3));
    	vertices.put(5, new Bloc(Type.CHEMIN, 12, 13));
    	vertices.put(6, new Bloc(Type.CHEMIN, 4, 3));
    	vertices.put(7, new Bloc(Type.CHEMIN, 8, 11));
    	vertices.put(8, new Bloc(Type.CHEMIN, 9, 14));
    	vertices.put(9, new Bloc(Type.CHEMIN, 9, 7));

    	vertices.put(10, new Bloc(Type.ARRIVEE, 15, 19));

        mBlocks = new ArrayList<Bloc>();
        Bloc b = new Bloc(Type.DEPART, 0, 0);
    	vertices.put(0,b);
    	numberOfVectices = vertices.size();
        mBoule.setInitialRectangle(new RectF(b.getRectangle()));
        for(Bloc bloc : vertices.values()){
        	mBlocks.add(bloc);
        }
        do{
         graphe = new GrapheMat(numberOfVectices, 2);
        }while (!GrapheMat.existeChemin(0, numberOfVectices-1, graphe));
        GrapheMat.fermetureTransitive(graphe);
        for (int i = 0; i < graphe.nb ; i++) {
            for (int j = 0; j < graphe.nb; j++){
            	
            	if(graphe.m[i][j]==1){
            		int x=vertices.get(i).getpX() ;
            		int y=vertices.get(i).getpY();
            		while( x<vertices.get(j).getpX() && y<vertices.get(j).getpY()){

                			mBlocks.add(new Bloc(Type.CHEMIN, x, y));
                			mBlocks.add(new Bloc(Type.CHEMIN, x, y+1));

                		y++;
                		x++;
            		}
            		while( !(x<vertices.get(j).getpX()) && y<vertices.get(j).getpY()){

            			mBlocks.add(new Bloc(Type.CHEMIN, x, y));

            		y++;
        		}
            		while( x<vertices.get(j).getpX() && !(y<vertices.get(j).getpY())){

            			mBlocks.add(new Bloc(Type.CHEMIN, x, y));

            		x++;
        		}

            	}
            	
            }
        }
       



        return mBlocks;
    }

}