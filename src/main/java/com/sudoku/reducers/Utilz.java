package com.sudoku.reducers;

import com.sudoku.Field;

import java.util.*;

public class Utilz {
    public static  <T> ArrayList<T> makeMutable(List<T> inList){
        return new ArrayList<>(inList);
    }

    /**
     * Values in a row that has only one candidate for particular position
     * should be removed as a candidates for newly reset position
     * @param filter List of values for removal
     * @param pos reset position
     */
    public static void removeCertainCandidates(List<Integer> filter, int pos, Map<Integer, List<Integer>> candidatesMap) {
        List<Integer> values = makeMutable(candidatesMap.get(pos));
        values.removeAll(filter);
        candidatesMap.replace(pos, values);
    }

    public static List<Integer> getFilterValues(int previousOwnerPosition, int previousOwner, List<Integer> positions, Map<Integer, List<Integer>> candidatesMap) {
        Field sField = Field.getInstance();
        List<Integer> valuesToFilterOut = new ArrayList<>();
        for (Integer position : positions) {
            if ((position != previousOwnerPosition) && (sField.isDefined(position))) {
                List<Integer> values = candidatesMap.getOrDefault(position, Arrays.asList());
                valuesToFilterOut.add(values.get(0));
            }
        }
        return valuesToFilterOut;
    }

    public static void appendCandidate(int previousOwnerPosition, int previousOwner, List<Integer> positions, Map<Integer, List<Integer>> candidatesMap) {
        Field sField = Field.getInstance();
        for (Integer position : positions) {
            if (position != previousOwnerPosition) {
                if (!sField.isDefined(position)) {
                    List<Integer> values = candidatesMap.getOrDefault(position, Arrays.asList());
                    values.add(previousOwner);
                    Collections.sort(values);
                    candidatesMap.replace(position, values);
                }
            }
        }
    }
}
