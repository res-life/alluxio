/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.util;

import alluxio.wire.LogInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Utility methods for working with log.
 */
@ThreadSafe
public final class LogUtils {
  /** The truncated length for a message line. */
  public static final int MAX_TRUNCATED_LENGTH = 300;

  private LogUtils() {} // prevent instantiation

  /**
   * Gets a logger's level with specify name, if the level argument is not null, it will set to
   * specify level first.
   * @param logName logger's name
   * @param level logger's level
   * @return an entity object about the log info
   * @throws IOException if an I/O error occurs
   */
  public static LogInfo setLogLevel(String logName, String level) throws IOException {
    LogInfo logInfo = new LogInfo();
    if (StringUtils.isNotBlank(logName)) {
      try {
        LoggerContext ctx = (LoggerContext) (LogManager.getContext(false));
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(logName);
        if (loggerConfig != null) {
          Level toLevel = Level.getLevel(level.toUpperCase());
          if (toLevel != null) {
            loggerConfig.setLevel(toLevel);
            Level originLevel = loggerConfig.getLevel();
            ctx.updateLoggers();
            logInfo.setLevel(originLevel.toString());
            logInfo.setMessage("Setting Level to " + toLevel);
          } else {
            logInfo.setMessage("Bad level : " + level);
          }
        } else {
          logInfo.setMessage("log is null.");
        }
      } catch (Exception e) {
        String msg = String.format("Exception occurred when setting log level, log name %s, log level %s",
            e.getMessage(), logName, level);
        logInfo.setMessage(msg);
      }
    } else {
      logInfo.setMessage("Please specify a correct logName.");
    }

    return logInfo;
  }

  /**
   * Log a warning message with full exception if debug logging is enabled,
   * or just the exception string otherwise.
   *
   * @param logger the logger to be used
   * @param message the message to be logged
   * @param args the arguments for the message
   */
  public static void warnWithException(Logger logger, String message, Object ...args) {
    if (logger.isDebugEnabled()) {
      logger.debug(message, args);
    } else {
      if (args.length > 0 && args[args.length - 1] instanceof Throwable) {
        args[args.length - 1] = (args[args.length - 1]).toString();
      }
      logger.warn(message + ": {}", args);
    }
  }

  /**
   * Truncates each line of a message to a certain length.
   *
   * @param message the message to truncate the lines for
   * @return the message, with lines truncated to length {@link #MAX_TRUNCATED_LENGTH}
   */
  public static String truncateMessageLineLength(Object message) {
    return truncateMessageLineLength(message, MAX_TRUNCATED_LENGTH);
  }

  /**
   * Truncates each line of a message to a certain length.
   *
   * @param message the message to truncate the lines for
   * @param maxLineLength the maximum length of a message line
   * @return the message, with lines truncated to the specified length
   */
  public static String truncateMessageLineLength(Object message, int maxLineLength) {
    if (message == null) {
      return "null";
    }
    String strMessage = message.toString();
    if (strMessage.length() <= maxLineLength) {
      return strMessage;
    }
    return Arrays.stream(strMessage.split("\n")).map(line -> {
      if (line.length() > maxLineLength) {
        return String.format("%s ... <truncated %d characters>", line.substring(0, maxLineLength),
            line.length() - maxLineLength);
      }
      return line;
    }).collect(Collectors.joining("\n"));
  }
}
