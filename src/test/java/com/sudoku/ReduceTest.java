package com.sudoku;

import com.sudoku.menu.Action;
import com.sudoku.menu.FieldsFromArguments;
import com.sudoku.menu.Show;
import com.sudoku.properties.Arguments;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReduceTest {
    private static Field sFields;

    @BeforeAll
    static void beforeAll() {
        sFields = Field.getInstance();
    }

    @BeforeEach
    void setUp() {
        sFields.resetFields();
    }

    @Test
    void testCandidatesCalculation() {
        //assign
        String[] inArgs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Arguments.initializeArgumentContainer(inArgs);
        Arguments.getInstance();

        //act
        Action setFields = new FieldsFromArguments();
        setFields.execute(sFields);
        var actCandidates9 = sFields.getPositionCandidates(9);
        var actCandidates8 = sFields.getPositionCandidates(8);

        //assert
        assertArrayEquals(List.of(4, 5, 6, 7, 8, 9).toArray(), actCandidates9.toArray());
        assertArrayEquals(List.of(9).toArray(), actCandidates8.toArray());

        sFields.setField(80, 1);
        sFields.setField(72, 4);

        actCandidates9 = sFields.getPositionCandidates(9);
        actCandidates8 = sFields.getPositionCandidates(8);
        var actCandidate76 = sFields.getPositionCandidates(76);
        assertArrayEquals(List.of(2, 3, 6, 7, 8, 9).toArray(), actCandidate76.toArray());
        assertArrayEquals(List.of(5, 6, 7, 8, 9).toArray(), actCandidates9.toArray());
        assertArrayEquals(List.of(9).toArray(), actCandidates8.toArray());
    }
}
