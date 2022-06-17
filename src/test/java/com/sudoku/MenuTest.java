package com.sudoku;

import com.sudoku.menu.Action;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class MenuTest {


    @Test
    void testMenu(){
        try {
            Reflections reflections = new Reflections("com.sudoku.menu");
            Set<Class<? extends Action>> classes = reflections.getSubTypesOf(Action.class);
            System.out.println(classes);
            Action show = (Action) Class.forName(classes.iterator().next().getName()).getDeclaredConstructor().newInstance();
            System.out.println(show.name());
            show.execute(Field.getInstance());
        } catch (InstantiationException | NoSuchMethodException | SecurityException | ClassNotFoundException | InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
