/*
 * Copyright 2015 Smom - Software Module Management.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.smom.log.core.services;

import br.com.smom.log.api.exceptions.LogException;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.services.InternalLog;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogService implements Log {

    @Override
    public void debug(String message) {
        try {
            getLogger().debug(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void debug(String message, Throwable cause) {
        try {
            getLogger().debug(message, cause);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void info(String message) {
        try {
            getLogger().info(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void warn(String message) {
        try {
            getLogger().warn(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void warn(String message, Throwable cause) {
        try {
            getLogger().warn(message, cause);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void error(String message) {
        try {
            getLogger().error(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void error(String message, Throwable cause) {
        try {
            getLogger().error(message, cause);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void fatal(String message) {
        try {
            getLogger().fatal(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void fatal(String message, Throwable cause) {
        try {
            getLogger().fatal(message, cause);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    private Logger getLogger() throws LogException {

        Logger logger;
        Appender fileAppender;
        String fileAppenderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        boolean doAppender = false;

        logger = Logger.getLogger(LogService.class);
        fileAppender = logger.getAppender(fileAppenderName);

        if (fileAppender != null) {
            if (!fileAppender.getName().equals(fileAppenderName)) {
                doAppender = true;
            }
        } else {
            doAppender = true;
        }

        if (doAppender) {
            logger.removeAllAppenders();
            logger.addAppender(getFileAppender());
            logger.addAppender(getConsoleAppender());
            logger.setLevel(Level.DEBUG);
        }

        return logger;
    }

    private FileAppender getFileAppender() throws LogException {

        SimpleDateFormat sdf;
        String path;
        FileAppender fileAppender;

        try {
            sdf = new SimpleDateFormat("yyyyMMdd");
            path = System.getenv().get("SMOM_HOME") + "/logs/" + sdf.format(new Date()) + ".log";
            fileAppender = new FileAppender(getPatternLayout(), path);
            fileAppender.setName(sdf.format(new Date()));
            return fileAppender;
        } catch (IOException e) {
            InternalLog.warning(Messages.ERROR_LOG_CONFIGURATION + String.format("Cause=%s.", e.getMessage()));
            throw new LogException(Messages.ERROR_LOG_CONFIGURATION, e);
        }
    }

    private ConsoleAppender getConsoleAppender() throws LogException {
        ConsoleAppender consoleAppender;
        consoleAppender = new ConsoleAppender(getPatternLayout());
        consoleAppender.setName("console");
        return consoleAppender;
    }

    private PatternLayout getPatternLayout() {
        return new PatternLayout("%d{dd/MM/yy HH:mm:ss} %5p: %m%n");
    }

}
