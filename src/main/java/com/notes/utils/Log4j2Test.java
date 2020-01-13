package com.notes.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Test {
    public static void main(String[] args) throws Exception {
//        Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        Logger logger = LogManager.getLogger(/*"study"*/);
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");

//        throw new Exception("test");
    }
}