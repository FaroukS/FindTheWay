package time;

public class Timer {
	private int value;
	private int max;
	private int min;

	public Timer() {
		this(0, 0, 0);
	}

	public Timer(int value, int max, int min) {
		this.value = value;
		this.max = max;
		this.min = min;
	}
	
	public void increment(){
		if (value < max) {
			value++;
		} else {
			value = min;
		}
	}

	public void decrement() {
		if (value > min) {
			value--;
		} else {
			value = max;
		}
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Boolean isEmpty() {
		if (value == min) {
			return true;
		}
		return false;
	}

	public String toString() {
		String result = "";
		if (value < 10) {
			result = "0" + value;
		} else {
			result = String.valueOf(value);
		}
		return result;
	}
}
