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
package br.com.smom.financial.core.repositories;

import br.com.smom.financial.api.enums.FinancialMessages;
import br.com.smom.financial.api.exceptions.FinancialException;
import br.com.smom.financial.api.model.entities.FinancialEntity;
import br.com.smom.financial.core.dao.IFinancialDAO;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.datasource.api.services.PostgreSQL;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.Connection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class FinancialRepository implements IFinancialRepository{
    
    private final PostgreSQL posgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);
    
    @Inject
    private IFinancialDAO financialDAO;

    @Override
    public FinancialEntity create(FinancialEntity financialEntity) throws FinancialException {
        FinancialEntity financialCreated;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                financialDAO.setConnection(connection);
                financialCreated = financialDAO.create(financialEntity);

                posgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Financial created: " + financialCreated.toString());
                }
                return financialCreated;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new FinancialException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public FinancialEntity update(FinancialEntity financialEntity) throws FinancialException {
        FinancialEntity financialUpdated;
        Connection connection = null;
        int customerId;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                customerId = financialEntity.getId();
                financialDAO.setConnection(connection);
                financialDAO.update(financialEntity);
                financialUpdated = financialDAO.getById(customerId);

                posgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Financial updated: " + financialUpdated.toString());
                }
                return financialUpdated;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new FinancialException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public void delete(FinancialEntity financialEntity) throws FinancialException {
         Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                financialDAO.setConnection(connection);
                financialDAO.delete(financialEntity);
                posgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Financial deleted: " + financialEntity.toString());
                }
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new FinancialException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public FinancialEntity getById(int id) throws FinancialException {
        FinancialEntity financialEntity;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                financialDAO.setConnection(connection);
                financialEntity = financialDAO.getById(id);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Financial getting: " + (financialEntity != null ? financialEntity.toString() : "is null"));
                }
                return financialEntity;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new FinancialException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public List<FinancialEntity> getByPeople(int id) throws FinancialException {
        List<FinancialEntity> financialEntityList;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();

                financialDAO.setConnection(connection);
                financialEntityList = financialDAO.getByPeople(id);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Financial getting: " + (financialEntityList != null ? financialEntityList.toString() : "is null"));
                }
                return financialEntityList;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new FinancialException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public List<FinancialEntity> getByCreateDate(String startDate, String endDate) throws FinancialException {
        List<FinancialEntity> financialEntityList;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();

                financialDAO.setConnection(connection);
                financialEntityList = financialDAO.getByCreateDate(startDate, endDate);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Financial getting: " + (financialEntityList != null ? financialEntityList.toString() : "is null"));
                }
                return financialEntityList;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new FinancialException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public List<FinancialEntity> getByDueDate(String startDate, String endDate) throws FinancialException {
        List<FinancialEntity> financialEntityList;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();

                financialDAO.setConnection(connection);
                financialEntityList = financialDAO.getByDueDate(startDate, endDate);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Financial getting: " + (financialEntityList != null ? financialEntityList.toString() : "is null"));
                }
                return financialEntityList;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new FinancialException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }
    
}
