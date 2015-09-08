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
package br.com.smom.main.util.api.exception;

import br.com.smom.main.util.api.service.InternalLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class UtilException extends Exception {

    private int code;
    private String description;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public UtilException(String message) {
        super(message);
    }

    public UtilException(int code, String description, Throwable cause) {
        super("Code=" + code + " Description=" + description, cause);
        this.code = code;
        this.description = description;
        InternalLog.warning(String.format("%s Cause: %s", super.getMessage(), super.getCause().getMessage()));
    }

    public UtilException(int code, String description) {
        super("Code=" + code + " Description=" + description);
        this.code = code;
        this.description = description;
        InternalLog.warning(String.format("%s", super.getMessage()));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
