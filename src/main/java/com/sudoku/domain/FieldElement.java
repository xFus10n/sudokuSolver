package com.sudoku.domain;
import java.util.*;
import java.util.stream.Collectors;

public class FieldElement implements Cloneable{
    final int position;
    private int value;
    private List<Integer> candidates;
    private int moveCount;

    /* initial values for sudoku field*/
    public FieldElement(int position) {
        this.position = position;
        value = 0; moveCount = 0;
        candidates = List.of(1,2,3,4,5,6,7,8,9);
    }

    public List<Integer> getCandidates() {
        return candidates;
    }

    public int getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    public void setValue(int value) {
        this.value = value;
        candidates = Arrays.asList(value);
        moveCount++;
    }

    public void removeCandidate(int value) {
        candidates = candidates.stream().filter(x -> x != value).collect(Collectors.toList());
        moveCount++;
    }

    public int getMoveCount(){
        return moveCount;
    }

    @Override
    public String toString() {
        return "FieldElement{" +
                "position=" + position +
                ", value=" + value +
                ", candidates=" + candidates +
                ", moveCount=" + moveCount +
                '}';
    }

    @Override
    public FieldElement clone() {
        try {
            FieldElement clone = (FieldElement) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
