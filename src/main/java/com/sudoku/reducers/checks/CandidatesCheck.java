package com.sudoku.reducers.checks;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.OwnerAPI;

public interface CandidatesCheck {
    void checkCandidates(ElementWithHistory elementWithHistory, OwnerAPI api);
}
