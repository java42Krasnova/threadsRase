package telran.multithreading.race;

public enum ConstatsRange {
	  MIN_THREADS(3),
	  MAX_THREADS(10),
	  MIN_DISTANCE(100),
	  MAX_DISTANCE(3500),
	  MIN_SLEEP_TIME(2),
	  MAX_SLEEP_TIME(5);

	  private int value;
	ConstatsRange(int value) {
			this.value = value;
		}
	public int getValue() {
		return value;
	}
}