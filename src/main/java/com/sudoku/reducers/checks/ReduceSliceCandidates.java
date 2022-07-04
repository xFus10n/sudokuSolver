package com.sudoku.reducers.checks;

import com.sudoku.Field;
import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.OwnerAPI;

import java.util.List;

public class ReduceSliceCandidates implements CandidatesCheck {

    @Override
    public void checkCandidates(ElementWithHistory elementWithHistory, OwnerAPI api) {
        List<Integer> colPositionsForReduce = Field.getSlicePositions(api.colNumber); // column with set element
        if (colPositionsForReduce.contains(elementWithHistory.getElementPosition())) {
            elementWithHistory.reduceFieldCandidates(api.owner);
        }
    }
}
