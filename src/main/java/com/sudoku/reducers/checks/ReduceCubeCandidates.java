package com.sudoku.reducers.checks;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.OwnerAPI;
import java.util.List;

import static com.sudoku.utils.FieldUtilz.getCubePositions;

public class ReduceCubeCandidates implements CandidatesCheck{

    @Override
    public void checkCandidates(ElementWithHistory elementWithHistory, OwnerAPI api) {
        List<Integer> cubePositionsForReduce = getCubePositions(api.cubeNumber); // row with set element
        if (cubePositionsForReduce.contains(elementWithHistory.getElementPosition())) {
            elementWithHistory.reduceFieldCandidates(api.owner);
        }
    }
}
