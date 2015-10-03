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
import br.com.smom.customer.api.model.entities.PeopleEntity;
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
public class CustomerDAO extends GenericDataBaseDAO implements ICustomerDAO {

    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public int create(PeopleEntity peopleEntity) throws CustomerException {

        try {
            String query = "insert into peoples (type, name, cpf_cnpj, active, date_create) "
                    + "values (?, ?, ?, ?, ?, ?)";
            return executeInsert(query,
                    peopleEntity.getType(),
                    peopleEntity.getName(),
                    peopleEntity.getCpfCnpj(),
                    peopleEntity.isActive(),
                    peopleEntity.getDateCreate());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public void update(PeopleEntity peopleEntity) throws CustomerException {
        try {
            String query = "update peoples set type = ?, name = ?, cpf_cnpj = ?, active = ?, date_create = ? where id = ?";
            executeUpdate(query,
                    peopleEntity.getType(),
                    peopleEntity.getName(),
                    peopleEntity.getCpfCnpj(),
                    peopleEntity.isActive(),
                    peopleEntity.getDateCreate(),
                    peopleEntity.getId());
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public void delete(PeopleEntity peopleEntity) throws CustomerException {
        peopleEntity.setActive(false);
        update(peopleEntity);
    }

    @Override
    public PeopleEntity getById(int id) throws CustomerException {
        try {
            String query = "select * from peoples p where p.id = ? and p.active = TRUE";
            ResultSet resultSet = executeQuery(query, id);
            return fillCustomerEntity(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public List<PeopleEntity> getByName(String name) throws CustomerException {
        try {
            String query = "select * from peoples p where p.name like '%?%' and p.active = TRUE";
            ResultSet resultSet = executeQuery(query, name);
            return fillCustomerList(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public List<PeopleEntity> getAll() throws CustomerException {
        try {
            String query = "select * from peoples where p.active = TRUE";
            ResultSet resultSet = executeQuery(query);
            return fillCustomerList(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    @Override
    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws CustomerException {
        try {
            String query = "select * from peoples p where p.active = TRUE order by p.date_create limit ?";
            ResultSet resultSet = executeQuery(query, positions);
            return fillCustomerList(resultSet);
        } catch (DataSourceException e) {
            throw new CustomerException(e);
        }
    }

    private PeopleEntity fillCustomerEntity(ResultSet resultSet) throws CustomerException {
        try {
            PeopleEntity peopleEntity = null;
            while (resultSet.next()) {
                peopleEntity = setCustomerEntity(resultSet);
            }
            return peopleEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    private PeopleEntity setCustomerEntity(ResultSet resultSet) throws CustomerException {
        try {
            PeopleEntity peopleEntityModel = new PeopleEntity(
                    resultSet.getInt("id"),
                    resultSet.getInt("type"),
                    resultSet.getString("name"),
                    resultSet.getString("cpf_cnpf"),
                    resultSet.getBoolean("active"),
                    resultSet.getDate("date_create"));
            return peopleEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    private List<PeopleEntity> fillCustomerList(ResultSet resultSet) throws CustomerException {
        try {
            List<PeopleEntity> peopleEntityList = new ArrayList<>();
            while (resultSet.next()) {
                peopleEntityList.add(setCustomerEntity(resultSet));
            }
            return peopleEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

}
