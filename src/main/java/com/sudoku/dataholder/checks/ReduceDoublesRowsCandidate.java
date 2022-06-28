package com.sudoku.dataholder.checks;

import com.sudoku.Field;
import com.sudoku.dataholder.OwnerAPI;
import com.sudoku.dataholder.Utilz;

import java.util.*;

public class ReduceDoublesRowsCandidate implements CandidatesCheck{

    @Override
    public void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> positionCandidates) {
        Field sField = Field.getInstance();

        //for all rows
        for (int i = 0; i < Field.DIM_SIZE; i++) {
            List<Integer> rowPositions = sField.getRowPositions(ownerAPI.getRowNumber());
            Optional<List<Integer>> doublesCandidates = getDoublesCandidates(positionCandidates, rowPositions);
            doublesCandidates.ifPresent(doubles -> removeDoublesCandidates(positionCandidates, rowPositions, doubles));
        }
    }

    private Optional<List<Integer>> getDoublesCandidates(Map<Integer, List<Integer>> positionCandidates, List<Integer> rowPositions) {
        Set<List<Integer>> checkSet = new HashSet<>();
        //for all positions
        for (Integer position : rowPositions) {
            List<Integer> candidates = positionCandidates.get(position);
            if (candidates.size() == 2) {
                boolean match = checkSet.add(candidates);
                if (match && checkSet.size() == 2) return Optional.of(candidates);
            }
        }
        return Optional.empty();
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
