package com.sudoku.reducers.checks;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.OwnerAPI;

public class ReduceDoublesRowsCandidate implements CandidatesCheck{

    @Override
    public void checkCandidates(ElementWithHistory elementWithHistory, OwnerAPI api) {

    }
//
//    @Override
//    public void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> positionCandidates) {
//        Field sField = Field.getInstance();
//        //for all rows
//        for (int i = 0; i < Field.DIM_SIZE; i++) {
//            List<Integer> rowPositions = sField.getRowPositions(i);
//            List<List<Integer>> doublesCandidates = getDoublesCandidates(positionCandidates, rowPositions);
//            doublesCandidates.forEach(doubles -> removeDoublesCandidates(positionCandidates, rowPositions, doubles));
//        }
//    }
//
//    private List<List<Integer>> getDoublesCandidates(Map<Integer, List<Integer>> positionCandidates, List<Integer> rowPositions) {
//        Set<List<Integer>> checkSet = new HashSet<>();
//        List<List<Integer>> output = new ArrayList<>();
//        //for all positions
//        for (Integer position : rowPositions) {
//            List<Integer> candidates = positionCandidates.get(position);
//            if (candidates.size() == 2) {
//                boolean match = !checkSet.add(candidates) /* set returns false if adding duplicate */;
//                if (match) output.add(candidates);
//            }
//        }
//        return output;
//    }
//
//    private void removeDoublesCandidates(Map<Integer, List<Integer>> positionCandidates, List<Integer> rowPositions, List<Integer> doubles) {
//        for (Integer position : rowPositions) {
//            List<Integer> candidates = positionCandidates.get(position);
//            if (candidates.size() != 2 && candidates != doubles) {
//                Utilz.removeCertainCandidates(doubles, position, positionCandidates);
//            }
//        }
//    }
}
