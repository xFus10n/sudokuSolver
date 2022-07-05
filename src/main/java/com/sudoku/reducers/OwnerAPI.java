package com.sudoku.reducers;

public class OwnerAPI {
    public int position;
    public int owner;
    public int rowNumber;
    public int colNumber;
    public int cubeNumber;

    public OwnerAPI() {
    }

    public OwnerAPI(int position, int owner, int rowNumber, int colNumber, int cubeNumber) {
        this.position = position;
        this.owner = owner;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.cubeNumber = cubeNumber;
    }
}
