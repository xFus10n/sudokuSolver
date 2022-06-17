package com.sudoku.menuHandler;

import com.sudoku.menu.Action;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class ActionsHandler {

    /**
     * Get Commands for menu
     * Using reflection find all classes which implements Action interface
     * Each command assigned to an ID in tree map
     * @return map of actions for menu
     */
    public static TreeMap<Integer, Action> getServices() {

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
                                              Logger.getLogger(ActionsHandler.class)
                                                    .error(e.getMessage());
                                          }
                                          return null;
                                      })
                                      .collect(Collectors.toList());
        return convertList2TreeMap(actions);
    }

    private static TreeMap<Integer, Action> convertList2TreeMap(List<Action> list) {
        var actionTree = new TreeMap<Integer, Action>();
        for (Action action : list) {
            actionTree.put(action.id(), action);
        }
        return actionTree;
    }
}
