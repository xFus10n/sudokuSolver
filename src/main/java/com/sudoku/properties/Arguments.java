package com.sudoku.properties;

import com.sudoku.logger.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;

public class Arguments {
    private static boolean valuesAreSet;
    private static List<Integer> values;
    private static Arguments arguments;
    private Arguments(){}
    public static synchronized Arguments getInstance(){
        if (valuesAreSet) {
            if (arguments == null) {
                try {
                    arguments = new Arguments();
                    values = new ArrayList<>(80);
                    setValues();
                } catch (Exception e) {
                    ConsoleLogger.getInstance().toConsole(e.getMessage());
                }

            }
            return arguments;
        } else {
            ConsoleLogger.getInstance().toConsole("Pass arguments in main method first");
            System.exit(0);
        }
        return null;
    }

    public static void setArguments(String[] arguments){
        System.setProperty(SysProperties.ARG_DELIMITER.name(), ";");
        System.setProperty(SysProperties.INPUT_ARGUMENTS.name(), String.join(";", arguments));
        valuesAreSet = true;
    }

    private static void setValues(){
        String arguments = System.getProperty(SysProperties.INPUT_ARGUMENTS.name());
        String delimiter = System.getProperty(SysProperties.ARG_DELIMITER.name());
        for(String s : arguments.split(delimiter)) {
            try {
                values.add(Integer.valueOf(s));
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public List<Integer> getValues(){
        return values;
    }
}
