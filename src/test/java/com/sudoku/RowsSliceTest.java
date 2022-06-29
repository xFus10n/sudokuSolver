package com.sudoku;

import com.sudoku.menu.Action;
import com.sudoku.menu.FieldsFromArguments;
import com.sudoku.properties.Arguments;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RowsSliceTest {
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
    void testRowsCandidatesCalculation2() {
        //assign
        String[] inArgs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Arguments.initializeArgumentContainer(inArgs);
        Arguments.getInstance();

        //act
        Action setFields = new FieldsFromArguments();
        setFields.execute(sFields);

        List<Integer> actCandidates8 = sFields.getCandidates(8);
        List<Integer> actCandidates7 = sFields.getCandidates(7);

        //assert
        assertArrayEquals(List.of(9).toArray(), actCandidates8.toArray());
        assertArrayEquals(List.of(8).toArray(), actCandidates7.toArray());

        sFields.setField(7, 0);
        actCandidates8 = sFields.getCandidates(8);
        actCandidates7 = sFields.getCandidates(7);

        //assert
        assertArrayEquals(List.of(8, 9).toArray(), actCandidates8.toArray());
        assertArrayEquals(List.of(8, 9).toArray(), actCandidates7.toArray());
    }

    @Test
    void testRowsCandidatesCalculation() {
        //assign
        String[] inArgs = {"1", "2", "3", "4", "5", "6"};
        Arguments.initializeArgumentContainer(inArgs);
        Arguments.getInstance();

        //act
        Action setFields = new FieldsFromArguments();
        setFields.execute(sFields);

        List<Integer> actCandidates8 = sFields.getCandidates(8);
        List<Integer> actCandidates5 = sFields.getCandidates(5);

        //assert
        assertArrayEquals(Arrays.asList(7, 8, 9).toArray(), actCandidates8.toArray());
        assertArrayEquals(Arrays.asList(6).toArray(), actCandidates5.toArray());

        sFields.setField(5, 7);
        actCandidates8 = sFields.getCandidates(8);
        actCandidates5 = sFields.getCandidates(5);

        //assert
        assertArrayEquals(Arrays.asList(6, 8, 9).toArray(), actCandidates8.toArray());
        assertArrayEquals(Arrays.asList(7).toArray(), actCandidates5.toArray());

        sFields.setField(5, 0);
        actCandidates8 = sFields.getCandidates(8);
        actCandidates5 = sFields.getCandidates(5);

        //assert
        assertArrayEquals(Arrays.asList(6, 7, 8, 9).toArray(), actCandidates8.toArray());
        assertArrayEquals(Arrays.asList(6, 7, 8, 9).toArray(), actCandidates5.toArray());

        sFields.setField(5, 0);
        actCandidates8 = sFields.getCandidates(8);
        actCandidates5 = sFields.getCandidates(5);

        //assert
        assertArrayEquals(Arrays.asList(6, 7, 8, 9).toArray(), actCandidates8.toArray());
        assertArrayEquals(Arrays.asList(6, 7, 8, 9).toArray(), actCandidates5.toArray());

        sFields.resetFields();
        actCandidates8 = sFields.getCandidates(8);
        actCandidates5 = sFields.getCandidates(5);

        //assert
        assertArrayEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(), actCandidates8.toArray());
        assertArrayEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).toArray(), actCandidates5.toArray());
    }

    @Test
    void testSliceCandidatesCalculation() {
        //assign
        String[] inArgs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Arguments.initializeArgumentContainer(inArgs);
        Arguments.getInstance();

        //act
        Action setFields = new FieldsFromArguments();
        setFields.execute(sFields);

        List<Integer> actCandidates79 = sFields.getCandidates(79);
        List<Integer> actCandidates72 = sFields.getCandidates(72);

        //assert
        assertArrayEquals(List.of(1,2,3,4,5,6,7,9).toArray(), actCandidates79.toArray());
        assertArrayEquals(List.of(2,3,4,5,6,7,8,9).toArray(), actCandidates72.toArray());

        sFields.setField(9, 4);
        sFields.setField(7, 0);
        actCandidates79 = sFields.getCandidates(79);
        actCandidates72 = sFields.getCandidates(72);

        //assert
        assertArrayEquals(List.of(1,2,3,4,5,6,7,8,9).toArray(), actCandidates79.toArray());
        assertArrayEquals(List.of(2,3,5,6,7,8,9).toArray(), actCandidates72.toArray());

        sFields.setField(18, 5);
        sFields.setField(27, 6);
        sFields.setField(36, 7);
        sFields.setField(45, 8);
        actCandidates72 = sFields.getCandidates(72);

        //assert
        assertArrayEquals(List.of(2,3,9).toArray(), actCandidates72.toArray());

        sFields.setField(54, 2);
        sFields.setField(63, 3);
        actCandidates72 = sFields.getCandidates(72);

        //assert
        assertArrayEquals(List.of(9).toArray(), actCandidates72.toArray());

        sFields.setField(63, 0);
        sFields.setField(72, 1);
        actCandidates72 = sFields.getCandidates(72);

        //assert
        assertArrayEquals(List.of(1).toArray(), actCandidates72.toArray());

        sFields.setField(72, 0);
        actCandidates72 = sFields.getCandidates(72);

        //assert
        assertArrayEquals(List.of(3,9).toArray(), actCandidates72.toArray());
    }
}
