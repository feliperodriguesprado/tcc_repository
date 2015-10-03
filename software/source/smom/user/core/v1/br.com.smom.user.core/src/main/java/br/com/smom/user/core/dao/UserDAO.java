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
package br.com.smom.user.core.dao;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.dao.GenericDataBaseDAO;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.user.api.enums.UserMessages;
import br.com.smom.user.api.exceptions.UserException;
import br.com.smom.user.api.model.entities.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserDAO extends GenericDataBaseDAO implements IUserDAO {

    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public UserEntity create(UserEntity userEntity) throws UserException {
        try {
            String query = "insert into users (name, password, username, active)"
                    + "values (?, ?, ?, ?)";
            return getById(executeInsert(query,
                    userEntity.getName(),
                    userEntity.getPassword(),
                    userEntity.getUserName(),
                    true));
        } catch (DataSourceException e) {
            throw new UserException(e);
        }
    }

    @Override
    public void update(UserEntity user) throws UserException {
        try {
            String query = "update users set name = ?, password = ?, username = ?, active = ? where id = ?";
            executeUpdate(query,
                    user.getName(),
                    user.getPassword(),
                    user.getUserName(),
                    user.isActive(),
                    user.getId());
        } catch (DataSourceException e) {
            throw new UserException(e);
        }
    }

    @Override
    public void delete(UserEntity user) throws UserException {
        user.setActive(false);
        update(user);
    }

    @Override
    public UserEntity getById(int id) throws UserException {
        try {
            String query = "select * from users u where u.id = ?";
            ResultSet resultSet = executeQuery(query, id);
            return setUserEntity(resultSet);
        } catch (DataSourceException e) {
            throw new UserException(e);
        }
    }

    @Override
    public UserEntity getByUsername(UserEntity user) throws UserException {
        try {
            String query = "select * from users u where u.username = '?'";
            ResultSet resultSet = executeQuery(query, user.getUserName());
            return setUserEntity(resultSet);
        } catch (DataSourceException e) {
            throw new UserException(e);
        }
    }
    
    

    @Override
    public List<UserEntity> getAll() throws UserException {
        try {
            String query = "select * from users u where u.active = TRUE";
            ResultSet resultSet = executeQuery(query);
            return fillUsersList(resultSet);
        } catch (DataSourceException e) {
            throw new UserException(e);
        }
    }

    private UserEntity setUserEntity(ResultSet resultSet) throws UserException {
        try {
            UserEntity phoneEntityModel = new UserEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("username"),
                    resultSet.getBoolean("active"));
            return phoneEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UserMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new UserException(UserMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    private List<UserEntity> fillUsersList(ResultSet resultSet) throws UserException {
        try {
            List<UserEntity> userEntityList = new ArrayList<>();
            while (resultSet.next()) {
                userEntityList.add(setUserEntity(resultSet));
            }
            return userEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UserMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new UserException(UserMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

}
