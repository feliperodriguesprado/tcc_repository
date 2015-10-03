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

import br.com.smom.customer.api.model.entities.PeopleEntity;
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
public class CustomerDAO extends GenericDataBaseDAO implements ICustomerDAO {

    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public int create(PeopleEntity peopleEntity) throws UtilException {

        String query = "insert into peoples (type, name, cpf_cnpj, active, date_create) "
                + "values (?, ?, ?, ?, ?, ?)";
        return executeInsert(query,
                peopleEntity.getType(),
                peopleEntity.getName(),
                peopleEntity.getCpf_cnpj(),
                peopleEntity.isActive(),
                peopleEntity.getDate_create());
    }

    @Override
    public void update(PeopleEntity peopleEntity) throws UtilException {
        String query = "update peoples set type = ?, name = ?, cpf_cnpj = ?, active = ?, date_create = ? where id = ?";
        executeUpdate(query,
                peopleEntity.getType(),
                peopleEntity.getName(),
                peopleEntity.getCpf_cnpj(),
                peopleEntity.isActive(),
                peopleEntity.getDate_create(),
                peopleEntity.getId());
    }

    @Override
    public void delete(PeopleEntity peopleEntity) throws UtilException {
        String query = "update peoples set active = FALSE where id = ?";
        executeUpdate(query,
                peopleEntity.getId());
    }

    @Override
    public PeopleEntity getById(int id) throws UtilException {
        String query = "select * from peoples p where p.id = ? and p.active = TRUE";
        ResultSet resultSet = executeQuery(query, id);
        return fillCustomerEntity(resultSet);
    }

    @Override
    public List<PeopleEntity> getByName(String name) throws UtilException {
        String query = "select * from peoples p where p.id = ? and p.active = TRUE";
        ResultSet resultSet = executeQuery(query, name);
        return fillCustomerList(resultSet);
    }

    @Override
    public List<PeopleEntity> getAll() throws UtilException {
        String query = "select * from peoples where p.active = TRUE";
        ResultSet resultSet = executeQuery(query);
        return fillCustomerList(resultSet);
    }

    @Override
    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws UtilException {
        String query = "select * from peoples p where p.active = TRUE order by p.date_create limit ?";
        ResultSet resultSet = executeQuery(query, positions);
        return fillCustomerList(resultSet);

    }

    private PeopleEntity fillCustomerEntity(ResultSet resultSet) throws UtilException {
        try {
            PeopleEntity peopleEntity = null;
            while (resultSet.next()) {
                peopleEntity = setCustomerEntity(resultSet);
            }
            return peopleEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private PeopleEntity setCustomerEntity(ResultSet resultSet) throws UtilException {
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
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private List<PeopleEntity> fillCustomerList(ResultSet resultSet) throws UtilException {
        try {
            List<PeopleEntity> peopleEntityList = new ArrayList<>();
            while (resultSet.next()) {
                peopleEntityList.add(setCustomerEntity(resultSet));
            }
            return peopleEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

}
