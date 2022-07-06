package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.utils.Validation;

public class Undo implements Action{
    @Override
    public int id() {
        return 8;
    }

    @Override
    public String name() {
        return "undo last move";
    }

    @Override
    public void execute(Field sudokuField) {
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            sudokuField.undoFieldElement(i);
        }
        Validation.validate();
    }
}
