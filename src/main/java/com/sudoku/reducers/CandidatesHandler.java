package com.sudoku.reducers;

import com.sudoku.domain.ElementWithHistory;
import com.sudoku.reducers.checks.*;

import java.util.*;

public class CandidatesHandler {
    private final List<CandidatesCheck> reducers;

    public CandidatesHandler() {
        reducers = initReducers();
    }

    private static List<CandidatesCheck> initReducers(){
        List<CandidatesCheck> reducersList = new ArrayList<>();
        reducersList.add(new ReduceRowsCandidates());
//        reducersList.add(new ReduceSliceCandidates());
//        reducersList.add(new ReduceCubeCandidates());
//        reducersList.add(new ReduceDoublesRowsCandidate());
        return reducersList;
    }

    public void reduce(ElementWithHistory elementWithHistory, OwnerAPI api){
        for (CandidatesCheck reducer : reducers) {
            reducer.checkCandidates(elementWithHistory, api);
        }
    }
}
