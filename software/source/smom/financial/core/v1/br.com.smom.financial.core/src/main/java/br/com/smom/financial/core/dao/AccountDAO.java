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
import br.com.smom.financial.api.model.entities.AccountEntity;
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
public class AccountDAO extends GenericDataBaseDAO implements IAccountDAO {

    private Log logService = null;

    @Override
    public int create(AccountEntity accountEntity) throws FinancialException {
        try {
            String query = "insert into accounts (description)"
                    + "values (?)";
            return executeInsert(query,
                    accountEntity.getDescription());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public void update(AccountEntity accountEntity) throws FinancialException {
        try {
            String query = "update accounts set description = ? where id = ?";
            executeUpdate(query,
                    accountEntity.getDescription(),
                    accountEntity.getId());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public void delete(AccountEntity accountEntity) throws FinancialException {
        try {
            String query = "delete from accounts p where p.id = ?";
            executeUpdate(query,
                    accountEntity.getId());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public AccountEntity getById(int id) throws FinancialException {
        try {
            String query = "select * from accounts a where a.id = ?";
            ResultSet resultSet = executeQuery(query, id);
            return fillAccountEntity(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public List<AccountEntity> getAll() throws FinancialException {
        try {
            String query = "select * from accounts";
            ResultSet resultSet = executeQuery(query);
            return fillAccountList(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    private AccountEntity fillAccountEntity(ResultSet resultSet) throws FinancialException {
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        try {
            AccountEntity accountEntity = null;
            while (resultSet.next()) {
                accountEntity = setAccountEntity(resultSet);
            }
            return accountEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private AccountEntity setAccountEntity(ResultSet resultSet) throws FinancialException {
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        try {
            AccountEntity accountEntityModel = new AccountEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("description"));
            return accountEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private List<AccountEntity> fillAccountList(ResultSet resultSet) throws FinancialException {
        logService = (Log) ServiceProvider.getBundleService(Log.class);
        try {
            List<AccountEntity> accountEntityList = new ArrayList<>();
            while (resultSet.next()) {
                accountEntityList.add(setAccountEntity(resultSet));
            }
            return accountEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }
}
