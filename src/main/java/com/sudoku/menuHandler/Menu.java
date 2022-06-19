package com.sudoku.menuHandler;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.menu.Action;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {
    private final Scanner scanner;
    private final ConsoleLogger consoleLogger = ConsoleLogger.getInstance();
    private final TreeMap<Integer, Action> services;
    public Menu() {
        scanner = new Scanner(System.in);
        services = ActionsHandler.getServices();
    }

    public void run(){
        attachShutDownHook(scanner);
        consoleLogger.toConsole("*** Starting menu ***");
        while (true) {
            for (Map.Entry<Integer, Action> entry : services.entrySet()) {
                consoleLogger.toConsole(entry.getKey() + " : " + entry.getValue().name());
            }
            consoleLogger.toConsole("Choose option: ", true);
            try {
               int option = scanner.nextInt();
               services.get(option).execute(Field.getInstance());
            } catch (Exception e) {
                consoleLogger.toConsole(e.getMessage());
            }
            consoleLogger.toConsole("");
        }
    }

    public void attachShutDownHook(Scanner scanner) {
        ConsoleLogger logger = ConsoleLogger.getInstance();
        logger.toConsole("attaching shutdown hook");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
            logger.toConsole("application shutdown");
        }));
    }
}
