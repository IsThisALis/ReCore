package com.isthisalis.recore.util;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Logging utility class.
 */
@Deprecated
public class Logging {

    private final Logger logger;
    private static FileHandler handler;


      /**
       * Create new logger.
       * @param name Your name for object that calls logger.
       */
    public Logging(String name) {
      logger = Logger.getLogger(name);
    }


      /**
       * Sends message to info.
       * @param msg Message to be sent.
       */
    public void info(String msg) {
      logger.info(msg);
    }


      /**
       * Sends message to warning.
       * @param msg Message to be sent.
       */
    public void warning(String msg) {
      logger.warning(msg);
    }


      /**
       * Sends message and Exception to error.
       * @param msg Message to be sent.
       * @param e Exception to be sent.
       */
    public void error(String msg, Exception e) {
      logger.log(Level.SEVERE, msg, e);
    }


      /**
       * Sends message and Exception to error.
       * @param msg Message to be sent.
       */
    public void error(String msg) {
      logger.log(Level.SEVERE, msg);
    }


      /**
       * Sets logging to file.
       */
    public boolean logToFile() {
      logger.setUseParentHandlers(false);
      if (logger == null) { System.err.println("ReCore: Unable to find logger!"); return false; }

      try {
          if (handler == null) handler = new FileHandler(logger.getName() + ".log", 0, 1, true);
        } catch (Exception e) {
          if (logger != null) this.error("Unable to set up logfiles handling", e);
        }
      handler.setLevel(Level.ALL);
      handler.setFormatter(new BaseFormat());
      logger.addHandler(handler);
      return true; 
    }


    private class BaseFormat extends Formatter {
      
        @Override
      public String format(LogRecord rec) {
        return String.format("%s  %s: %s%n", rec.getLevel(), rec.getLoggerName(), rec.getMessage());
      }
    }
}
