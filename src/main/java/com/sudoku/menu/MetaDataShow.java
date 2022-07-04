package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.logger.ConsoleLogger;

import java.util.Scanner;

public class MetaDataShow implements Action{
    @Override
    public int id() {
        return 7;
    }

    @Override
    public String name() {
        return "show meta data (in dev)";
    }

    @Override
    public void execute(Field sudokuField) {
        ConsoleLogger logger = ConsoleLogger.getInstance();
        Field.printPositionHelp();
        Field sField = Field.getInstance();
        Scanner scanner = sField.getScanner();
        try {
            logger.toConsole("Enter position: ", true);
            int pos = scanner.nextInt();
            if (pos< 0 || pos > Field.FIELD_CAPACITY - 1) {
                logger.toConsole("Accepted values range are 0 ... 80");
                return;
            }
            int fieldValue = sField.getFieldValue(pos);
            logger.toConsole("Position value is = " + fieldValue);

            logger.toConsole("Meta data for position : ", true);
            logger.toConsole(sField.getFieldElement(pos).toString());

        } catch (Exception e) {
            logger.toConsole(e.getMessage());
            scanner.close();
        }
    }
}
