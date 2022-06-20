package com.sudoku.menu;

import com.sudoku.Field;

public class Validate implements Action{
    @Override
    public int id() {
        return 5;
    }

    @Override
    public String name() {
        return "validate sudoku field";
    }

    @Override
    public void execute(Field sudokuField) {

    }
}
