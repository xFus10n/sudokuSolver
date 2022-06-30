package com.sudoku.domain;

import java.util.HashMap;
import java.util.Map;

public class ElementWithHistory {
    private Map<Integer,FieldElement> history;
    private FieldElement fieldElement;

    public ElementWithHistory(int position) {
        history = new HashMap<>();
        fieldElement = new FieldElement(position);
        history.put(fieldElement.getMoveNumber(), fieldElement.clone());
    }

//    public void setFieldElement(FieldElement fieldElement) {
//        history.put(this.fieldElement.getMoveNumber(), this.fieldElement.clone());
//        this.fieldElement = fieldElement;
//    }

    public void setFieldValue(int value) {
        fieldElement.setValue(value);
        history.put(fieldElement.getMoveNumber(), fieldElement.clone());
    }

    public void reduceFieldCandidates(Integer... values){
        fieldElement.removeCandidate(values);
        history.put(fieldElement.getMoveNumber(), fieldElement.clone());
    }

    public FieldElement getHistory(int moveNumber) {
        return history.get(moveNumber);
    }

    public FieldElement getFieldElementCurrentState() {
        return fieldElement;
    }
}
