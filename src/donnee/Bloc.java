package donnee;

import android.graphics.RectF;

public class Bloc {
	public enum  Type { CHEMIN, DEPART, ARRIVEE, TROU };
	
	private float SIZE = Boule.RAYON * 2;
	
	public Type mType = null;
	private RectF mRectangle = null;
	private int pX = 0 ;
	private int pY=0;
	
	public Type getType() {
		return mType;
	}

	public RectF getRectangle() {
		return mRectangle;
	}
/**
 * create a bloc
 * @param pType type of the bloc we have the choice between chemin (way), depart (start) and arrivee (end)
 * @param pX position in the axis of abscissa
 * @param pY position in the axis of y
 */
	public Bloc(Type pType, int pX, int pY) {
		this.mType = pType;
		this.pX=pX;
		this.pY=pY;
		this.mRectangle = new RectF(pX * SIZE, pY * SIZE, (pX + 1) * SIZE, (pY + 1) * SIZE);
	}

public int getpX() {
	return pX;
}


public int getpY() {
	return pY;
}


}
