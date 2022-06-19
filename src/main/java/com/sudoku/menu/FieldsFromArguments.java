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
        sudokuField.resetFields();
        var fieldCapacity = Field.FIELD_CAPACITY;
        var values = Arguments.getInstance().getValues();
        if (values.size() < 80) {
            int missingElements = fieldCapacity - values.size();
            values.addAll(Collections.nCopies(missingElements, 0));
        }
        sudokuField.setFields(values);
    }
}
