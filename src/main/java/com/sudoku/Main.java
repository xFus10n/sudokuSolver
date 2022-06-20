package com.sudoku;
import com.sudoku.menuHandler.Menu;
import com.sudoku.properties.Arguments;


public class Main {
    public static void main(String[] args) {
        Arguments.initializeArgumentContainer(args);
        var main = new Main();
        main.run();
    }

    private void run(){
        new Menu().run();
    }
}
