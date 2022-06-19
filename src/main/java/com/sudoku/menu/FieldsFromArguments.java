package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.properties.Arguments;

public class FieldsFromArguments implements Action{
    @Override
    public int id() {
        return 4;
    }

    @Override
    public String name() {
        return "set sudoku fields from positional arguments";
    }

    @Override
    public void execute(Field sudokuField) {
        Arguments.getInstance().getValues().forEach(System.out::println);
    }
}
