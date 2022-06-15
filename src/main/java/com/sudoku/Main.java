package com.sudoku;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        var main = new Main();
        main.run("Starting application");
    }

    private void run(String argument){
        logger.info(argument);
        var sudokuField = Field.getInstance();
        sudokuField.setField(-1,2,3);
        sudokuField.setField(9,2,3);
        sudokuField.setField(0,-1,3);
        sudokuField.setField(10,10,3);
        sudokuField.setField(1,10,3);
        sudokuField.setField(0,0,5);
        sudokuField.setField(1,1,5);
        sudokuField.setField(2,2,5);
        sudokuField.showFields(logger);
    }
}
