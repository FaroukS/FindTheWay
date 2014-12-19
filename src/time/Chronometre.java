package time;

public class Chronometre {
	private Timer hours = new Timer(0, ChronometreParameter.MAX_HOURS, 0);
	private Timer minutes = new Timer(0, ChronometreParameter.MAX_MINUTES, 0);
	private Timer seconds = new Timer(0, ChronometreParameter.MAX_SECONDS, 0);

	public Chronometre(int hours,int minutes, int seconds) {
		this.hours = new Timer(hours, ChronometreParameter.MAX_HOURS, 0);
		this.minutes = new Timer(minutes, ChronometreParameter.MAX_MINUTES, 0);
		this.seconds = new Timer(seconds, ChronometreParameter.MAX_SECONDS, 0);
	}

	public void increment() {
		seconds.increment();
		if (seconds.getValue() == ChronometreParameter.MAX_SECONDS) {
			minutes.increment();
			if (minutes.getValue() == ChronometreParameter.MAX_MINUTES) {
				hours.increment();
			}
		} 
	}
	
	public void decrement() {
		seconds.decrement();
		if (seconds.getValue() == ChronometreParameter.MAX_SECONDS) {
			minutes.decrement();
			if (minutes.getValue() == ChronometreParameter.MAX_MINUTES) {
				hours.decrement();
			}
		}
	}

	public Timer getSeconds() {
		return seconds;
	}

	public Timer getMinutes() {
		return minutes;
	}

	public Timer getHours() {
		return hours;
	}

	public Boolean isEmpty() {
		if (hours.isEmpty() && minutes.isEmpty() && seconds.isEmpty()) {
			return true;
		}
		return false;
	}

	public String toString() {
		return hours.toString() + ":" + minutes.toString() + ":" + seconds.toString();
	}
}
