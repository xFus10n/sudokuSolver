package com.sudoku;

import com.sudoku.domain.ActionType;
import com.sudoku.domain.ElementWithHistory;
import com.sudoku.domain.FieldElement;
import com.sudoku.utils.Utilz;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSolvingUnits {

    @Test
    void testHistory() {
        ElementWithHistory elementWithHistory = new ElementWithHistory(0);
        elementWithHistory.reduceFieldCandidates(9, 1);
        elementWithHistory.reduceFieldCandidates(3);
        elementWithHistory.updateCounter(1);
        elementWithHistory.setFieldValue(2, 2);

        FieldElement history2 = elementWithHistory.getHistory(2);
        assertEquals(2, history2.getValue());
        assertEquals(List.of(2), history2.getCandidates());
        assertEquals(2, history2.getMoveNumber());
        assertEquals(ActionType.SET, history2.getActionType());

        FieldElement history1 = elementWithHistory.getHistory(1);
        assertEquals(0, history1.getValue());
        assertEquals(List.of(2, 4, 5, 6, 7, 8), history1.getCandidates());
        assertEquals(1, history1.getMoveNumber());
        assertEquals(ActionType.REDUCE, history1.getActionType());

        FieldElement history0 = elementWithHistory.getHistory(0);
        assertEquals(0, history0.getValue());
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), history0.getCandidates());
        assertEquals(0, history0.getMoveNumber());
        assertEquals(ActionType.DEFAULT, history0.getActionType());
    }

    @Test
    void testRandomizer() {
        assertEquals(0, Utilz.chooseRandomInteger(List.of()));
        assertEquals(1, Utilz.chooseRandomInteger(List.of(1)));
        int rndInt = Utilz.chooseRandomInteger(List.of(1, 2));
        if (rndInt != 1) {
            assertEquals(2, rndInt);
        } else assertEquals(1, rndInt);
    }

    @Test
    void testShuffling() {
        List<Integer> shuffledList = Utilz.getShuffledList();
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            assertTrue(shuffledList.contains(i));
        }
    }
}
