package com.sudoku.menu;

import com.sudoku.Field;

public interface Action {
    int id();
    String name();
    void execute(Field sudokuField);
}
