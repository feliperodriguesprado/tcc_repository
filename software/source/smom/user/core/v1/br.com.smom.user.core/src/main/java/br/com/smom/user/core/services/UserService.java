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
package br.com.smom.user.core.services;

import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.user.api.model.entities.UserEntity;
import br.com.smom.user.api.services.User;
import javax.ejb.Stateless;

@Stateless
public class UserService implements User {

    @Override
    public UserEntity create(UserEntity user) throws UtilException {
        return null;
    }

    @Override
    public void update(UserEntity user) throws UtilException {
    }

    @Override
    public void delete(UserEntity user) throws UtilException {
    }

    @Override
    public UserEntity getById(int id) throws UtilException {
        return null;
    }

    @Override
    public boolean authenticateLogin(UserEntity user) throws UtilException {
        return true;
    }

}
