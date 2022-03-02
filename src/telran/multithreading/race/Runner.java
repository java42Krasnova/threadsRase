package telran.multithreading.race;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

public class Runner extends Thread {
private Race race;
private int runnerId;
public int getRunnerId() {
	return runnerId;
}
private static Instant startTime ;
private static  final ArrayList<Runner> runnerList = new ArrayList<>();
private long runningTime ;

public long getRunningTime() {
	return runningTime;
}
public static void setStartTime(Instant startTime) {
	Runner.startTime = startTime;
}

public static ArrayList<Runner> getRunnerlist() {
	return runnerList;
}


public Runner(Race race, int runnerId) {
	this.race = race;
	this.runnerId = runnerId;
}

@Override
public void run() {
	int minSleep = race.getMinSleep();
	int maxSleep = race.getMaxSleep();
	int distance = race.getDistance();
	for (int i = 0; i < distance; i++) {
		try {
			sleep(new Random().ints(minSleep, maxSleep+1).findAny().getAsInt());
		} catch (InterruptedException e) {
			throw new IllegalStateException();
		}
		System.out.println(runnerId);
	}
	
synchronized (runnerList) {
		runningTime = ChronoUnit.MILLIS.between(startTime, Instant.now());
		runnerList.add(this);
	}
}
}