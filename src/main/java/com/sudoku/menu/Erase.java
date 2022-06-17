package com.sudoku.menu;

import com.sudoku.Field;

public class Erase implements Action{
    @Override
    public int id() {
        return 3;
    }

    @Override
    public String name() {
        return "initialize sudoku field";
    }

    @Override
    public void execute(Field sudokuField) {
        sudokuField.resetFields();
    }
}
