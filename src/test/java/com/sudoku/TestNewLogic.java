package com.sudoku;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.domain.FieldElement;
import org.junit.jupiter.api.Test;

public class TestNewLogic {

    @Test
    void testHistory() {
        ElementWithHistory elementWithHistory = new ElementWithHistory(0);
        System.out.println("fieldElement0 = " + elementWithHistory.getFieldElementCurrentState());

        elementWithHistory.reduceFieldCandidates(9, 1);
        System.out.println("fieldElement1 = " + elementWithHistory.getFieldElementCurrentState());

        elementWithHistory.reduceFieldCandidates(3);
        System.out.println("fieldElement2 = " + elementWithHistory.getFieldElementCurrentState());

        elementWithHistory.setFieldValue(2);
        System.out.println("fieldElement3 = " + elementWithHistory.getFieldElementCurrentState());

        elementWithHistory.updateCounter();
        System.out.println("fieldElement4 = " + elementWithHistory.getFieldElementCurrentState());

        System.out.println("--------------------------------");

        FieldElement history4 = elementWithHistory.getHistory(4);
        System.out.println("history3 = " + history4);
        FieldElement history3 = elementWithHistory.getHistory(3);
        System.out.println("history3 = " + history3);
        FieldElement history2 = elementWithHistory.getHistory(2);
        System.out.println("history2 = " + history2);
        FieldElement history1 = elementWithHistory.getHistory(1);
        System.out.println("history1 = " + history1);
        FieldElement history0 = elementWithHistory.getHistory(0);
        System.out.println("history0 = " + history0);
    }
}
