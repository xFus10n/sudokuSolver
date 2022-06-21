package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

public class Solve implements Action{
    @Override
    public int id() {
        return 6;
    }

    @Override
    public String name() {
        return "solve sudoku puzzle";
    }

    @Override
    public void execute(Field sudokuField) {
        boolean solvable = sudokuField.solvable();
        ConsoleLogger.getInstance().toConsole("Sudoku is solvable = " + solvable);
    }
}
