/*
 * Smom - Software Module Management.
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
package br.com.smom.main.util.api.exceptions;

import br.com.smom.main.util.api.enums.Messages;

public class UtilException extends Exception {

    private int code;
    private String description;

    public UtilException(String message) {
        super(message);
    }

    public UtilException(int code, String description) {
        super("Code=" + code + " Description=" + description);
        this.code = code;
        this.description = description;
    }

    public UtilException(int code, String description, Throwable cause) {
        super("Code=" + code + " Description=" + description, cause);
        this.code = code;
        this.description = description;
    }

    public UtilException(Messages message) {
        super(message.getMessage());
        this.code = message.getCode();
        this.description = message.getDescription();
    }

    public UtilException(Messages message, String cause) {
        super(message.getMessage() + String.format("Cause=%s; ", cause));
        this.code = message.getCode();
        this.description = message.getDescription();
    }

    public UtilException(Messages message, Throwable cause) {
        super(String.format("%s Cause=%s;", message.getMessage(), cause.getMessage()));
        this.code = message.getCode();
        this.description = message.getDescription();
    }

    public UtilException(Throwable cause) {
        super(cause);
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
