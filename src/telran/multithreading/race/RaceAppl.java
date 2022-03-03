package telran.multithreading.race;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import telran.view.*;

public class RaceAppl {

	private static final int MAX_THREADS = 20;
	private static final int MIN_DISTANCE = 10;
	private static final int MAX_DISTANCE = 1000;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);
	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Start new game", RaceAppl::startGame),
				Item.exit()
		};
		return res;
	}
	static void startGame(InputOutput io) {
		// V.R. There isn't range's explanation (2-20) in the message
		int nThreads = io.readInt("Enter number of the runners", 2, MAX_THREADS);
		// V.R. There isn't range's explanation (10-1000) in the message
		int distance = io.readInt("Enter distance", MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
		Runner[] runners = new Runner[nThreads];
		startRunners(runners, race);
		joinRunners(runners);
		displayWinner(race);
	}

	private static void displayWinner(Race race) {
		// V.R. There isn't congratulation to the winner
		ArrayList<Runner> winnerList = Runner.getRunnerlist();
		System.out.println("The Result Table\n" + "________________________");
		System.out.print("Place|  racer| 	 time\n" + "________________________\n");
			IntStream.range(0, winnerList.size())
			.forEach(i -> System.out.printf("  %d\t %d\t %d\n",
					i+1,winnerList.get(i).getRunnerId(), winnerList.get(i).getRunningTime()) );
	
	}

	private static void joinRunners(Runner[] runners) {
		Arrays.stream(runners).forEach(r -> {
			try {
				r.join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});
		
	}

	private static void startRunners(Runner[] runners, Race race) {
		Runner.getRunnerlist().clear();
		Runner.setStartTime(Instant.now());
		IntStream.range(0, runners.length).forEach(i -> {
			runners[i] = new Runner(race, i + 1);
			runners[i].start();
		});
		
	}

}