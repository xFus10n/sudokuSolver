package com.sudoku.reducers.checks;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.OwnerAPI;
import java.util.*;

import static com.sudoku.utils.FieldUtilz.getRowPositions;


public class ReduceRowsCandidates implements CandidatesCheck {

    @Override
    public void checkCandidates(ElementWithHistory elementWithHistory, OwnerAPI api) {
        List<Integer> rowPositionsForReduce = getRowPositions(api.rowNumber); // row with set element
        if (rowPositionsForReduce.contains(elementWithHistory.getElementPosition())) {
            elementWithHistory.reduceFieldCandidates(api.owner);
        }
    }
}
