package com.sudoku.menuHandler;

import com.sudoku.Field;
import com.sudoku.menu.Action;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {
    private final Logger logger = Logger.getLogger(Menu.class);
    private final TreeMap<Integer, Action> services;
    public Menu() {
        services = ActionsHandler.getServices();
    }

    public void run(){
        logger.info("*** Starting menu ***");
        while (true) {
            for (Map.Entry<Integer, Action> entry : services.entrySet()) {
                logger.info(entry.getKey() + " : " + entry.getValue().name());
            }
            System.out.print("Choose option: ");
            Scanner scanner = new Scanner(System.in);
            try {
               int option = scanner.nextInt();
               services.get(option).execute(Field.getInstance());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
