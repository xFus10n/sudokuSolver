package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.properties.Arguments;
import java.util.Collections;

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
        var values = Arguments.getInstance().getValues();
        if (values.size() < Field.FIELD_CAPACITY) {
            int missingElements = Field.FIELD_CAPACITY - values.size();
            values.addAll(Collections.nCopies(missingElements, 0));
        }
        sudokuField.setFields(values);
    }
}
