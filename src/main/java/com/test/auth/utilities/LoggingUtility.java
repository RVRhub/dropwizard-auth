package com.test.auth.utilities;

import org.apache.log4j.Logger;

public class LoggingUtility {
    private final static Logger logger = Logger.getLogger(LoggingUtility.class);

    private LoggingUtility() {

    }

    public static void info(final String message) {
        logger.info(message);
    }

    public static void warning(final String message) {
        logger.warn(message);
    }

    public static void error(final String message) {
        logger.error(message);
    }

    public static void exception(final String message, final Throwable t) {
        logger.error(message, t);
    }
}
