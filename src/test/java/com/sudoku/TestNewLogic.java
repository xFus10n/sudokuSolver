package com.sudoku;

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
}
