package com.sudoku.menu;

import com.sudoku.Field;

public interface Action {
    String name();
    void execute(Field sudokuField);
}
