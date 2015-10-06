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

import br.com.smom.customer.api.enums.CustomerMessages;
import br.com.smom.customer.api.exceptions.CustomerException;
import br.com.smom.customer.api.model.entities.PhoneEntity;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.dao.GenericDataBaseDAO;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PhoneDAO extends GenericDataBaseDAO implements IPhoneDAO {

    @Override
    public int create(PhoneEntity phoneEntity) throws CustomerException {
        try {
            String query = "insert into phones (people_id, number)"
                    + "values (?, ?)";
            return executeInsert(query,
                    phoneEntity.getPeopleId(),
                    phoneEntity.getNumber());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public void update(PhoneEntity phoneEntity) throws CustomerException {
        try {
            String query = "update phones set people_id = ?, number = ? where id = ?";
            executeUpdate(query,
                    phoneEntity.getPeopleId(),
                    phoneEntity.getNumber(),
                    phoneEntity.getId());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public void delete(PhoneEntity phoneEntity) throws CustomerException {
        try {
            String query = "delete from phones p where p.id = ?";
            executeUpdate(query,
                    phoneEntity.getId());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public PhoneEntity getById(int id) throws CustomerException {
        try {
            String query = "select * from phones p where p.id = ?";
            ResultSet resultSet = executeQuery(query, id);
            return fillPhoneEntity(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public List<PhoneEntity> getByCustomerId(int customerId) throws CustomerException {
        try {
            String query = "select * from phones p where p.people_id = ?";
            ResultSet resultSet = executeQuery(query, customerId);
            return fillPhoneList(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    private PhoneEntity fillPhoneEntity(ResultSet resultSet) throws CustomerException {

        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        try {
            PhoneEntity phoneEntity = null;
            while (resultSet.next()) {
                phoneEntity = setPhoneEntity(resultSet);
            }
            return phoneEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private PhoneEntity setPhoneEntity(ResultSet resultSet) throws CustomerException {

        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        try {
            PhoneEntity phoneEntityModel = new PhoneEntity(
                    resultSet.getInt("id"),
                    resultSet.getInt("people_id"),
                    resultSet.getString("number"));
            return phoneEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private List<PhoneEntity> fillPhoneList(ResultSet resultSet) throws CustomerException {

        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        try {
            List<PhoneEntity> addressEntityList = new ArrayList<>();
            while (resultSet.next()) {
                addressEntityList.add(setPhoneEntity(resultSet));
            }
            return addressEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

}
