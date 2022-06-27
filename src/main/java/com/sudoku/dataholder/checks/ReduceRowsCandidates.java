package com.sudoku.dataholder.checks;

import com.sudoku.Field;
import com.sudoku.dataholder.OwnerAPI;

import java.util.*;
import java.util.stream.Collectors;

import static com.sudoku.dataholder.Utilz.makeMutable;

public class ReduceRowsCandidates implements CandidatesCheck{
    private final Map<Integer, List<Integer>> positionCandidates;

    public ReduceRowsCandidates(Map<Integer, List<Integer>> positionCandidates) {
        this.positionCandidates = positionCandidates;
    }

    @Override
    public void checkCandidates(OwnerAPI ownerAPI) {
        List<Integer> rowPositions = Field.getInstance().getRowPositions(ownerAPI.getRowNumber()); //todo: can be extracted
        if (ownerAPI.getNewOwner() == 0) {
            List<Integer>filter = appendCandidate(ownerAPI.getCurrentPosition(), ownerAPI.getPreviousOwner(), rowPositions); //previous owner becomes a candidate for all positions in a row
            removeCertainCandidates(filter, ownerAPI.getCurrentPosition());
        } else {
            //remove candidates of new owner for all positions in a row
            for (Integer position : rowPositions) {
                if (position != ownerAPI.getCurrentPosition()) {
                    List<Integer> values = positionCandidates.getOrDefault(position, Arrays.asList());
                    List<Integer> reducedValues = values.stream().filter(x -> x != ownerAPI.getNewOwner()).collect(Collectors.toList());
                    positionCandidates.replace(position, reducedValues);
                }
            }
            if (ownerAPI.getPreviousOwner() != 0) {
                appendCandidate(ownerAPI.getCurrentPosition(), ownerAPI.getPreviousOwner(), rowPositions);
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
}
