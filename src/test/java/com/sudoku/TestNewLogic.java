package com.sudoku;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.domain.FieldElement;
import org.junit.jupiter.api.Test;

public class TestNewLogic {

    @Test
    void testHistory() {
        ElementWithHistory elementWithHistory = new ElementWithHistory(0);
        System.out.println("fieldElement0 = " + elementWithHistory);

        elementWithHistory.reduceFieldCandidates(9, 1);
        System.out.println("fieldElement1 = " + elementWithHistory);

        elementWithHistory.reduceFieldCandidates(3);
        System.out.println("fieldElement2 = " + elementWithHistory);

        elementWithHistory.updateCounter(1);
        System.out.println("fieldElement3 = " + elementWithHistory);

        elementWithHistory.setFieldValue(2, 2);
        System.out.println("fieldElement4 = " + elementWithHistory);

        System.out.println("--------------------------------");

        FieldElement history2 = elementWithHistory.getHistory(2);
        System.out.println("history2 = " + history2);
        FieldElement history1 = elementWithHistory.getHistory(1);
        System.out.println("history1 = " + history1);
        FieldElement history0 = elementWithHistory.getHistory(0);
        System.out.println("history0 = " + history0);
    }
}
