package telran.multithreading.race;

import java.util.Random;

public class ThreadRunner implements Runnable {
	private static int distance;
	private static int minSleepTime;
	private static int maxSleepTime;
	private static int winner;
	private int threadNum;

	public ThreadRunner(int thredNum) {
		this.threadNum = thredNum;
	}

	public int getThredNum() {
		return threadNum;
	}

	public static void setDistanse(int distanse) {
		ThreadRunner.distance = distanse;
	}

	public static void setMinSleepTime(int minSleepTime) {
		ThreadRunner.minSleepTime = minSleepTime;
	}

	public static void setMaxSleepTime(int maxSleepTime) {
		ThreadRunner.maxSleepTime = maxSleepTime;
	}
	
	public static int getWiner() {
		return winner;
	}

	@Override
	public void run() {
		int sleepTime;
		for (int i = 0; i <= distance; i++) {
			sleepTime = new Random().ints(minSleepTime, maxSleepTime + 1).findFirst().getAsInt();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadNum);
		}
		if (winner == 0) {
			winner = threadNum;
		}
	}
}
