package com.sudoku.logger;

import org.apache.log4j.Logger;

public class ConsoleLogger {
    private static Logger logger;
    private static ConsoleLogger consoleLogger;
    private ConsoleLogger() {
    }

    public static synchronized ConsoleLogger getInstance(){
        if (consoleLogger == null) {
            consoleLogger = new ConsoleLogger();
            logger = Logger.getLogger(ConsoleLogger.class);
        }
        return consoleLogger;
    }

    public void toConsole(String msg){
        logger.info(msg + "\n");
    }

    public void toConsole(String msg, boolean noNewLine){
        logger.info(msg);
    }
}
