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
package br.com.smom.main.util.api.model.to;

import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.exceptions.UtilException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"code", "description"})
public class ResponseResourceTO {

    private int code;
    private String description;

    public ResponseResourceTO() {
    }

    public ResponseResourceTO(Messages message, Object object) {
        this.code = message.getCode();
        this.description = message.getDescription();
    }

    public ResponseResourceTO(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public ResponseResourceTO(UtilException e) {
        this.code = e.getCode();
        this.description = e.getDescription();
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

    public void setMessage(Messages message) {
        this.code = message.getCode();
        this.description = message.getDescription();
    }
    
    public void setMessage(UtilException e) {
        this.code = e.getCode();
        this.description = e.getDescription();
    }

}
