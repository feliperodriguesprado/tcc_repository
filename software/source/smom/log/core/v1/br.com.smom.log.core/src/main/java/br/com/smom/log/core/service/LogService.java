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
package br.com.smom.log.core.service;

import br.com.smom.log.api.service.Log;
import br.com.smom.main.util.api.service.InternalLog;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LogService implements Log {

    
    
    @Override
    public void info(String message) {

        Logger logger = getLogger();

        if (logger != null) {
            logger.info(message);
        }
    }

    @Override
    public void error(String message) {

        Logger logger = getLogger();

        if (logger != null) {
            logger.error(message);
        }

    }

    private Logger getLogger() {

        Logger logger;

        try {
            logger = Logger.getLogger(LogService.class);
            logger.removeAllAppenders();
            logger.setLevel(Level.TRACE);            
            logger.addAppender(getFileAppender());
            return logger;
        } catch (IOException e) {
            return null;
        }
    }

    private FileAppender getFileAppender() throws IOException {

        SimpleDateFormat sdf;
        String path;
        FileAppender fileAppender;

        try {
            sdf = new SimpleDateFormat("yyyyMMdd");
            path = System.getenv().get("HOME") + "/logs/" + sdf.format(new Date()) + ".log";
            fileAppender = new FileAppender(getPatternLayout(), path);
            fileAppender.setName("file");
            return fileAppender;
        } catch (IOException e) {
            InternalLog.severe("Error get file appender Log4j. Cause: " + e.getMessage());
            throw e;
        }
    }

    private PatternLayout getPatternLayout() {

        PatternLayout patternLayout;

        patternLayout = new PatternLayout("%d{dd/MM/yy HH:mm:ss} %5p: %m%n");
        return patternLayout;
    }

}
