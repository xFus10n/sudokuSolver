package com.sudoku.dataholder.checks;

import com.sudoku.Field;
import com.sudoku.dataholder.OwnerAPI;
import com.sudoku.dataholder.Utilz;

import java.util.*;

public class ReduceDoublesRowsCandidate implements CandidatesCheck{

    @Override
    public void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> positionCandidates) {
        Field sField = Field.getInstance();
        //fixme: apply only on modified rows the same way for row check does
        //for all rows
        for (int i = 0; i < Field.DIM_SIZE; i++) {
            List<Integer> rowPositions = sField.getRowPositions(i);
            List<List<Integer>> doublesCandidates = getDoublesCandidates(positionCandidates, rowPositions);
            doublesCandidates.forEach(doubles -> removeDoublesCandidates(positionCandidates, rowPositions, doubles));
        }
    }

    private List<List<Integer>> getDoublesCandidates(Map<Integer, List<Integer>> positionCandidates, List<Integer> rowPositions) {
        Set<List<Integer>> checkSet = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        //for all positions
        for (Integer position : rowPositions) {
            List<Integer> candidates = positionCandidates.get(position);
            if (candidates.size() == 2) {
                boolean match = !checkSet.add(candidates) /* set returns false if adding duplicate */;
                if (match) output.add(candidates);
            }
        }
        return output;
    }

    private void removeDoublesCandidates(Map<Integer, List<Integer>> positionCandidates, List<Integer> rowPositions, List<Integer> doubles) {
        for (Integer position : rowPositions) {
            List<Integer> candidates = positionCandidates.get(position);
            if (candidates.size() != 2 && candidates != doubles) {
                Utilz.removeCertainCandidates(doubles, position, positionCandidates);
            }
        }
    }
}
