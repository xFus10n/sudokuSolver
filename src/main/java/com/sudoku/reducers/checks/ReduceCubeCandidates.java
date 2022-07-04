package com.sudoku.reducers.checks;

import com.sudoku.Field;
import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.OwnerAPI;

import java.util.List;

public class ReduceCubeCandidates implements CandidatesCheck{

    @Override
    public void checkCandidates(ElementWithHistory elementWithHistory, OwnerAPI api) {
        List<Integer> cubePositionsForReduce = Field.getCubePositions(api.cubeNumber); // row with set element
        if (cubePositionsForReduce.contains(elementWithHistory.getElementPosition())) {
            elementWithHistory.reduceFieldCandidates(api.owner);
        }
    }
}
