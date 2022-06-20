package com.sudoku.menuHandler;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.menu.Action;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {
    private final int menuSize;
    private final Scanner scanner;
    private final ConsoleLogger consoleLogger = ConsoleLogger.getInstance();
    private final TreeMap<Integer, Action> services;
    public Menu() {
        scanner = new Scanner(System.in);
        services = ActionsHandler.getServices();
        menuSize = services.size();
    }

    public void run(){
        attachShutDownHook();
        consoleLogger.toConsole("*** Starting menu ***");
        while (true) {
            consoleLogger.toConsole("");
            for (Map.Entry<Integer, Action> entry : services.entrySet()) {
                consoleLogger.toConsole(entry.getKey() + " : " + entry.getValue().name());
            }
            consoleLogger.toConsole("");
            consoleLogger.toConsole("Choose option: ", true);
            try {
               int option = scanner.nextInt();
               if (option < 0 || option > menuSize) continue;
               services.get(option).execute(Field.getInstance());
            } catch (Exception e) {
                consoleLogger.toConsole(e.getMessage());
                System.exit(1);
            }
        }
    }

    private void attachShutDownHook() {
        ConsoleLogger logger = ConsoleLogger.getInstance();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
            logger.toConsole("application shutdown");
        }));
    }
}
