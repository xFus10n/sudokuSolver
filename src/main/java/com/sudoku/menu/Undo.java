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
        int moveNumber = sudokuField.getMoveNumber();
        if (moveNumber == 0) return;
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            sudokuField.undoFieldElement(i);
        }
        sudokuField.setMoveNumber(moveNumber - 1);
        Validation.validate();
    }
}
