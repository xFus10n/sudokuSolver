package com.sudoku;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.logger.ConsoleLogger;
import com.sudoku.properties.Status;
import com.sudoku.reducers.CandidatesHandler;

import java.util.*;

public final class Field {
    private static Field fieldInstance;

    public static final  int FIELD_CAPACITY = 81;
    public static final  int DIM_SIZE = 9;
    private static final int STRING_CAPACITY = 320;
    private static final int SOLVABLE_AMOUNT_ELEMENTS = 17;
    private static final ConsoleLogger logger = ConsoleLogger.getInstance();
    private static final CandidatesHandler candidateReducer = new CandidatesHandler();

    private ElementWithHistory[][] sudokuFields;
    private final Scanner scanner;
    private final Map<Integer, List<Integer>> cubeMap = initCubes();
    private final Map<Integer, List<Integer>> rowMap = initRows();
    private final Map<Integer, List<Integer>> colMap = initCols();
    private Status status = Status.DEFAULT;

    public boolean isDefined(int position){
        return getField(position) != 0;
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
                output[i][j] = sudokuFields[i][j].getFieldElementCurrentState().getValue();
            }
        }
        return output;
    }

    /**
     * Print out sudoku fields
     */
    public void showFields() {
        int[][] fields = getSudokuFields();
        for (int j = 0, sudokuFieldLength = sudokuFields.length; j < sudokuFieldLength; j++) {
            int[] arr = fields[j];
            StringBuilder output = new StringBuilder(STRING_CAPACITY);
            for (int i = 0, arrLength = arr.length; i < arrLength; i++) {
                if (i == 0) {
                    output.append("[ ")
                          .append(arr[0]);
                } else if (i == arrLength - 1) {
                    output.append(' ')
                          .append(arr[i])
                          .append(" ]");
                } else if ((i + 1) % 3 == 0) {
                    output.append(' ')
                          .append(arr[i])
                          .append(" ]  [");
                } else {
                    output.append(' ')
                          .append(arr[i]);
                }
            }
            logger.toConsole(output.toString()
                                   .replace("0", "*"));
            if ((j + 1) % 3 == 0) {
                logger.toConsole("");
            }
        }
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
        if (row < 0 || row >= DIM_SIZE) {
            return false;
        }
        if (col < 0 || col >= DIM_SIZE) {
            return false;
        }
        if (newValue < 0 || newValue > DIM_SIZE) {
            return false;
        }
        try {
            int lastMoveNumber = sudokuFields[row][col].getLastMoveNumber();
            sudokuFields[row][col].setFieldValue(newValue);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void startReducers(int lastMoveNumber, int newValue) {

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
        int row = position / DIM_SIZE;
        int col = position - (row * DIM_SIZE);
        return setField(row, col, value, position);
    }

    public int getField(int position) {
        int row = position / DIM_SIZE;
        int col = position - (row * DIM_SIZE);
        return sudokuFields[row][col].getFieldElementCurrentState().getValue();
    }

    public List<Integer> getCubePositions(int cubeOrder) {
        return cubeMap.getOrDefault(cubeOrder, List.of());
    }

    public List<Integer> getRowPositions(int rowOrder) {
        return rowMap.getOrDefault(rowOrder, List.of());
    }

    public List<Integer> getSlicePositions(int colOrder) {
        return colMap.getOrDefault(colOrder, List.of());
    }

    /**
     * Set sudoku field from command line
     * use 0 as hidden value, if values < 80 all remaining are 0
     */
    public void setFields(List<Integer> values) {
        for (int i = 0; i < FIELD_CAPACITY; i++) {
            setField(i, values.get(i));
        }
    }

    /**
     * initialize fields
     */
    public void resetFields() {
        sudokuFields = new ElementWithHistory[DIM_SIZE][DIM_SIZE];
        initSudokuFields();
    }

    public boolean solvable() {
        int[][] fields = getSudokuFields();
        int count = 0;
        for (int[] sudokuField : fields) {
            for (int i : sudokuField) {
                if (0 == i) {
                    count++;
                }
            }
            if (FIELD_CAPACITY - count < SOLVABLE_AMOUNT_ELEMENTS) {
                return false;
            }
        }
        return true;
    }

    /**
     * Position of 9 sub fields that should contain unique numbers
     *
     * @return map of cubes with positions
     */
    private static Map<Integer, List<Integer>> initCubes() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(0, 1, 2, 9, 10, 11, 18, 19, 20));
        map.put(1, List.of(3, 4, 5, 12, 13, 14, 21, 22, 23));
        map.put(2, List.of(6, 7, 8, 15, 16, 17, 24, 25, 26));
        map.put(3, List.of(27, 28, 29, 36, 37, 38, 45, 46, 47));
        map.put(4, List.of(30, 31, 32, 39, 40, 41, 48, 49, 50));
        map.put(5, List.of(33, 34, 35, 42, 43, 44, 51, 52, 53));
        map.put(6, List.of(54, 55, 56, 63, 64, 65, 72, 73, 74));
        map.put(7, List.of(57, 58, 59, 66, 67, 68, 75, 76, 77));
        map.put(8, List.of(60, 61, 62, 69, 70, 71, 78, 79, 80));
        return map;
    }

    private static Map<Integer, List<Integer>> initRows() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        map.put(1, List.of(9, 10, 11, 12, 13, 14, 15, 16, 17));
        map.put(2, List.of(18, 19, 20, 21, 22, 23, 24, 25, 26));
        map.put(3, List.of(27, 28, 29, 30, 31, 32, 33, 34, 35));
        map.put(4, List.of(36, 37, 38, 39, 40, 41, 42, 43, 44));
        map.put(5, List.of(45, 46, 47, 48, 49, 50, 51, 52, 53));
        map.put(6, List.of(54, 55, 56, 57, 58, 59, 60, 61, 62));
        map.put(7, List.of(63, 64, 65, 66, 67, 68, 69, 70, 71));
        map.put(8, List.of(72, 73, 74, 75, 76, 77, 78, 79, 80));
        return map;
    }

    private static Map<Integer, List<Integer>> initCols() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(0, 9, 18, 27, 36, 45, 54, 63, 72));
        map.put(1, List.of(1, 10, 19, 28, 37, 46, 55, 64, 73));
        map.put(2, List.of(2, 11, 20, 29, 38, 47, 56, 65, 74));
        map.put(3, List.of(3, 12, 21, 30, 39, 48, 57, 66, 75));
        map.put(4, List.of(4, 13, 22, 31, 40, 49, 58, 67, 76));
        map.put(5, List.of(5, 14, 23, 32, 41, 50, 59, 68, 77));
        map.put(6, List.of(6, 15, 24, 33, 42, 51, 60, 69, 78));
        map.put(7, List.of(7, 16, 25, 34, 43, 52, 61, 70, 79));
        map.put(8, List.of(8, 17, 26, 35, 44, 53, 62, 71, 80));
        return map;
    }

    private int getCubeIDbyPosition(int pos) {
        for (Map.Entry<Integer, List<Integer>> entry : cubeMap.entrySet()) {
            if (entry.getValue().contains(pos)) return entry.getKey();
        }
        return -1;
    }

    public static void printPositionHelp() {
        logger.toConsole("[ 0,  1,  2  ] [ 3,  4,  5  ]  [ 6,  7,  8  ]");
        logger.toConsole("[ 9,  10, 11 ] [ 12, 13, 14 ]  [ 15, 16, 17 ]");
        logger.toConsole("[ 18, 19, 20 ] [ 21, 22, 23 ]  [ 24, 25, 26 ]");
        logger.toConsole("");
        logger.toConsole("[ 27, 28, 29 ] [ 30, 31, 32 ]  [ 33, 34, 35 ]");
        logger.toConsole("[ 36, 37, 38 ] [ 39, 40, 41 ]  [ 42, 43, 44 ]");
        logger.toConsole("[ 45, 46, 47 ] [ 48, 49, 50 ]  [ 51, 52, 53 ]");
        logger.toConsole("");
        logger.toConsole("[ 54, 55, 56 ] [ 57, 58, 59 ]  [ 60, 61, 62 ]");
        logger.toConsole("[ 63, 64, 65 ] [ 66, 67, 68 ]  [ 69, 70, 71 ]");
        logger.toConsole("[ 72, 73, 74 ] [ 75, 76, 77 ]  [ 78, 79, 80 ]");
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
