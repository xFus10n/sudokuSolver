package com.sudoku.reducers;

public class OwnerAPI {
    int currentPosition;
    int newOwner;
    int previousOwner;
    int rowNumber;
    int colNumber;
    int cubeNumber;

    public OwnerAPI(int currentPosition, int newOwner, int previousOwner, int rowNumber, int colNumber, int cubeNumber) {
        this.currentPosition = currentPosition;
        this.newOwner = newOwner;
        this.previousOwner = previousOwner;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.cubeNumber = cubeNumber;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(int newOwner) {
        this.newOwner = newOwner;
    }

    public int getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(int previousOwner) {
        this.previousOwner = previousOwner;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public void setColNumber(int colNumber) {
        this.colNumber = colNumber;
    }

    public int getCubeNumber() {
        return cubeNumber;
    }

    public void setCubeNumber(int cubeNumber) {
        this.cubeNumber = cubeNumber;
    }
}
