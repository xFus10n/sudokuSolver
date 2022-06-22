package com.sudoku.dataholder;

import com.sudoku.Field;

import java.util.*;
import java.util.stream.Collectors;

public class DataHolder {
    private final Map<Integer, List<Integer>> positionCandidates;

    public DataHolder() {
        positionCandidates = initPositions();
    }

    private Map<Integer, List<Integer>> initPositions() {
        Map<Integer, List<Integer>> candidates = new HashMap<>();
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            candidates.put(i, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return candidates;
    }

    public void reset() {
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            positionCandidates.replace(i, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
    }

    public List<Integer> getPositionCandidates(int pos) {
        return positionCandidates.getOrDefault(pos, Arrays.asList());
    }

    public void setPositionOwner(int pos, int owner, int row, int col, int cube){
        Field sField = Field.getInstance();
        int previousOwner = sField.getField(pos);
        if (previousOwner == owner) return;

        if (owner == 0) {
            positionCandidates.replace(pos, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        } else {
            positionCandidates.replace(pos, Arrays.asList(owner));
        }

        reduceRowsCandidates(pos, owner, row, sField, previousOwner);
        //reduce slice candidates
        //reduce cube candidates
    }

    private void reduceRowsCandidates(int pos, int owner, int row, Field sField, int previousOwner) {
        //reduce row candidates
        List<Integer> rowPositions = sField.getRowPositions(row);
        if (owner == 0) {
            List<Integer>filter = appendCandidate(pos, previousOwner, rowPositions);
            removeCandidates(filter, pos);
        } else {
            for (Integer position : rowPositions) {
                if (position != pos) {
                    List<Integer> values = positionCandidates.getOrDefault(position, Arrays.asList());
                    List<Integer> reducedValues = values.stream().filter(x -> x != owner).collect(Collectors.toList());
                    positionCandidates.replace(position, reducedValues);
                }
            }
            if (previousOwner != 0) {
                appendCandidate(pos, previousOwner, rowPositions);
            }
        }
    }

    private List<Integer> appendCandidate(int pos, int previousOwner, List<Integer> rowPositions) {
        List<Integer> valuesToFilterOut = new ArrayList<>(); //on reset position
        for (Integer position : rowPositions) {
            if (position != pos) {
                List<Integer> values = positionCandidates.getOrDefault(position, Arrays.asList());
                if (values.size() == 1) {
                    valuesToFilterOut.add(values.get(0));
                } else {
                    /* only for uncertain positions */
                    values.add(previousOwner);
                    Collections.sort(values);
                    positionCandidates.replace(position, values);
                }
            }
        }
        return valuesToFilterOut;
    }

    private void removeCandidates(List<Integer> filter, int pos) {
        List<Integer> values = makeMutable(positionCandidates.get(pos));
        values.removeAll(filter);
        positionCandidates.replace(pos, values);
    }

    private <T> ArrayList<T> makeMutable(List<T> inList){
        return new ArrayList<>(inList);
    }
}
