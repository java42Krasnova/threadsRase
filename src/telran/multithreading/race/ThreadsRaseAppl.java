package telran.multithreading.race;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class ThreadsRaseAppl {

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[]items= {new ThreadsRace(), Item.exit()};
		Menu menu = new Menu("Thread Race", items);
		menu.perform(io);
	}

}
