package com.sudoku.menu;

import com.sudoku.Field;

public class Exit implements Action{
    @Override
    public int id() {
        return 0;
    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void execute(Field sudokuField) {
        System.exit(0);
    }
}
