package com.sudoku;
import com.sudoku.menu.Action;
import com.sudoku.menu.Erase;
import com.sudoku.menu.Position;
import com.sudoku.menu.Show;
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


        Action show = new Show();
        Action erase = new Erase();
        Action position = new Position();

        Field sFields = Field.getInstance();
        logger.info(show.name());
        show.execute(sFields);

        logger.info(position.name());
        position.execute(sFields);
        show.execute(sFields);

        logger.info(erase.name());
        erase.execute(sFields);
        show.execute(sFields);
    }
}
