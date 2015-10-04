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
package br.com.smom.user.core.repositories;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.datasource.api.services.PostgreSQL;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.user.api.enums.UserMessages;
import br.com.smom.user.api.exceptions.UserException;
import br.com.smom.user.api.model.entities.UserEntity;
import br.com.smom.user.core.dao.IUserDAO;
import java.sql.Connection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserRepository implements IUserRepository {

    private PostgreSQL postgreSQLService = null;
    private Log logService = null;
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserEntity create(UserEntity user) throws UserException {
        postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        UserEntity userCreated;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                userDAO.setConnection(connection);
                userCreated = userDAO.create(user);

                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("User created: " + userCreated.toString());
                }
                return userCreated;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                if(e.getMessage().contains("uk_users_username")){
                    throw new UserException(UserMessages.WARN_USER_EXISTS);
                }
                throw new UserException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(UserMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UserException(UserMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public void update(UserEntity user) throws UserException {
        postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        UserEntity userUpdated;
        Connection connection = null;
        int customerId;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerId = user.getId();
                userDAO.setConnection(connection);
                userDAO.update(user);
                userUpdated = userDAO.getById(customerId);
                
                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("User updated: " + userUpdated.toString());
                }
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                if(e.getMessage().contains("uk_users_username")){
                    throw new UserException(UserMessages.WARN_USER_EXISTS);
                }
                throw new UserException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(UserMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UserException(UserMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public void delete(UserEntity user) throws UserException {
        postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                userDAO.setConnection(connection);
                userDAO.delete(user);
                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("User deleted: " + user.toString());
                }
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new UserException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(UserMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UserException(UserMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public UserEntity getById(int id) throws UserException {
        postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        UserEntity userEntity;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                userDAO.setConnection(connection);
                userEntity = userDAO.getById(id);
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("User getting: " + (userEntity != null ? userEntity.toString() : "is null"));
                }
                return userEntity;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new UserException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(UserMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UserException(UserMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public UserEntity getByUsername(UserEntity user) throws UserException {
        postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        UserEntity userEntity;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                userDAO.setConnection(connection);
                userEntity = userDAO.getByUsername(user);
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("User getting: " + (userEntity != null ? userEntity.toString() : "is null"));
                }
                return userEntity;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new UserException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(UserMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UserException(UserMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    
    @Override
    public List<UserEntity> getAll() throws UserException {
        postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        List<UserEntity> userEntityList;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();

                userDAO.setConnection(connection);
                userEntityList = userDAO.getAll();
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("User getting: " + (userEntityList != null ? userEntityList.toString() : "is null"));
                }
                return userEntityList;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new UserException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(UserMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UserException(UserMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

}
