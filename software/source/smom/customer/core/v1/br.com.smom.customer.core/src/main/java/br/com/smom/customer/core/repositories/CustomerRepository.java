/*
 * Copyright 2015 Smom - Software Module Management.
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
package br.com.smom.customer.core.repositories;

import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.customer.core.dao.ICustomerDAO;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.services.PostgreSQL;
import br.com.smom.main.util.api.enums.UtilMessages;
import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.model.entities.ViewModuleEntity;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.Connection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CustomerRepository implements ICustomerRepository{
    
    private final PostgreSQL posgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);
    @Inject
    private ICustomerDAO customerDAO;    

    @Override
    public PeopleEntity create(PeopleEntity peopleEntity) throws UtilException {
        PeopleEntity customerCreated;
        Connection connection;

        if (posgreSQLService != null) {
            connection = posgreSQLService.getConnection();
            try {
                customerDAO.setConnection(connection);
                int generatedKey = customerDAO.create(peopleEntity);
                customerCreated = customerDAO.getById(generatedKey);
                posgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("View module created: " + (customerCreated != null ? customerCreated.toString() : "is null"));
                }
                return customerCreated;
            } catch (UtilException e) {
                posgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(UtilMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UtilException(UtilMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public PeopleEntity update(PeopleEntity peopleEntity) throws UtilException {
        PeopleEntity customerUpdated;
        Connection connection;

        if (posgreSQLService != null) {
            connection = posgreSQLService.getConnection();
            try {
                customerDAO.setConnection(connection);
                customerDAO.update(peopleEntity);
                customerUpdated = customerDAO.getById(peopleEntity.getId());
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module updated: " + (customerUpdated != null ? customerUpdated.toString() : "is null"));
                }
                return customerUpdated;
            } catch (UtilException e) {
                posgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(UtilMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UtilException(UtilMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public void delete(PeopleEntity peopleEntity) throws UtilException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PeopleEntity getById(int id) throws UtilException {
        PeopleEntity customerEntity;
        Connection connection;

        if (posgreSQLService != null) {
            connection = posgreSQLService.getConnection();
            try {
                customerDAO.setConnection(connection);
                customerEntity = customerDAO.getById(id);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module getting: " + (customerEntity != null ? customerEntity.toString() : "is null"));
                }
                return customerEntity;
            } catch (UtilException e) {
                posgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(UtilMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UtilException(UtilMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public List<PeopleEntity> getByName(String name) throws UtilException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PeopleEntity> getAll() throws UtilException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws UtilException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
