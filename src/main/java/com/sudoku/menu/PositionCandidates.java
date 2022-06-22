package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

import java.util.Scanner;

public class PositionCandidates implements Action{
    @Override
    public int id() {
        return 7;
    }

    @Override
    public String name() {
        return "show position candidates (in dev)";
    }

    @Override
    public void execute(Field sudokuField) {
        ConsoleLogger logger = ConsoleLogger.getInstance();
        Field.printPositionHelp();
        Scanner scanner = Field.getInstance().getScanner();
        try {
            logger.toConsole("Enter position: ", true);
            int pos = scanner.nextInt();

        } catch (Exception e) {
            logger.toConsole(e.getMessage());
            scanner.close();
        }
    }
}
