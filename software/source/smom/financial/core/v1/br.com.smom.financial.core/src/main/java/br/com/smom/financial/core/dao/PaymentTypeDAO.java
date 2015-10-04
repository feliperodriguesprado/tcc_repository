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
import br.com.smom.financial.api.model.entities.PaymentTypeEntity;
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
public class PaymentTypeDAO extends GenericDataBaseDAO implements IPaymentTypeDAO {

    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public int create(PaymentTypeEntity financialEntity) throws FinancialException {
        try {
            String query = "insert into payment_types (description)"
                    + "values (?)";
            return executeInsert(query,
                    financialEntity.getDescription());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public void update(PaymentTypeEntity financialEntity) throws FinancialException {
        try {
            String query = "update payment_types set description = ? where id = ?";
            executeUpdate(query,
                    financialEntity.getDescription(),
                    financialEntity.getId());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public void delete(PaymentTypeEntity financialEntity) throws FinancialException {
        try {
            String query = "delete from payment_types p where p.id = ?";
            executeUpdate(query,
                    financialEntity.getId());
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public PaymentTypeEntity getById(int id) throws FinancialException {
        try {
            String query = "select * from payment_types p where p.id = ?";
            ResultSet resultSet = executeQuery(query, id);
            return fillPaymentTypeEntity(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    @Override
    public List<PaymentTypeEntity> getAll() throws FinancialException {
        try {
            String query = "select * from payment_types";
            ResultSet resultSet = executeQuery(query);
            return fillPaymentTypeList(resultSet);
        } catch (DataSourceException e) {
            throw new FinancialException(e);
        }
    }

    private PaymentTypeEntity fillPaymentTypeEntity(ResultSet resultSet) throws FinancialException {
        try {
            PaymentTypeEntity paymentTypeEntity = null;
            while (resultSet.next()) {
                paymentTypeEntity = setPaymentTypeEntity(resultSet);
            }
            return paymentTypeEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private PaymentTypeEntity setPaymentTypeEntity(ResultSet resultSet) throws FinancialException {
        try {
            PaymentTypeEntity paymentEntityModel = new PaymentTypeEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("description"));
            return paymentEntityModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    private List<PaymentTypeEntity> fillPaymentTypeList(ResultSet resultSet) throws FinancialException {
        try {
            List<PaymentTypeEntity> addressEntityList = new ArrayList<>();
            while (resultSet.next()) {
                addressEntityList.add(setPaymentTypeEntity(resultSet));
            }
            return addressEntityList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.toString(), e);
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }
}
