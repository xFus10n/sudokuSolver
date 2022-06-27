package com.sudoku.dataholder;

import com.sudoku.Field;
import com.sudoku.dataholder.checks.CandidatesCheck;
import com.sudoku.dataholder.checks.ReduceRowsCandidates;

import java.util.*;

public class CandidatesDataHolder {
    private final Map<Integer, List<Integer>> positionCandidates;
    private final List<CandidatesCheck> reducers;

    public CandidatesDataHolder() {
        positionCandidates = initPositions();
        reducers = initReducers();
    }

    private List<CandidatesCheck> initReducers(){
        List<CandidatesCheck> reducers = new ArrayList<>();
        reducers.add(new ReduceRowsCandidates(positionCandidates));
        return reducers;
    }

    private Map<Integer, List<Integer>> initPositions() {
        Map<Integer, List<Integer>> candidates = new HashMap<>();
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            candidates.put(i, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
        return candidates;
    }

    public void reset() {
        for (int i = 0; i < Field.FIELD_CAPACITY; i++) {
            positionCandidates.replace(i, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
    }

    public List<Integer> getPositionCandidates(int pos) {
        return positionCandidates.getOrDefault(pos, Arrays.asList());
    }

    public void setPositionOwner(OwnerAPI ownerAPI){
        if (ownerAPI.previousOwner == ownerAPI.newOwner) return;
        if (ownerAPI.newOwner == 0) {
            positionCandidates.replace(ownerAPI.currentPosition, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); //put initial list, later filter
        } else {
            positionCandidates.replace(ownerAPI.currentPosition, Arrays.asList(ownerAPI.newOwner));
        }

        for (CandidatesCheck reducer : reducers) {
            reducer.checkCandidates(ownerAPI);
        }
        //reduce slice candidates
        //reduce cube candidates
    }
}
