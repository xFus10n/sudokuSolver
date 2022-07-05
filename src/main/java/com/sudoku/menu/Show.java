package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

public class Show implements Action{
    public static final int STRING_CAPACITY = 320;

    @Override
    public int id() {
        return 1;
    }

    @Override
    public String name() {
        return "print sudoku fields";
    }

    @Override
    public void execute(Field sudokuField) {
          showFields(sudokuField);
    }

    /**
     * Print out sudoku fields
     */
    public void showFields(Field sudokuField) {
        ConsoleLogger logger = ConsoleLogger.getInstance();
        int[][] fields = sudokuField.getSudokuFields();
        for (int j = 0, sudokuFieldLength = fields.length; j < sudokuFieldLength; j++) {
            int[] arr = fields[j];
            StringBuilder output = new StringBuilder(STRING_CAPACITY);
            for (int i = 0, arrLength = arr.length; i < arrLength; i++) {
                if (i == 0) {
                    output.append("[ ")
                          .append(arr[0]);
                } else if (i == arrLength - 1) {
                    output.append(' ')
                          .append(arr[i])
                          .append(" ]");
                } else if ((i + 1) % 3 == 0) {
                    output.append(' ')
                          .append(arr[i])
                          .append(" ]  [");
                } else {
                    output.append(' ')
                          .append(arr[i]);
                }
            }
            logger.toConsole(output.toString()
                                   .replace("0", "*"));
            if ((j + 1) % 3 == 0) {
                logger.toConsole("");
            }
        }
    }
}
