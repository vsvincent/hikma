package hikma.reminder;

import java.util.Scanner;

import static hikma.reminder.console.Menu.printMenu;

public class ReminderApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] options = {"Prayer", "Fasting", "Exit"};
		int selection = printMenu(options, scanner);
		//TODO use variable selection by implementing either reflection or a simple switch statement.
	}
}
