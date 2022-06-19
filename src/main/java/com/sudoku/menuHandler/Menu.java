package com.sudoku.menuHandler;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.menu.Action;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {
    private final ConsoleLogger consoleLogger = ConsoleLogger.getInstance();
    private final TreeMap<Integer, Action> services;
    public Menu() {
        services = ActionsHandler.getServices();
    }

    public void run(){
        consoleLogger.toConsole("*** Starting menu ***");
        while (true) {
            for (Map.Entry<Integer, Action> entry : services.entrySet()) {
                consoleLogger.toConsole(entry.getKey() + " : " + entry.getValue().name());
            }
            consoleLogger.toConsole("Choose option: ", true);
            Scanner scanner = new Scanner(System.in);
            try {
               int option = scanner.nextInt();
               services.get(option).execute(Field.getInstance());
            } catch (Exception e) {
                consoleLogger.toConsole(e.getMessage());
            }
        }
    }
}
