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
package br.com.smom.customer.core.dao;

import br.com.smom.customer.api.model.entities.PhoneEntity;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.dao.GenericDataBaseDAO;
import br.com.smom.main.util.api.enums.UtilMessages;
import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PhoneDAO extends GenericDataBaseDAO implements IPhoneDAO{
    
    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public int create(PhoneEntity phoneEntity) throws UtilException {
        String query = "insert into phones (people_id, number)"
                + "values (?, ?)";
        return executeInsert(query,
                phoneEntity.getPeople_id(),
                phoneEntity.getNumber());
    }

    @Override
    public void update(PhoneEntity phoneEntity) throws UtilException {
        String query = "update phones set people_id = ?, number = ? where id = ?";
        executeUpdate(query,
                phoneEntity.getPeople_id(),
                phoneEntity.getNumber(),
                phoneEntity.getId());
    }

    @Override
    public void delete(PhoneEntity phoneEntity) throws UtilException {
        String query = "delete from phones p where p.id = ?";
        executeUpdate(query,
                phoneEntity.getId());
    }

    @Override
    public PhoneEntity getById(int id) throws UtilException {
        String query = "select * from phones p where p.id = ?";
        ResultSet resultSet = executeQuery(query, id);
        return setPhoneEntity(resultSet);
    }

    @Override
    public List<PhoneEntity> getByCustomerId(int customerId) throws UtilException {
        String query = "select * from phones p where p.people_id = ?";
        ResultSet resultSet = executeQuery(query, customerId);
        return fillPhoneList(resultSet);
    }
    

    private PhoneEntity setPhoneEntity(ResultSet resultSet) throws UtilException {
        try {
            PhoneEntity phoneEntityModel = new PhoneEntity(
                    resultSet.getInt("id"),
                    resultSet.getInt("people_id"),
                    resultSet.getString("number"));
            return phoneEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }
    
     private List<PhoneEntity> fillPhoneList(ResultSet resultSet) throws UtilException {
        try {
            List<PhoneEntity> addressEntityList = new ArrayList<>();
            while (resultSet.next()) {
                addressEntityList.add(setPhoneEntity(resultSet));
            }
            return addressEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

}
