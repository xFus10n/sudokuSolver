package com.sudoku;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.domain.FieldElement;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestNewLogic {

    @Test
    void testDomain() {
        Map<Integer, FieldElement> history = new HashMap<>();

        FieldElement fieldElement = new FieldElement(0);
        history.put(0, fieldElement.clone());
        System.out.println("fieldElement0 = " + fieldElement);

        fieldElement.removeCandidate(9);
        history.put(1, fieldElement.clone());
        System.out.println("fieldElement1 = " + fieldElement);

        fieldElement.removeCandidate(1);
        history.put(2, fieldElement.clone());
        System.out.println("fieldElement2 = " + fieldElement);

        fieldElement.setValue(2);
        history.put(3, fieldElement.clone());
        System.out.println("fieldElement3 = " + fieldElement);

        System.out.println("--------------------------------");

        FieldElement historyMove3 = history.get(3);
        System.out.println("historyMove3 = " + historyMove3);
        FieldElement historyMove2 = history.get(2);
        System.out.println("historyMove2 = " + historyMove2);
        FieldElement historyMove1 = history.get(1);
        System.out.println("historyMove1 = " + historyMove1);
        FieldElement historyMove0 = history.get(0);
        System.out.println("historyMov0 = " + historyMove0);
    }

    @Test
    void testHistory() {
        ElementWithHistory elementWithHistory = new ElementWithHistory(0);
        FieldElement fieldElement0 = elementWithHistory.getFieldElementCurrentState();
        System.out.println("fieldElement0 = " + fieldElement0);

        fieldElement0.removeCandidate(9);
        elementWithHistory.setFieldElement(fieldElement0);
        FieldElement fieldElement1 = elementWithHistory.getFieldElementCurrentState();
        System.out.println("fieldElement1 = " + fieldElement1);

        fieldElement1.removeCandidate(1);
        elementWithHistory.setFieldElement(fieldElement1);
        FieldElement fieldElement2 = elementWithHistory.getFieldElementCurrentState();
        System.out.println("fieldElement2 = " + fieldElement2);

        fieldElement2.setValue(2);
        elementWithHistory.setFieldElement(fieldElement2);
        FieldElement fieldElement3 = elementWithHistory.getFieldElementCurrentState();
        System.out.println("fieldElement3 = " + fieldElement3);

        System.out.println("--------------------------------");

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
