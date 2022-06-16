package com.sudoku;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        var main = new Main();
        main.run(args);
    }

    private void run(String[] args){
        var sudokuField = Field.getInstance();
        sudokuField.setField(0,3);
        sudokuField.setField(8,3);
        sudokuField.setField(72,3);
        sudokuField.setField(80,3);
        sudokuField.showFields();
    }
}
