package com.sudoku.reducers.checks;

import com.sudoku.Field;
import com.sudoku.domain.FieldElement;
import com.sudoku.reducers.OwnerAPI;

import java.util.*;
import java.util.stream.Collectors;

import static com.sudoku.reducers.Utilz.*;


public class ReduceRowsCandidates implements CandidatesCheck {

    @Override
    public void checkCandidates(FieldElement fieldElement) {
        Field sField = Field.getInstance();
        int lastSetPosition = sField.getLastSetPosition();
        int reduceValue = sField.getFieldValue(lastSetPosition);
        int rowNumber = sField.getRowIDbyPosition(lastSetPosition);
        List<Integer> rowPositionsForReduce = sField.getRowPositions(rowNumber);
        if (rowPositionsForReduce.contains(fieldElement.getPosition())) {
            /* start reduce */

        } else {
            /* update counter only */
        }
    }

    @Override
    public void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> candidatesMap) {
        Field sField = Field.getInstance();
        List<Integer> rowPositions = sField.getRowPositions(ownerAPI.getRowNumber()); //todo: can be extracted
        if (ownerAPI.getPreviousOwner() != 0) appendCandidate(ownerAPI.getCurrentPosition(), ownerAPI.getPreviousOwner(), rowPositions, candidatesMap);
        if (ownerAPI.getNewOwner() == 0) {
            List<Integer>filter = getFilterValues(ownerAPI.getCurrentPosition(), ownerAPI.getPreviousOwner(), rowPositions, candidatesMap); //previous owner becomes a candidate for all positions in a row
            removeCertainCandidates(filter, ownerAPI.getCurrentPosition(), candidatesMap);
        } else {
            //remove candidates of new owner for all positions in a row
            for (Integer position : rowPositions) {
                if (position != ownerAPI.getCurrentPosition() && !sField.isDefined(position)) {
                    List<Integer> values = candidatesMap.getOrDefault(position, Arrays.asList());
                    List<Integer> reducedValues = values.stream().filter(x -> x != ownerAPI.getNewOwner()).collect(Collectors.toList());
                    candidatesMap.replace(position, reducedValues);
                }
            }
        }
    }
}
