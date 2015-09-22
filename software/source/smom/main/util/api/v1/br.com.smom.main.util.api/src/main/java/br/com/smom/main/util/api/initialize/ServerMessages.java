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
package br.com.smom.main.util.api.initialize;

import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.services.InternalLog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class ServerMessages {

    public static void load() {

        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(System.getenv().get("SMOM_HOME") + "/messages.properties");
            Reader reader = new InputStreamReader(file, "UTF-8");
            properties.load(reader);

            for (Messages message : Messages.values()) {
                String valuePropertie = properties.getProperty(message.name());

                if (valuePropertie != null) {
                    message.setCode(Integer.parseInt(valuePropertie.split(";")[0]));
                    message.setDescription(valuePropertie.split(";")[1]);
                }
            }
        } catch (FileNotFoundException e) {
            InternalLog.severe(String.format("Error load file properties with server messages. Cause: %s;", e.getMessage()));
        } catch (IOException e) {
            InternalLog.severe(String.format("Error load file properties with server messages. Cause: %s;", e.getMessage()));
        }

    }

}
