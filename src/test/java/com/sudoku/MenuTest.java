package com.sudoku;

import com.sudoku.menu.Action;
import com.sudoku.menuHandler.Handler;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class MenuTest {


    @Test
    void testMenu(){
        Map<Integer, Action> services = Handler.getServices();
        for (Map.Entry<Integer, Action> entry : services.entrySet()) {
            System.out.println("Id: " + entry.getKey());
            System.out.println(entry.getValue().name());
        }
    }
}
