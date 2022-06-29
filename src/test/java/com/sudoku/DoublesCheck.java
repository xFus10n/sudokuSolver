package com.sudoku;

import com.sudoku.menu.Action;
import com.sudoku.menu.FieldsFromArguments;
import com.sudoku.menu.Show;
import com.sudoku.properties.Arguments;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DoublesCheck {
    private static Field sFields;
//    0 0 0 0 0 0 0 1 0 1 0 8 0 0 0 0 3 0 0 0 0 3 1 8 9 6 7 0 6 0 0 0 0 3 0 2 9 2 0 6 7 0 0 0 0 0 0 0 0 2 4 6 7 0 0 8 0 5 0 1 0 2 6 3 0 0 0 6 9 1 0 0 0 1 0 0 4 0 0 0 0

    @BeforeAll
    static void beforeAll() {
        sFields = Field.getInstance();
    }

    @BeforeEach
    void setUp() {
        sFields.resetFields();
    }

    @Test
    void testDoublesCalculation() {
        //assign
        String[] inArgs = {"3", "0", "0", "0", "0", "0", "0", "0", "8", "4", "0", "0", "0", "0", "0", "0", "0", "7", "5", "0", "0", "0", "0", "0", "0", "0", "6",
                           "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
                           "6", "0", "0", "0", "0", "0", "0", "0", "5", "7", "0", "0", "0", "0", "0", "0", "0", "3", "8", "0", "0", "0", "0", "0", "0", "0", "4"};
        Arguments.initializeArgumentContainer(inArgs);
        Arguments.getInstance();

        //act
        Action setFields = new FieldsFromArguments();
        setFields.execute(sFields);

        List<Integer> actCandidates31 = sFields.getCandidates(31);
        List<Integer> actCandidates36 = sFields.getCandidates(36);
        List<Integer> actCandidates40 = sFields.getCandidates(40);
        List<Integer> actCandidates44 = sFields.getCandidates(44);
        List<Integer> actCandidates49 = sFields.getCandidates(49);

        //assert
        assertArrayEquals(List.of(1,2,9).toArray(), actCandidates36.toArray());
        assertArrayEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(), actCandidates31.toArray());
        assertArrayEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(), actCandidates40.toArray());
        assertArrayEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(), actCandidates49.toArray());
        assertArrayEquals(List.of(1,2,9).toArray(), actCandidates44.toArray());

        //act
        sFields.setField(51, 9);
        sFields.setField(29, 9);

        actCandidates31 = sFields.getCandidates(31);
        actCandidates36 = sFields.getCandidates(36);
        actCandidates40 = sFields.getCandidates(40);
        actCandidates44 = sFields.getCandidates(44);
        actCandidates49 = sFields.getCandidates(49);

        //assert
        assertArrayEquals(List.of(1,2).toArray(), actCandidates36.toArray());
        assertArrayEquals(List.of(3, 4, 5, 6, 7, 8).toArray(), actCandidates31.toArray());
        assertArrayEquals(List.of(3, 4, 5, 6, 7, 8, 9).toArray(), actCandidates40.toArray());
        assertArrayEquals(List.of(3, 4, 5, 6, 7, 8).toArray(), actCandidates49.toArray());
        assertArrayEquals(List.of(1,2).toArray(), actCandidates44.toArray());

        //test
        sFields.setField(51, 0);
        new Show().execute(sFields);

        actCandidates31 = sFields.getCandidates(31);
        actCandidates36 = sFields.getCandidates(36);
        actCandidates40 = sFields.getCandidates(40);
        actCandidates44 = sFields.getCandidates(44);
        actCandidates49 = sFields.getCandidates(49);

        System.out.println("actCandidates31 = " + actCandidates31);
        System.out.println("actCandidates36 = " + actCandidates36);
        System.out.println("actCandidates40 = " + actCandidates40);
        System.out.println("actCandidates44 = " + actCandidates44);
        System.out.println("actCandidates49 = " + actCandidates49);
    }
}
