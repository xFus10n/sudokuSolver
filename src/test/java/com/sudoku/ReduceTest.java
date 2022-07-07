package com.sudoku;

import com.sudoku.menu.Action;
import com.sudoku.menu.FieldsFromArguments;
import com.sudoku.menu.Show;
import com.sudoku.menu.ShowStatus;
import com.sudoku.properties.Arguments;
import com.sudoku.utils.Utilz;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReduceTest {
    private static Field sFields;
    private static String args = "0 0 0 0 0 0 0 1 0 1 0 8 0 0 0 0 3 0 0 0 0 3 1 8 9 6 7 0 6 0 0 0 0 3 0 2 9 2 0 6 7 0 0 0 0 0 0 0 0 2 4 6 7 0 0 8 0 5 0 1 0 2 6 3 0 0 0 6 9 1 0 0 0 1 0 0 4 0 0 0 0";

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

//    @Test
//    void testUndoToSpecificMove() {
//        //assign
//        Arguments.initializeArgumentContainer(args.split(" "));
//        Arguments.getInstance();
//
//        //act
//        new FieldsFromArguments().execute(sFields);
//        new Show().execute(sFields);
//        new ShowStatus().execute(sFields);
//        int moveNumber = sFields.getMoveNumber();
//        System.out.println("Move number : " + moveNumber);
//        System.out.println("1----------------------------");
//
//        sFields.setField(3, 2); //33
//        sFields.setField(5, 7); //34
//        sFields.setField(12, 4); //35
//        new Show().execute(sFields);
//        new ShowStatus().execute(sFields);
//        int moveNumberX = sFields.getMoveNumber();
//        System.out.println("Move number : " + moveNumberX);
//        System.out.println("2----------------------------");
//
//        Utilz.undo(sFields, moveNumber);
//
//        new Show().execute(sFields);
//        new ShowStatus().execute(sFields);
//        moveNumberX = sFields.getMoveNumber();
//        System.out.println("Move number : " + moveNumberX);
//        System.out.println("3----------------------------");
//
//
//        sFields.setField(3, 2); //33
//        sFields.setField(5, 7); //34
//        sFields.setField(12, 4); //35
//        new Show().execute(sFields);
//        new ShowStatus().execute(sFields);
//        moveNumberX = sFields.getMoveNumber();
//        System.out.println("Move number : " + moveNumberX);
//        System.out.println("4----------------------------");
//
//        Utilz.undo(sFields, moveNumber);
//
//        new Show().execute(sFields);
//        new ShowStatus().execute(sFields);
//        moveNumberX = sFields.getMoveNumber();
//        System.out.println("Move number : " + moveNumberX);
//        System.out.println("5----------------------------");
//    }
}
