package com.sudoku;

import com.sudoku.menu.*;
import com.sudoku.properties.Arguments;
import com.sudoku.properties.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private String inArgsSolvedSudoku = "1 2 3 4 5 6 7 8 9 4 5 6 7 8 9 1 2 3 7 8 9 1 2 3 4 5 6 2 3 4 5 6 7 8 9 1 5 6 7 8 9 1 2 3 4 8 9 1 2 3 4 5 6 7 3 4 5 6 7 8 9 1 2 6 7 8 9 1 2 3 4 5 9 1 2 3 4 5 6 7 8";
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
    void testSolvedStatus() {
        //assign
        Arguments.initializeArgumentContainer(inArgsSolvedSudoku.split(" "));
        Arguments.getInstance();

        //act
        new FieldsFromArguments().execute(sFields);
        new Validate().execute(sFields);

        //assert
        assertEquals(Status.SOLVED, sFields.getStatus());
    }

    @Test
    void testValidatedStatus() {
        //assign
        Arguments.initializeArgumentContainer(inArgsSolvedSudoku.split(" "));
        Arguments.getInstance();

        //act
        new FieldsFromArguments().execute(sFields);
        sFields.setField(80, 0);
        new Validate().execute(sFields);

        //assert
        assertEquals(Status.VALIDATED, sFields.getStatus());
    }

    @Test
    void testFailedStatus() {
        //assign
        Arguments.initializeArgumentContainer(inArgsSolvedSudoku.split(" "));
        Arguments.getInstance();

        //act
        new FieldsFromArguments().execute(sFields);
        sFields.setField(80, 9);
        new Validate().execute(sFields);

        //assert
        assertEquals(Status.FAILED, sFields.getStatus());
    }

    @Test
    void sudokuFieldsShouldBeAllZeros() {
        var fields = Field.getInstance();
        var sudokuFields = fields.getSudokuFields();
        var expectedFields = new int[9][9];
        assertArrayEquals(expectedFields, sudokuFields);
    }

    @Test
    void setFieldTest() {
        var fields = Field.getInstance();
        assertTrue(fields.setField(0, 1));
        assertTrue(fields.setField(80, 1));
        assertFalse(fields.setField(-1, 1));
        assertFalse(fields.setField(81, 1));
        assertTrue(fields.setField(1, 0));
        assertTrue(fields.setField(1, 9));
        assertFalse(fields.setField(40, 10));
    }

    @Test
    void testReset() {
        assertTrue(sFields.setField(0, 1));

        var sudokuFields = sFields.getSudokuFields();
        int[][] expectedFields = { {1,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}};
        assertArrayEquals(expectedFields, sudokuFields);

        var initialFields = new int[9][9];
        sFields.resetFields();
        assertArrayEquals(initialFields, Field.getInstance().getSudokuFields());
    }

    @Test
    void testSetFieldsFromArguments() {
        //assign
        String[] inArgs = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Arguments.initializeArgumentContainer(inArgs);
        Arguments.getInstance();
        int[][] expectedFields = { {0,0,0,0,0,0,0,0,0}, {1,2,3,4,5,6,7,8,9}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}};

        //act
        Action setFields = new FieldsFromArguments();
        setFields.execute(sFields);

        //assert
        assertArrayEquals(expectedFields, sFields.getSudokuFields());
    }

    @Test
    void testSetFieldsFromArguments2() {
        //assign
        String[] inArgs = {"1", "2", "3", "4", "5", "6", "7", "8", "9","0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                           "0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9","0", "0", "0", "0", "0", "0", "0", "0", "0",
                           "1", "2", "3", "4", "5", "6", "7", "8", "9","0", "0", "0", "0", "0", "0", "0", "0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Arguments.initializeArgumentContainer(inArgs);
        Arguments.getInstance();
        int[][] expectedFields = {{1,2,3,4,5,6,7,8,9}, {0,0,0,0,0,0,0,0,0},{1,2,3,4,5,6,7,8,9},
                                  {0,0,0,0,0,0,0,0,0}, {1,2,3,4,5,6,7,8,9},{0,0,0,0,0,0,0,0,0},
                                  {1,2,3,4,5,6,7,8,9}, {0,0,0,0,0,0,0,0,0},{1,2,3,4,5,6,7,8,9}};

        //act
        Action setFields = new FieldsFromArguments();
        setFields.execute(sFields);

        //assert
        assertArrayEquals(expectedFields, sFields.getSudokuFields());
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

        new Show().execute(sFields);
        System.out.println(actCandidates8);
        System.out.println(actCandidates7);

        //assert
        assertArrayEquals(List.of(8, 9).toArray(), actCandidates8.toArray());
        assertArrayEquals(List.of(8, 8).toArray(), actCandidates7.toArray());
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
}