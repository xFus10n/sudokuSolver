package com.sudoku;
import com.sudoku.menuHandler.Menu;


public class Main {
    public static void main(String[] args) {
        var main = new Main();
        main.run(args);
    }

    private void run(String[] args){
        new Menu().run();
    }
}
