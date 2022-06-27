package com.sudoku.dataholder.checks;

import com.sudoku.Field;
import com.sudoku.dataholder.OwnerAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReduceSliceCandidates implements CandidatesCheck{
    private Map<Integer, List<Integer>> positionCandidates;

    private void initPositionCandidates(Map<Integer, List<Integer>> candidatesMap) {
        positionCandidates = candidatesMap;
    }

    @Override
    public void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> positionCandidates) {
        initPositionCandidates(positionCandidates);
        Field sField = Field.getInstance();
        List<Integer> slicePositions = sField.getSlicePositions(ownerAPI.getRowNumber()); //todo: can be extracted

        if (ownerAPI.getNewOwner() == 0) {
        } else {
            //remove candidates of new owner for all positions in a row
            for (Integer position : slicePositions) {
                if (position != ownerAPI.getCurrentPosition() && !sField.isDefined(position)) {
                    List<Integer> values = positionCandidates.getOrDefault(position, Arrays.asList());
                    List<Integer> reducedValues = values.stream().filter(x -> x != ownerAPI.getNewOwner()).collect(Collectors.toList());
                    positionCandidates.replace(position, reducedValues);
                }
            }
        }
    }
}
