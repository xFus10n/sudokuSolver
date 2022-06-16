package com.sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
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
        var fields = Field.getInstance();
        assertTrue(fields.setField(0, 1));

        var sudokuFields = fields.getSudokuFields();
        int[][] expectedFields = { {1,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0}};
        assertArrayEquals(expectedFields, sudokuFields);

        var initialFields = new int[9][9];
        sFields.resetFields();
        assertArrayEquals(initialFields, Field.getInstance().getSudokuFields());
    }
}