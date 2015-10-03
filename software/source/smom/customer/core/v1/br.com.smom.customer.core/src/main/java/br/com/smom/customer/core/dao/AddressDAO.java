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
import br.com.smom.customer.api.model.entities.AddressEntity;
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
public class AddressDAO extends GenericDataBaseDAO implements IAddressDAO {

    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public int create(AddressEntity addressEntity) throws CustomerException {
        try {
            String query = "insert into address (people_id, cep, city, uf, district, street)"
                    + "values (?, ?, ?, ?, ?, ?)";
            return executeInsert(query,
                    addressEntity.getPeople_id(),
                    addressEntity.getCep(),
                    addressEntity.getCity(),
                    addressEntity.getUf(),
                    addressEntity.getDistrict(),
                    addressEntity.getStreet());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public void update(AddressEntity addressEntity) throws CustomerException {
        try {
            String query = "update address set people_id = ?, cep = ?, city = ?, uf = ?, district = ?, street = ? where id = ?";
            executeUpdate(query,
                    addressEntity.getPeople_id(),
                    addressEntity.getCep(),
                    addressEntity.getCity(),
                    addressEntity.getUf(),
                    addressEntity.getDistrict(),
                    addressEntity.getStreet(),
                    addressEntity.getId());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public void delete(AddressEntity addressEntity) throws CustomerException {
        try {
            String query = "delete from address a where a.id = ?";
            executeUpdate(query,
                    addressEntity.getId());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public AddressEntity getById(int id) throws CustomerException {
        try {
            String query = "select * from address a where a.id = ?";
            ResultSet resultSet = executeQuery(query, id);
            return setAddressEntity(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public List<AddressEntity> getByCustomerId(int customerId) throws CustomerException {
        try {
            String query = "select * from address a where a.people_id = ?";
            ResultSet resultSet = executeQuery(query, customerId);
            return fillAddressList(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    private List<AddressEntity> fillAddressList(ResultSet resultSet) throws CustomerException {
        try {
            List<AddressEntity> addressEntityList = new ArrayList<>();
            while (resultSet.next()) {
                addressEntityList.add(setAddressEntity(resultSet));
            }
            return addressEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    private AddressEntity setAddressEntity(ResultSet resultSet) throws CustomerException {
        try {
            AddressEntity addressEntityModel = new AddressEntity(
                    resultSet.getInt("id"),
                    resultSet.getInt("people_id"),
                    resultSet.getString("cep"),
                    resultSet.getString("city"),
                    resultSet.getString("uf"),
                    resultSet.getString("district"),
                    resultSet.getString("street"));
            return addressEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

}
