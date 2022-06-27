package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

import java.util.Scanner;

public class Position implements Action {
    private final ConsoleLogger logger = ConsoleLogger.getInstance();

    @Override
    public int id() {
        return 2;
    }

    @Override
    public String name() {
        return "set sudoku field value";
    }

    @Override
    public void execute(Field sudokuField) {
        Field.printPositionHelp();
        Scanner scanner = Field.getInstance().getScanner();
        try {
            logger.toConsole("Enter position: ", true);
            int pos = scanner.nextInt();
            logger.toConsole("Enter value: ", true);
            int value = scanner.nextInt();
            boolean success = sudokuField.setField(pos, value);
            logger.toConsole("Set value: " + value + " on position " + pos + " = " + success);
        } catch (Exception e) {
            logger.toConsole(e.getMessage());
            scanner.close();
        }
    }
}
