package com.sudoku.dataholder;

import com.sudoku.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataHolder {
    private final Map<Integer, List<Integer>> positionCandidates;

    public DataHolder() {
        positionCandidates = initPositions();
    }

    private Map<Integer, List<Integer>> initPositions() {
        Map<Integer, List<Integer>> candidates = new HashMap<>();
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            candidates.put(i, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return candidates;
    }

    public void reset() {
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            positionCandidates.replace(i, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
    }

    public List<Integer> getPositionCandidates(int pos) {
        return positionCandidates.getOrDefault(pos, List.of());
    }

    public void setPositionOwner(int pos, int owner, int row, int col, int cube){
        Field sField = Field.getInstance();
        int previousOwner = sField.getField(pos);

        if (owner == 0) {
            positionCandidates.replace(pos, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        } else {
            positionCandidates.replace(pos, List.of(owner));
        }

        reduceRowsCandidates(pos, owner, row, sField, previousOwner);
        //reduce slice candidates
        //reduce cube candidates
    }

    private void reduceRowsCandidates(int pos, int owner, int row, Field sField, int previousOwner) {
        //reduce row candidates
        if (previousOwner == owner) return;
        List<Integer> rowPositions = sField.getRowPositions(row);
        if (owner == 0) {
            appendCandidate(pos, previousOwner, rowPositions);
        } else {
            for (Integer position : rowPositions) {
                if (position != pos) {
                    List<Integer> values = positionCandidates.getOrDefault(position, List.of());
                    List<Integer> reducedValues = values.stream().filter(x -> x != owner).collect(Collectors.toList());
                    positionCandidates.replace(position, reducedValues);
                }
            }
            if (previousOwner != 0) {
                appendCandidate(pos, previousOwner, rowPositions);
            }
        }
    }

    private void appendCandidate(int pos, int previousOwner, List<Integer> rowPositions) {
        for (Integer position : rowPositions) {
            if (position != pos) {
                List<Integer> values = positionCandidates.getOrDefault(position, List.of());
                values.add(previousOwner);
                Collections.sort(values);
                positionCandidates.replace(position, values);
            }
        }
    }
}
