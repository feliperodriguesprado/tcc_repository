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

import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.financial.api.enums.FinancialMessages;
import br.com.smom.financial.api.exceptions.FinancialException;
import br.com.smom.financial.api.model.entities.AccountEntity;
import br.com.smom.financial.api.model.entities.FinancialEntity;
import br.com.smom.financial.api.model.entities.PaymentTypeEntity;
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
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class FinancialDAO extends GenericDataBaseDAO implements IFinancialDAO {

    private Log logService = null;

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
                    financialEntity.getIsPaid(),
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
                    financialEntity.getIsPaid(),
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
            String query = "select f.*, a.id as account_id, account_description from financial_releases f where f.id = ?";
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
            String query = "select "
                    + "f.*, "
                    + "p.id as id_peoples, "
                    + "p.type as type_peoples, "
                    + "p.name as name_peoples, "
                    + "p.cpf_cnpj as cpf_cnpj_peoples, "
                    + "p.active as active_peoples, "
                    + "p.date_create as date_create_peoples, "
                    + "a.id as id_accounts, "
                    + "a.description as description_accounts, "
                    + "pt.id as id_payment_types, "
                    + "pt.description as description_payment_types "
                    + "from financial_releases f "
                    + "join peoples p on p.id = f.people_id "
                    + "join accounts a on a.id = f.account_id "
                    + "join payment_types pt on pt.id = f.payment_type_id "
                    + "where p.active = TRUE "
                    + "and cast(f.create_date as text) between ? and ?";
            ResultSet resultSet = executeQuery(query, startDate, endDate);

            return fillFinancialList(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public List<FinancialEntity> getByDueDate(String startDate, String endDate) throws FinancialException {
        try {
            String query = "select "
                    + "f.*, "
                    + "p.id as id_peoples, "
                    + "p.type as type_peoples, "
                    + "p.name as name_peoples, "
                    + "p.cpf_cnpj as cpf_cnpj_peoples, "
                    + "p.active as active_peoples, "
                    + "p.date_create as date_create_peoples,"
                    + "a.id as id_accounts, "
                    + "a.description as description_accounts, "
                    + "pt.id as id_payment_types, "
                    + "pt.description as description_payment_types "
                    + "from financial_releases f "
                    + "join peoples p on p.id = f.people_id join accounts a on a.id = f.account_id "
                    + "join payment_types pt on pt.id = f.payment_type_id "
                    + "where p.active = TRUE "
                    + "and cast(f.create_date as text) between ? and ?";
            ResultSet resultSet = executeQuery(query, startDate, endDate);
            return fillFinancialList(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    private FinancialEntity fillFinancialEntity(ResultSet resultSet) throws FinancialException {
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        try {
            FinancialEntity financialEntity = null;
            while (resultSet.next()) {
                financialEntity = setFinancialEntity(resultSet);
            }
            return financialEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private FinancialEntity setFinancialEntity(ResultSet resultSet) throws FinancialException {
        logService = (Log) ServiceProvider.getBundleService(Log.class);

        AccountEntity accountEntity;
        PaymentTypeEntity paymentTypeEntity;
        PeopleEntity customerEntity;

        try {
            FinancialEntity release = new FinancialEntity(
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

            accountEntity = new AccountEntity();
            accountEntity.setId(resultSet.getInt("id_accounts"));
            accountEntity.setDescription(resultSet.getString("description_accounts"));

            paymentTypeEntity = new PaymentTypeEntity();
            paymentTypeEntity.setId(resultSet.getInt("id_payment_types"));
            paymentTypeEntity.setDescription(resultSet.getString("description_payment_types"));

            customerEntity = new PeopleEntity();
            customerEntity.setId(resultSet.getInt("id_peoples"));
            customerEntity.setType(resultSet.getInt("type_peoples"));
            customerEntity.setName(resultSet.getString("name_peoples"));
            customerEntity.setCpfCnpj(resultSet.getString("cpf_cnpj_peoples"));
            customerEntity.setActive(resultSet.getBoolean("active_peoples"));
            customerEntity.setDateCreate(resultSet.getDate("date_create_peoples"));

            release.setAccount(accountEntity);
            release.setPaymentType(paymentTypeEntity);
            release.setPeople(customerEntity);

            return release;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private List<FinancialEntity> fillFinancialList(ResultSet resultSet) throws FinancialException {
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        try {
            List<FinancialEntity> financialEntityList = new ArrayList<>();
            while (resultSet.next()) {
                financialEntityList.add(setFinancialEntity(resultSet));
            }
            return financialEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }
}
