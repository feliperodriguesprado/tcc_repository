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

import br.com.smom.user.api.enums.UserMessages;
import br.com.smom.user.api.exceptions.UserException;
import br.com.smom.user.api.model.entities.UserEntity;
import br.com.smom.user.api.services.User;
import br.com.smom.user.core.repositories.IUserRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService implements User {

    @Inject
    private IUserRepository userRepository;

    @Override
    public UserEntity create(UserEntity user) throws UserException {
        try {
            return userRepository.create(user);

        } catch (UserException e) {
            throw new UserException(UserMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public void update(UserEntity user) throws UserException {
        try {
            userRepository.update(user);

        } catch (UserException e) {
            throw new UserException(UserMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public void delete(UserEntity user) throws UserException {
        try {
            userRepository.delete(user);
            
        } catch (UserException e) {
            throw new UserException(UserMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public UserEntity getById(int id) throws UserException {
        try {
            return userRepository.getById(id);
            
        } catch (UserException e) {
            throw new UserException(UserMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public boolean authenticateLogin(UserEntity user) throws UserException {
        UserEntity entity = userRepository.getByUsername(user);
        if(entity != null){
            return entity.getPassword().equals(user.getPassword());
        } 
        return false;
    }

}
