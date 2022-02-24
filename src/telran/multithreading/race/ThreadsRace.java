package telran.multithreading.race;

import java.util.ArrayList;
import java.util.List;

import telran.view.InputOutput;
import telran.view.Item;

// V.R. It is hard to understand what is the relation between Threadrace and Item    
public class ThreadsRace implements Item {
private int minThreads = ConstatsRange.MIN_THREADS.getValue();
private int maxTreads = ConstatsRange.MAX_THREADS.getValue();
private int minDistanse = ConstatsRange.MIN_DISTANCE.getValue();
private int maxDistance = ConstatsRange.MAX_DISTANCE.getValue();
private int minSleepTime = ConstatsRange.MIN_SLEEP_TIME.getValue();
private int maxSleepTime = ConstatsRange.MAX_SLEEP_TIME.getValue();
	
private List<Thread> threadsList = new ArrayList<>();
	@Override
	public String displayName() {
		return "Treads Race";
	}

	@Override
	public void perform(InputOutput io) {
		int nRunners = io.readInt(String.format("Please chose number of runners from %d to %d Threads", minThreads,
				maxTreads), minThreads, maxTreads);
		int distance = io.readInt(String.format("Please chose a distance from %d to %d", minDistanse,maxDistance),
				minDistanse, maxDistance);
		startRasing(nRunners, distance);
		io.writeObjectLine(String.format("Congratulations to Thread #%d", ThreadRunner.getWiner()));
		
	}

	private void startRasing(int nRunners, int distance) {
		setAndStartThreds(nRunners,distance);
		threadsList.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	private void setAndStartThreds(int nRunners, int distance) {
		ThreadRunner.setDistanse(distance);
		ThreadRunner.setMinSleepTime(minSleepTime);
		ThreadRunner.setMaxSleepTime(maxSleepTime);
		for(int i = 1; i<= nRunners; i++) {
			Thread runner = new Thread(new ThreadRunner(i));
			runner.start();
			threadsList.add(runner);
		}
	}

	// This method returns true and all is finished. Whee is menu here?
	@Override
	public boolean isExit() {
		return true;
	}

}
