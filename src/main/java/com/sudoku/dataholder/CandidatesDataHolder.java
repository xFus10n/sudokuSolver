package com.sudoku.dataholder;

import com.sudoku.Field;

import java.util.*;
import java.util.stream.Collectors;

public class CandidatesDataHolder {
    private final Map<Integer, List<Integer>> positionCandidates;

    public CandidatesDataHolder() {
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

    public void setPositionOwner(OwnerAPI ownerAPI){
        if (ownerAPI.previousOwner == ownerAPI.newOwner) return;
        if (ownerAPI.newOwner == 0) {
            positionCandidates.replace(ownerAPI.currentPosition, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); //put initial list, later filter
        } else {
            positionCandidates.replace(ownerAPI.currentPosition, Arrays.asList(ownerAPI.newOwner));
        }

        reduceRowsCandidates(ownerAPI);
        //reduce slice candidates
        //reduce cube candidates
    }

    private void reduceRowsCandidates(OwnerAPI ownerAPI) {
        List<Integer> rowPositions = Field.getInstance().getRowPositions(ownerAPI.rowNumber); //todo: can be extracted
        if (ownerAPI.newOwner == 0) {
            List<Integer>filter = appendCandidate(ownerAPI.currentPosition, ownerAPI.previousOwner, rowPositions); //previous owner becomes a candidate for all positions in a row
            removeCertainCandidates(filter, ownerAPI.currentPosition);
        } else {
            //remove candidates of new owner for all positions in a row
            for (Integer position : rowPositions) {
                if (position != ownerAPI.currentPosition) {
                    List<Integer> values = positionCandidates.getOrDefault(position, Arrays.asList());
                    List<Integer> reducedValues = values.stream().filter(x -> x != ownerAPI.newOwner).collect(Collectors.toList());
                    positionCandidates.replace(position, reducedValues);
                }
            }
            if (ownerAPI.previousOwner != 0) {
                appendCandidate(ownerAPI.currentPosition, ownerAPI.previousOwner, rowPositions);
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

    /**
     * Values in a row that has only one candidate for particular position
     * should be removed as a candidates for newly reset position
     * @param filter List of values for removal
     * @param pos reset position
     */
    private void removeCertainCandidates(List<Integer> filter, int pos) {
        List<Integer> values = makeMutable(positionCandidates.get(pos));
        values.removeAll(filter);
        positionCandidates.replace(pos, values);
    }

    private <T> ArrayList<T> makeMutable(List<T> inList){
        return new ArrayList<>(inList);
    }
}
