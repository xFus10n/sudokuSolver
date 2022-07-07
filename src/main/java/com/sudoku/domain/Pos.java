package com.sudoku.domain;

public class Pos {
    public int row; public int col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
