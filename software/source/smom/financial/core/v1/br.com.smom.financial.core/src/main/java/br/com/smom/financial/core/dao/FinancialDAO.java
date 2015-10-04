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
package br.com.smom.financial.core.dao;

import br.com.smom.financial.api.enums.FinancialMessages;
import br.com.smom.financial.api.exceptions.FinancialException;
import br.com.smom.financial.api.model.entities.FinancialEntity;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.dao.GenericDataBaseDAO;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinancialDAO extends GenericDataBaseDAO implements IFinancialDAO{
    
    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public FinancialEntity create(FinancialEntity financialEntity) throws FinancialException {
        try {
            String query = "insert into financial_releases (type, create_date, due_date, payment_date, is_paid, description, value) "
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            return getById(executeInsert(query,
                    financialEntity.getType(),
                    new Timestamp(new Date(System.currentTimeMillis()).getTime()),
                    financialEntity.getDueDate(),
                    financialEntity.getPaymentDate(),
                    financialEntity.isPaid(),
                    financialEntity.getDescription(),
                    financialEntity.getValue()));
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public FinancialEntity update(FinancialEntity financialEntity) throws FinancialException {
        try {
            String query = "update financial_releases set type = ?, create_date = ?, due_date = ?, payment_date = ?, is_paid = ?, description = ?, value = ? where id = ?";
            executeUpdate(query,
                    financialEntity.getType(),
                    financialEntity.getCreateDate(),
                    financialEntity.getDueDate(),
                    financialEntity.getPaymentDate(),
                    financialEntity.isPaid(),
                    financialEntity.getDescription(),
                    financialEntity.getValue(),
                    financialEntity.getId());
            return getById(financialEntity.getId());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public void delete(FinancialEntity financialEntity) throws FinancialException {
        try {
            String query = "delete from financial_releases f where f.id = ?";
            executeUpdate(query,
                    financialEntity.getId());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public FinancialEntity getById(int id) throws FinancialException {
        try {
            String query = "select * from financial_releases f where f.id = ?";
            ResultSet resultSet = executeQuery(query, id);
            return fillFinancialEntity(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public List<FinancialEntity> getByPeople(int id) throws FinancialException {
        try {
            String query = "select * from peoples p where p.id = ? and p.active = TRUE";
            ResultSet resultSet = executeQuery(query, id);
            return fillFinancialList(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public List<FinancialEntity> getByCreateDate(String startDate, String endDate) throws FinancialException {
        try {
            String query = "select * from financial_releases f join peoples p on p.id = f.people_id where p.active = TRUE and f.create_date between ? and ?";
            ResultSet resultSet = executeQuery(query, startDate, endDate);
            return fillFinancialList(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public List<FinancialEntity> getByDueDate(String startDate, String endDate) throws FinancialException {
        try {
            String query = "select * from financial_releases f join peoples p on p.id = f.people_id where p.active = TRUE and f.due_date between ? and ?";
            ResultSet resultSet = executeQuery(query, startDate, endDate);
            return fillFinancialList(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    private FinancialEntity fillFinancialEntity(ResultSet resultSet) throws FinancialException {
        try {
            FinancialEntity financialEntity = null;
            while (resultSet.next()) {
                financialEntity = setFinancialEntity(resultSet);
            }
            return financialEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    private FinancialEntity setFinancialEntity(ResultSet resultSet) throws FinancialException {
        try {
            FinancialEntity peopleEntityModel = new FinancialEntity(
                    resultSet.getInt("id"),
                    resultSet.getInt("type"),
                    resultSet.getInt("account_id"),
                    resultSet.getInt("people_id"),
                    resultSet.getInt("payment_type_id"),
                    resultSet.getDate("create_date"),
                    resultSet.getDate("due_date"),
                    resultSet.getDate("payment_date"),
                    resultSet.getBoolean("is_paid"),
                    resultSet.getString("description"),
                    resultSet.getDouble("value"));
            return peopleEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    private List<FinancialEntity> fillFinancialList(ResultSet resultSet) throws FinancialException {
        try {
            List<FinancialEntity> financialEntityList = new ArrayList<>();
            while (resultSet.next()) {
                financialEntityList.add(setFinancialEntity(resultSet));
            }
            return financialEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }
}
