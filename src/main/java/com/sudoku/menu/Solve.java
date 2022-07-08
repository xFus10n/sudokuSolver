package com.sudoku.menu;

import com.sudoku.Field;
import com.sudoku.domain.ActionType;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.properties.Status;
import com.sudoku.utils.FieldUtilz;
import com.sudoku.utils.Utilz;
import com.sudoku.utils.Validation;

import java.util.List;

import static com.sudoku.Field.FIELD_CAPACITY;

public class Solve implements Action{
    private static final int SOLVABLE_AMOUNT_ELEMENTS = 17;

    @Override
    public int id() {
        return 6;
    }

    @Override
    public String name() {
        return "solve sudoku puzzle";
    }

    @Override
    public void execute(Field sudokuField) {
        boolean solvable = solvable(sudokuField);
        ConsoleLogger logger = ConsoleLogger.getInstance();
        logger.toConsole("Sudoku is solvable = " + solvable);
        if (!solvable) return;
        if (sudokuField.getStatus() == Status.FAILED) return;

        int initialMoveNumber = sudokuField.getMoveNumber();
        int iterations = 0;
        Show show = new Show();
        try {
            while (sudokuField.getStatus() != Status.SOLVED) {
                ConsoleLogger.getInstance().toConsole("Iteration : " + iterations++ + '\r', true);
                for (int position : Utilz.getShuffledList()){
                    if (sudokuField.getFieldValue(position) != 0) continue;
                    List<Integer> positionCandidates = sudokuField.getPositionCandidates(position);
                    if (positionCandidates.isEmpty()) {
                        Utilz.undo(sudokuField, initialMoveNumber, true);
                        break;
                    }
                    int pickupNumber = Utilz.chooseRandomInteger(positionCandidates);
                    sudokuField.setField(position, pickupNumber);
                    if (sudokuField.getStatus() == Status.FAILED) {
                        Utilz.undo(sudokuField, initialMoveNumber, true);
                        break;
                    }
                }
                if (sudokuField.getStatus() != Status.SOLVED) Utilz.undo(sudokuField, initialMoveNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean solvable(Field sudokuField) {
        int[][] fields = sudokuField.getSudokuFields();
        int count = 0;
        for (int[] field : fields) {
            for (int i : field) {
                if (0 == i) {
                    count++;
                }
            }
            if (FIELD_CAPACITY - count < SOLVABLE_AMOUNT_ELEMENTS) {
                return false;
            }
        }
        return true;
    }
}
