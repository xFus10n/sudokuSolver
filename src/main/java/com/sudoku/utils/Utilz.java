package com.sudoku.utils;

import com.sudoku.Field;

import java.util.*;

public final class Utilz {
    private final PickInt rndInt = new PickInt();

    public static int chooseRandomInteger(List<Integer> pickupList) {
        return new PickInt().get(pickupList);
    }

    public static void undo(Field sudokuField, int move) {
        if (sudokuField.getMoveNumber() == 0) return;
        while (sudokuField.getMoveNumber() != move) {
            for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
                sudokuField.undoFieldElement(i);
            }
            sudokuField.setMoveNumber(sudokuField.getMoveNumber() - 1);
        }
        Validation.validate();
    }

    abstract static class pickRandomValue<T extends Number> {
        private static final Random rnd = new Random();
        T get(List<T> list) {
            if (list.size() == 1) return list.get(0);
            if (list.isEmpty()) return getDefault();
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
