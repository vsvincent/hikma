package hikma.reminder.console;

import java.util.Scanner;

public class Input {
    public static boolean getUserInputExitKey(Scanner scanner) {
        System.out.println("Please enter the q-key to exit.");
        String input = scanner.nextLine();
        if (input.contentEquals("q")) return true;
        else return false;
    }
}
