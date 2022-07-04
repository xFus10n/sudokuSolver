package com.sudoku.domain;
import java.util.*;
import java.util.stream.Collectors;

public class FieldElement implements Cloneable {
    final int position;
    private int value;
    private List<Integer> candidates;
    private int moveCount;
    private ActionType actionType;

    /* initial values for sudoku field */
    public FieldElement(int position) {
        this.position = position;
        value = 0; moveCount = 0;
        candidates = List.of(1,2,3,4,5,6,7,8,9);
        actionType = ActionType.DEFAULT;
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

    public void setValue(int counter, int value) {
        actionType = ActionType.SET;
        this.value = value;
        candidates = Arrays.asList(value);
        moveCount = counter;
    }

    public void setMoveNumber(int counter) {
        actionType = ActionType.REDUCE;
        moveCount = counter;
    }

    public void removeCandidate(int value) {
        actionType = ActionType.REDUCE;
        candidates = candidates.stream().filter(x -> x != value).collect(Collectors.toList());
    }

    public void removeCandidate(Integer... values) {
        List<Integer> filter = Arrays.asList(values);
        actionType = ActionType.REDUCE;
        candidates = candidates.stream().filter(x -> !filter.contains(x)).collect(Collectors.toList());
    }

    public int getMoveNumber(){
        return moveCount;
    }

    @Override
    public String toString() {
        return "FieldElement{" +
                "position=" + position +
                ", value=" + value +
                ", action=" + actionType +
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
