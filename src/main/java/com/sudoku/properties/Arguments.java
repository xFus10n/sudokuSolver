package com.sudoku.properties;

import com.sudoku.Field;
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
                    System.setProperty(SysProperties.ARG_DELIMITER.name(), ";");
                    arguments = new Arguments();
                    values = new ArrayList<>(Field.FIELD_CAPACITY);
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

    public static void initializeArgumentContainer(String[] inArguments){
        System.clearProperty(SysProperties.INPUT_ARGUMENTS.name());
        System.setProperty(SysProperties.INPUT_ARGUMENTS.name(), String.join(";", inArguments));
        valuesAreSet = true;
        if (arguments != null) arguments = null;
    }

    private static void setValues(){
        String inArgs = System.getProperty(SysProperties.INPUT_ARGUMENTS.name());
        String delimiter = System.getProperty(SysProperties.ARG_DELIMITER.name());
        for(String s : inArgs.split(delimiter)) {
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
