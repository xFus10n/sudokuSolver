package com.sudoku.menuHandler;

import com.google.common.collect.Maps;
import com.sudoku.menu.Action;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Handler {

    public static Map<Integer, Action> getServices() {

        Reflections reflections = new Reflections("com.sudoku.menu");
        Set<Class<? extends Action>> classes = reflections.getSubTypesOf(Action.class);
        List<Action> actions = classes.stream()
                                      .map(x -> {
                                          var className = x.getName();
                                          try {
                                              return (Action) Class.forName(className)
                                                                   .getDeclaredConstructor()
                                                                   .newInstance();
                                          } catch (InstantiationException | NoSuchMethodException | SecurityException | ClassNotFoundException |
                                                  InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
                                              Logger.getLogger(Handler.class)
                                                    .error(e.getMessage());
                                          }
                                          return null;
                                      })
                                      .collect(Collectors.toList());
        return convertList(actions);
    }

    private static Map<Integer, Action> convertList(List<Action> list) {
        return Maps.uniqueIndex(list, Action::id);
    }
}
