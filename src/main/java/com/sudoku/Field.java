package com.sudoku;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.domain.Pos;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.menu.ShowStatus;
import com.sudoku.properties.Status;
import com.sudoku.reducers.CandidatesHandler;
import com.sudoku.reducers.OwnerAPI;
import com.sudoku.utils.FieldUtilz;
import com.sudoku.utils.Validation;

import java.util.*;

import static com.sudoku.utils.FieldUtilz.getCubeIDbyPosition;

public final class Field {
    private static Field fieldInstance;

    public static final  int FIELD_CAPACITY = 81;
    public static final  int DIM_SIZE = 9;
    private static final ConsoleLogger logger                   = ConsoleLogger.getInstance();
    private static final CandidatesHandler candidateReducer = new CandidatesHandler();
    private static final ShowStatus        valid            = new ShowStatus();

    private ElementWithHistory[][] sudokuFields;
    private final Scanner scanner;
    private Status status = Status.DEFAULT;
    private int counter;


    public boolean isDefined(int position){
        return getFieldValue(position) != 0;
    }

    public Status getStatus() {
        return status;
    }

    public void setFailedStatus() {
        status = Status.FAILED;
    }

    public void setValidatedStatus() {
        status = Status.VALIDATED;
    }

    public void setSolvedStatus() {
        status = Status.SOLVED;
    }

    /**
     * hidden constructor
     */
    private Field() {
        initSudokuFields();
        counter = 0;
        scanner = new Scanner(System.in);
        attachShutDownHook();
    }

    private void initSudokuFields() {
        sudokuFields = new ElementWithHistory[DIM_SIZE][DIM_SIZE];
        int counter = 0;
        for (int i = 0; i < DIM_SIZE; i++) {
            for (int j = 0; j < DIM_SIZE; j++) {
                sudokuFields[i][j] = new ElementWithHistory(counter++);
            }
        }
    }

    /**
     * singleton instance
     *
     * @return Field instance
     */
    public static synchronized Field getInstance() {
        if (fieldInstance == null) {
            fieldInstance = new Field();
        }
        return fieldInstance;
    }

    /**
     * For test, assertions on sudoku fields
     *
     * @return two dim. integer array
     */
    public int[][] getSudokuFields() {
        int[][] output = new int[DIM_SIZE][DIM_SIZE];
        for (int i = 0; i < DIM_SIZE; i++) {
            for (int j = 0; j < DIM_SIZE; j++) {
                output[i][j] = sudokuFields[i][j].getElementValue();
            }
        }
        return output;
    }

    /**
     * Set sudoku field using rows and columns
     * Zero is hidden value
     *
     * @param row   row number with zero base
     * @param col   column number with base zero
     * @param newValue value from 0 to 9 where 0 is hidden value
     * @return true if set was successful
     */
    private boolean setField(int row, int col, int newValue, int currentPosition) {
        if (status == Status.FAILED) return false;
        if (row < 0 || row >= DIM_SIZE) {
            return false;
        }
        if (col < 0 || col >= DIM_SIZE) {
            return false;
        }
        if (newValue < 1 || newValue > DIM_SIZE) {
            return false;
        }
        try {
            counter++;
            OwnerAPI ownerAPI = new OwnerAPI(currentPosition, newValue, row, col, getCubeIDbyPosition(currentPosition));
            sudokuFields[row][col].setFieldValue(counter, newValue);
            applyReducers(ownerAPI, counter);
            Validation.validate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void applyReducers(OwnerAPI ownerAPI, int counter) {
        for (int i = 0; i < FIELD_CAPACITY; i++) {
            if (i != ownerAPI.position) { // don't update just set value
                ElementWithHistory fieldElement = getFieldElement(i);
                candidateReducer.reduce(fieldElement, ownerAPI);
                setFieldElementMoveNumber(i, counter);
            }
        }
    }

    public List<Integer> getPositionCandidates(int position) {
        Pos pos = FieldUtilz.getCoordinates(position);
        return sudokuFields[pos.row][pos.col].getPositionCandidates();
    }

    /**
     * Set sudoku field using position from 0 to 80
     * <p>
     * [ 0,  1,  2  ] [ 3,  4,  5  ]  [ 6,  7,  8  ]
     * [ 9,  10, 11 ] [ 12, 13, 14 ]  [ 15, 16, 17 ]
     * [ 18, 19, 20 ] [ 21, 22, 23 ]  [ 24, 25, 26 ]
     * <p>
     * [ 27, 28, 29 ] [ 30, 31, 32 ]  [ 33, 34, 35 ]
     * [ 36, 37, 38 ] [ 39, 40, 41 ]  [ 42, 43, 44 ]
     * [ 45, 46, 47 ] [ 48, 49, 50 ]  [ 51, 52, 53 ]
     * <p>
     * [ 54, 55, 56 ] [ 57, 58, 59 ]  [ 60, 61, 62 ]
     * [ 63, 64, 65 ] [ 66, 67, 68 ]  [ 69, 70, 71 ]
     * [ 72, 73, 74 ] [ 75, 76, 77 ]  [ 78, 79, 80 ]
     *
     * @param position absolute position in sudoku field
     * @param value    value from 0 to 9 where 0 is hidden value
     * @return true if set was successful
     */
    public boolean setField(int position, int value) {
        Pos pos = FieldUtilz.getCoordinates(position);
        return setField(pos.row, pos.col, value, position);
    }

    public int getFieldValue(int position) {
        Pos pos = FieldUtilz.getCoordinates(position);
        return sudokuFields[pos.row][pos.col].getElementValue();
    }

    public ElementWithHistory getFieldElement(int position) {
        Pos pos = FieldUtilz.getCoordinates(position);
        return sudokuFields[pos.row][pos.col];
    }

    public void setFieldElementMoveNumber(int position, int counter) {
        Pos pos = FieldUtilz.getCoordinates(position);
        sudokuFields[pos.row][pos.col].updateCounter(counter);
    }

    public int getMoveNumber() {
        return counter;
    }

    public void setMoveNumber(int counter) {
        this.counter = counter;
    }

    /**
     * Set sudoku field from command line
     * use 0 as hidden value, if values < 80 all remaining are 0
     */
    public void setFields(List<Integer> values) {
        resetFields();
        for (int i = 0; i < FIELD_CAPACITY; i++) {
            setField(i, values.get(i));
        }
    }

    public void undoFieldElement(int position) {
        Pos pos = FieldUtilz.getCoordinates(position);
        sudokuFields[pos.row][pos.col].undo();
    }

    public void undoFieldElement(int position, int move) {
        Pos pos = FieldUtilz.getCoordinates(position);
        while (sudokuFields[pos.row][pos.col].getMoveNumber() != move) {
            sudokuFields[pos.row][pos.col].undo();
        }
    }

    /**
     * initialize fields
     */
    public void resetFields() {
        sudokuFields = new ElementWithHistory[DIM_SIZE][DIM_SIZE];
        initSudokuFields();
        counter = 0;
        status = Status.DEFAULT;
    }

    public Scanner getScanner() {
        return scanner;
    }

    private void attachShutDownHook() {
        Runtime.getRuntime()
               .addShutdownHook(new Thread(() -> {
                   scanner.close();
                   logger.toConsole("application shutdown");
               }));
    }
}
