package com.sudoku.reducers.checks;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.OwnerAPI;

import java.util.List;

import static com.sudoku.utils.FieldUtilz.getSlicePositions;

public class ReduceSliceCandidates implements CandidatesCheck {

    @Override
    public void checkCandidates(ElementWithHistory elementWithHistory, OwnerAPI api) {
        List<Integer> colPositionsForReduce = getSlicePositions(api.colNumber); // column with set element
        if (colPositionsForReduce.contains(elementWithHistory.getElementPosition())) {
            elementWithHistory.reduceFieldCandidates(api.owner);
        }
    }
}
