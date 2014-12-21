package time;

public class Chronometre {
	private Timer minutes = new Timer(0, ChronometreParameter.MAX_MINUTES, 0);
	private Timer seconds = new Timer(0, ChronometreParameter.MAX_SECONDS, 0);

	public Chronometre(int hours,int minutes, int seconds) {
		this.minutes = new Timer(minutes, ChronometreParameter.MAX_MINUTES, 0);
		this.seconds = new Timer(seconds, ChronometreParameter.MAX_SECONDS, 0);
	}

	public void increment() {
		seconds.increment();
		if (seconds.getValue() == ChronometreParameter.MAX_SECONDS) {
			minutes.increment();
		} 
	}
	
	public void decrement() {
		seconds.decrement();
		if (seconds.getValue() == ChronometreParameter.MAX_SECONDS) {
			minutes.decrement();
		}
	}

	public Timer getSeconds() {
		return seconds;
	}

	public Timer getMinutes() {
		return minutes;
	}


	public Boolean isEmpty() {
		if (minutes.isEmpty() && seconds.isEmpty()) {
			return true;
		}
		return false;
	}

	public String toString() {
		return minutes.toString() + ":" + seconds.toString();
	}
}
