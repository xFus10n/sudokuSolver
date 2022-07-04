package com.sudoku.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementWithHistory {
    private final Map<Integer,FieldElement> history;
    private final FieldElement              fieldElement;

    public ElementWithHistory(int position) {
        history = new HashMap<>();
        fieldElement = new FieldElement(position);
        history.put(fieldElement.getMoveNumber(), fieldElement.clone());
    }

    public void setFieldValue(int counter, int value) {
        fieldElement.setValue(counter, value);
        history.put(fieldElement.getMoveNumber(), fieldElement.clone());
    }

    public void reduceFieldCandidates(Integer... values){
        fieldElement.removeCandidate(values);
    }

    public void updateCounter(int counter){
        fieldElement.setMoveNumber(counter);
        history.put(fieldElement.getMoveNumber(), fieldElement.clone());
    }

    public FieldElement getHistory(int moveNumber) {
        return history.get(moveNumber);
    }

    public int getMoveNumber() {
        return fieldElement.getMoveNumber();
    }

    public int getElementPosition() {
        return fieldElement.getPosition();
    }

    public int getElementValue() {return fieldElement.getValue();}

    public List<Integer> getPositionCandidates(){
        return fieldElement.getCandidates();
    }

    @Override
    public String toString(){
        return fieldElement.toString();
    }
}
