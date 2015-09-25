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

import br.com.smom.log.api.enums.LogMessages;
import br.com.smom.log.api.exceptions.LogException;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.services.InternalLog;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogService implements Log {

    @Override
    public void debug(String message) {
        try {
            getLogger().fine(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void debug(String message, Throwable cause) {
        try {
            getLogger().log(Level.FINE, message, cause);
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
            getLogger().warning(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void warn(String message, Throwable cause) {
        try {
            getLogger().log(Level.WARNING, message, cause);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void error(String message) {
        try {
            getLogger().severe(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void error(String message, Throwable cause) {
        try {
            getLogger().log(Level.SEVERE, message, cause);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void fatal(String message) {
        try {
            getLogger().severe(message);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    @Override
    public void fatal(String message, Throwable cause) {
        try {
            getLogger().log(Level.SEVERE, message, cause);
        } catch (LogException e) {
            InternalLog.warning(e.getMessage());
        }
    }

    private Logger getLogger() throws LogException {

        Logger logger = Logger.getLogger(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        Handler[] handlerList = logger.getHandlers();

        if (handlerList.length == 0) {
            logger.addHandler(getFileHandler());
            logger.setLevel(Level.FINE);
        }

        return logger;
    }

    private FileHandler getFileHandler() throws LogException {

        SimpleDateFormat sdf;
        String path;
        FileHandler fileHandler;
        int id = Math.abs(new Random().nextInt());

        try {
            sdf = new SimpleDateFormat("yyyyMMdd");
            path = System.getenv().get("SMOM_HOME") + "/logs/" + sdf.format(new Date()) + "_" + id + ".logging";
            fileHandler = new FileHandler(path);
            fileHandler.setFormatter(new SimpleFormatter());
            return fileHandler;
        } catch (IOException e) {
            InternalLog.warning(LogMessages.ERROR_LOG_CONFIGURATION + String.format("Cause=%s.", e.getMessage()));
            throw new LogException(LogMessages.ERROR_LOG_CONFIGURATION, e);
        }
    }

}
