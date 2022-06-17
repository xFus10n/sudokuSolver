package com.sudoku.menu;

import com.sudoku.Field;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Position implements Action {
    private final Logger logger = Logger.getLogger(Position.class);

    @Override
    public int id() {
        return 2;
    }

    @Override
    public String name() {
        return "set sudoku field value";
    }

    @Override
    public void execute(Field sudokuField) {
        printHelp();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter position: ");
            int pos = scanner.nextInt();
            System.out.print("Enter value: ");
            int value = scanner.nextInt();
            boolean success = sudokuField.setField(pos, value);
            logger.info("Set value: " + value + " on position " + pos + " = " + success);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void printHelp() {
        logger.info("[ 0,  1,  2  ] [ 3,  4,  5  ]  [ 6,  7,  8  ]");
        logger.info("[ 9,  10, 11 ] [ 12, 13, 14 ]  [ 15, 16, 17 ]");
        logger.info("[ 18, 19, 20 ] [ 21, 22, 23 ]  [ 24, 25, 26 ]");
        logger.info("");
        logger.info("[ 27, 28, 29 ] [ 30, 31, 32 ]  [ 33, 34, 35 ]");
        logger.info("[ 36, 37, 38 ] [ 39, 40, 41 ]  [ 42, 43, 44 ]");
        logger.info("[ 45, 46, 47 ] [ 48, 49, 50 ]  [ 51, 52, 53 ]");
        logger.info("");
        logger.info("[ 54, 55, 56 ] [ 57, 58, 59 ]  [ 60, 61, 62 ]");
        logger.info("[ 63, 64, 65 ] [ 66, 67, 68 ]  [ 69, 70, 71 ]");
        logger.info("[ 72, 73, 74 ] [ 75, 76, 77 ]  [ 78, 79, 80 ]");
    }
}
