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
package br.com.smom.main.datasource.api.exceptions;

import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.exceptions.UtilException;

public class DataSourceException extends UtilException {

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(int code, String description) {
        super(code, description);
    }

    public DataSourceException(int code, String description, Throwable cause) {
        super(code, description, cause);
    }

    public DataSourceException(Messages message) {
        super(message);
    }

    public DataSourceException(Messages message, String cause) {
        super(message, cause);
    }

    public DataSourceException(Messages message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }

}
