package com.sudoku.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConsoleLogger {
    private static Logger logger;
    private static ConsoleLogger consoleLogger;
    private ConsoleLogger() {
    }

    public static synchronized ConsoleLogger getInstance(){
        if (consoleLogger == null) {
            consoleLogger = new ConsoleLogger();
            logger = LogManager.getLogger(ConsoleLogger.class);
        }
        return consoleLogger;
    }

    public void toConsole(String msg){
        logger.info("{}\n", msg);
    }

    public void toConsole(String msg, boolean noNewLine){
        logger.info(msg);
    }
}
