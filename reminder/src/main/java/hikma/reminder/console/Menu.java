package hikma.reminder.console;

import java.util.Scanner;
public class Menu {
    public static int printMenu(String[] options, Scanner scanner){
        for(int i = 0; i < options.length; i++) {
            String menuOption = String.format("%s - %s", i, options[i]);
            System.out.println(menuOption);
        }
        System.out.print("Choose your option : ");
        return scanner.nextInt();
    }
}
