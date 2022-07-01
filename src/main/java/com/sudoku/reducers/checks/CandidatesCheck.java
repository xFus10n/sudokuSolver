package com.sudoku.reducers.checks;

import com.sudoku.domain.FieldElement;
import com.sudoku.reducers.OwnerAPI;

import java.util.List;
import java.util.Map;

public interface CandidatesCheck {
    void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> positionCandidates);
    void checkCandidates(FieldElement fieldElement);
}
