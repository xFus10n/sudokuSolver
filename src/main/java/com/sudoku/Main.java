package com.sudoku;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        Main main = new Main();
        main.run("Hello world");
    }

    private void run(String argument){
        logger.info(argument);
    }
}
