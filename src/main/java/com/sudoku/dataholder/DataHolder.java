package com.sudoku.dataholder;

import com.sudoku.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void setPositionCandidates(int pos, List<Integer> candidates){
        positionCandidates.replace(pos, candidates);
    }
}
