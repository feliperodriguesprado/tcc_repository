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
package br.com.smom.main.util.api.enums;

public enum Messages {

    INFO(0, ""),
    INFO_INITIALIZED_REQUEST_REST(0, ""),
    INFO_FINISH_REQUEST_REST(0, ""),
    INFO_SUCCESS_USER_FOUND(0, ""),
    WARN(0, ""),
    ERROR(0, ""),
    ERROR_GET_CONNECTION_POSTGRES(0, ""),
    ERROR_COMMIT_CLOSE_CONNECTION_POSTGRES(0, ""),
    ERROR_ROLLBACK_CLOSE_CONNECTION_POSTGRES(0, ""),
    ERROR_EXECUTE_QUERY_POSTGRES(0, ""),
    ERROR_FILL_MODEL_RESULTSET(0, ""),
    ERROR_CANNOT_BE_NULL(0, ""),
    ERROR_DATA_REQUEST_IS_NULL(0, ""),
    ERROR_LOG_CONFIGURATION(0, ""),
    WARN_UNAVAILABLE_MODULE(0, ""),
    FATAL(0, ""),
    FATAL_SYSTEM(0, ""),
    FATAL_GET_ALL_MESSAGES(0, "");

    private int code;
    private String description;

    private Messages(int code, String message) {
        this.code = code;
        this.description = message;
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

    @Override
    public String toString() {
        return String.format("Code=%s; Description=%s; ", code, description);
    }
    
    public String toString(String cause) {
        return String.format("Code=%s; Description=%s; Cause=%s; ", code, description, cause);
    }

    public String toString(Throwable cause) {
        return String.format("Code=%s; Description=%s; Cause=%s; ", code, description, cause.getMessage());
    }

}
