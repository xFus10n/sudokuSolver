package com.sudoku.menuHandler;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.menu.Action;
import org.apache.logging.log4j.LogManager;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {
    private final int menuSize;
    private final Scanner scanner;
    private final ConsoleLogger consoleLogger = ConsoleLogger.getInstance();
    private final TreeMap<Integer, Action> services;
    public Menu() {
        scanner = Field.getInstance().getScanner();
        services = ActionsHandler.getServices();
        menuSize = services.size();
    }

    public void run(){
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
                consoleLogger.toConsole(e.toString());
                System.exit(1);
            }
        }
    }

}
