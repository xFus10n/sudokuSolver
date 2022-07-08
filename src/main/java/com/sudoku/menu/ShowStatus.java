package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

public class ShowStatus implements Action {

    @Override
    public int id() {
        return 5;
    }

    @Override
    public String name() {
        return "status of sudoku field";
    }

    @Override
    public void execute(Field sudokuField) {
        ConsoleLogger.getInstance().toConsole("sudoku fields status is : " + sudokuField.getStatus());
    }
}
