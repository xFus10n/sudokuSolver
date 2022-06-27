package com.sudoku.dataholder.checks;

import com.sudoku.Field;
import com.sudoku.dataholder.OwnerAPI;

import java.util.*;
import java.util.stream.Collectors;

import static com.sudoku.dataholder.Utilz.makeMutable;

public class ReduceRowsCandidates implements CandidatesCheck{
    private Map<Integer, List<Integer>> positionCandidates;

    private void initPositionCandidates(Map<Integer, List<Integer>> candidatesMap) {
        positionCandidates = candidatesMap;
    }

    @Override
    public void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> candidatesMap) {
        initPositionCandidates(candidatesMap);
        List<Integer> rowPositions = Field.getInstance().getRowPositions(ownerAPI.getRowNumber()); //todo: can be extracted
        if (ownerAPI.getPreviousOwner() != 0) appendCandidate(ownerAPI.getCurrentPosition(), ownerAPI.getPreviousOwner(), rowPositions);
        if (ownerAPI.getNewOwner() == 0) {
            List<Integer>filter = getFilterValues(ownerAPI.getCurrentPosition(), ownerAPI.getPreviousOwner(), rowPositions); //previous owner becomes a candidate for all positions in a row
            removeCertainCandidates(filter, ownerAPI.getCurrentPosition());
        } else {
            //remove candidates of new owner for all positions in a row
            for (Integer position : rowPositions) {
                if (position != ownerAPI.getCurrentPosition()) {
                    List<Integer> values = candidatesMap.getOrDefault(position, Arrays.asList());
                    List<Integer> reducedValues = values.stream().filter(x -> x != ownerAPI.getNewOwner()).collect(Collectors.toList());
                    candidatesMap.replace(position, reducedValues);
                }
            }
        }
    }

    private void appendCandidate(int previousOwnerPosition, int previousOwner, List<Integer> rowPositions) {
        Field sField = Field.getInstance();
        for (Integer position : rowPositions) {
            if (position != previousOwnerPosition) {
                if (!sField.isDefined(position)) {
                    List<Integer> values = positionCandidates.getOrDefault(position, Arrays.asList());
                    values.add(previousOwner);
                    Collections.sort(values);
                    positionCandidates.replace(position, values);
                }
            }
        }
    }

    private List<Integer> getFilterValues(int previousOwnerPosition, int previousOwner, List<Integer> rowPositions) {
        Field sField = Field.getInstance();
        List<Integer> valuesToFilterOut = new ArrayList<>();
        for (Integer position : rowPositions) {
            if ((position != previousOwnerPosition) && (sField.isDefined(position))) {
                List<Integer> values = positionCandidates.getOrDefault(position, Arrays.asList());
                valuesToFilterOut.add(values.get(0));
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
}
