package com.sudoku.menu;

import com.sudoku.Field;

public class Show implements Action{
    @Override
    public String name() {
        return "print sudoku fields";
    }

    @Override
    public void execute(Field sudokuField) {
          sudokuField.showFields();
    }
}
