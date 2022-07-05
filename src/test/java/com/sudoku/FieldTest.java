package com.sudoku;

import com.sudoku.menu.*;
import com.sudoku.properties.Arguments;
import com.sudoku.properties.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private static final String inArgsSolvedSudoku = "1 2 3 4 5 6 7 8 9 4 5 6 7 8 9 1 2 3 7 8 9 1 2 3 4 5 6 2 3 4 5 6 7 8 9 1 5 6 7 8 9 1 2 3 4 8 9 1 2 3 4 5 6 7 3 4 5 6 7 8 9 1 2 6 7 8 9 1 2 3 4 5 9 1 2 3 4 5 6 7 8";
    private static final Field  sFields            = Field.getInstance();

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
        new Undo().execute(sFields);
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
        assertFalse(fields.setField(1, 0));
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
}