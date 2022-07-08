package com.sudoku.utils;

import com.sudoku.Field;
import com.sudoku.properties.Status;

import java.util.*;

public final class Utilz {
    private static final PickInt rndInt = new PickInt();

    public static int chooseRandomInteger(List<Integer> pickupList) {
        return rndInt.get(pickupList);
    }

    public static List<Integer> getShuffledList() {
        List<Integer> shuffledList = new ArrayList<>(Field.FIELD_CAPACITY);
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            shuffledList.add(i);
        }
        Collections.shuffle(shuffledList);
        return shuffledList;
    }

    public static void undo(Field sudokuField, int move) {
        if (sudokuField.getMoveNumber() == move) return;
        if (sudokuField.getMoveNumber() == 0) {
            return;
        }
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            sudokuField.undoFieldElement(i, move);
        }
        sudokuField.setMoveNumber(move);
        Validation.validate();
    }

    public static void undo(Field sudokuField) {
        if (sudokuField.getMoveNumber() == 0) {
            return;
        }
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            sudokuField.undoFieldElement(i);
        }
        int moveNumber = sudokuField.getMoveNumber();
        sudokuField.setMoveNumber(moveNumber - 1);
        Validation.validate();
    }

    public static void undo(Field sudokuField, int move, boolean validatedStatus) {
        if (sudokuField.getMoveNumber() == 0) {
            return;
        }
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            sudokuField.undoFieldElement(i, move);
        }
        sudokuField.setMoveNumber(move);
        sudokuField.setValidatedStatus();
        Validation.validate();
    }

    abstract static class pickRandomValue<T extends Number> {
        private static final Random rnd = new Random();

        T get(List<T> list) {
            if (list.size() == 1) {
                return list.get(0);
            }
            if (list.isEmpty()) {
                return getDefault();
            }
            int position = rnd.nextInt(list.size());
            return list.get(position);
        }

        abstract T getDefault();
    }

    private static class PickInt extends pickRandomValue<Integer> {
        @Override
        Integer getDefault() {
            return 0;
        }
    }
}
