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
package br.com.smom.user.api.model.entities;

import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "name", "password", "userName", "active", "responseResource"})
public class UserEntity {

    private int id;           // Column DB
    private String name;      // Column DB
    private String password;  // Column DB
    private String userName;  // Column DB
    private boolean active;   // Column DB
    private ResponseResourceTO responseResource;

    public UserEntity() {
    }

    public UserEntity(int id, String name, String password, String userName, boolean active) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userName = userName;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ResponseResourceTO getResponseResource() {
        return responseResource;
    }

    public void setResponseResource(ResponseResourceTO responseResource) {
        this.responseResource = responseResource;
    }

}
