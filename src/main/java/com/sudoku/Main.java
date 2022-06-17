package com.sudoku;
import com.sudoku.menuHandler.Menu;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        var main = new Main();
        main.run(args);
    }

    private void run(String[] args){
        new Menu().run();
    }
}
