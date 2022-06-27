package com.sudoku.dataholder;

import java.util.ArrayList;
import java.util.List;

public class Utilz {
    public static  <T> ArrayList<T> makeMutable(List<T> inList){
        return new ArrayList<>(inList);
    }
}
