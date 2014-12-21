package time;

public class Chronometre {
	private Timer minutes = new Timer(0, ChronometreParameter.MAX_MINUTES, 0);
	private Timer seconds = new Timer(0, ChronometreParameter.MAX_SECONDS, 0);

	public Chronometre(int hours,int minutes, int seconds) {
		this.minutes = new Timer(minutes, ChronometreParameter.MAX_MINUTES, 0);
		this.seconds = new Timer(seconds, ChronometreParameter.MAX_SECONDS, 0);
	}

	
	/**
	 * Méthode qui incrémente le temps
	 */
	public void increment() {
		seconds.increment();
		if (seconds.getValue() == ChronometreParameter.MAX_SECONDS) {
			minutes.increment();
		} 
	}
	
	/**
	 * Méthode qui decremente le temps
	 */
	public void decrement() {
		seconds.decrement();
		if (seconds.getValue() == ChronometreParameter.MAX_SECONDS) {
			minutes.decrement();
		}
	}

	/**
	 * Méthode qui retourne les secondes
	 * @return seconds
	 */
	public Timer getSeconds() {
		return seconds;
	}

	/**
	 * Méthode qui retourne les minutes
	 * @return minutes
	 */
	public Timer getMinutes() {
		return minutes;
	}

	/**
	 * Méthode qui retourne l'état du chronometre. Si le chronometre est vide ou non
	 * @return true or false
	 */
	public Boolean isEmpty() {
		if (minutes.isEmpty() && seconds.isEmpty()) {
			return true;
		}
		return false;
	}

	
	/**
	 * Méthode toString qui retourne les valeurs du chronometre sous forme minutes:secondes
	 */
	public String toString() {
		return minutes.toString() + ":" + seconds.toString();
	}
}
