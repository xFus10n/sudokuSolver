package com.sudoku.dataholder.checks;

import com.sudoku.dataholder.OwnerAPI;

import java.util.List;
import java.util.Map;

public interface CandidatesCheck {
    void checkCandidates(OwnerAPI ownerAPI, Map<Integer, List<Integer>> positionCandidates);
}
