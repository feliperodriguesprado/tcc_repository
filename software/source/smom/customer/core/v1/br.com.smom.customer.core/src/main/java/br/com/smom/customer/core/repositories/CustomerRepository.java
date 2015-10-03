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
package br.com.smom.customer.core.repositories;

import br.com.smom.customer.api.enums.CustomerMessages;
import br.com.smom.customer.api.exceptions.CustomerException;
import br.com.smom.customer.api.model.entities.AddressEntity;
import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.customer.api.model.entities.PhoneEntity;
import br.com.smom.customer.core.dao.IAddressDAO;
import br.com.smom.customer.core.dao.ICustomerDAO;
import br.com.smom.customer.core.dao.IPhoneDAO;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.datasource.api.services.PostgreSQL;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.Connection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CustomerRepository implements ICustomerRepository {

    private final PostgreSQL posgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);
    @Inject
    private ICustomerDAO customerDAO;
    @Inject
    private IPhoneDAO phoneDAO;
    @Inject
    private IAddressDAO addressDAO;

    @Override
    public PeopleEntity create(PeopleEntity peopleEntity) throws CustomerException {
        PeopleEntity customerCreated;
        Connection connection = null;
        int generatedKey;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                phoneDAO.setConnection(connection);
                addressDAO.setConnection(connection);
                generatedKey = customerDAO.create(peopleEntity);
                customerCreated = customerDAO.getById(generatedKey);

                for (AddressEntity address : peopleEntity.getAddressList()) {
                    addressDAO.create(address);
                }

                for (PhoneEntity phone : peopleEntity.getPhoneList()) {
                    phoneDAO.create(phone);
                }

                posgreSQLService.commit(connection);

                customerCreated.setPhoneList(phoneDAO.getByCustomerId(generatedKey));
                customerCreated.setAddressList(addressDAO.getByCustomerId(generatedKey));

                if (logService != null) {
                    logService.info("Customer created: " + customerCreated.toString());
                }
                return customerCreated;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public PeopleEntity update(PeopleEntity peopleEntity) throws CustomerException {
        PeopleEntity customerUpdated;
        Connection connection = null;
        int customerId;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                customerId = peopleEntity.getId();
                customerDAO.setConnection(connection);
                phoneDAO.setConnection(connection);
                addressDAO.setConnection(connection);
                customerDAO.update(peopleEntity);
                customerUpdated = customerDAO.getById(customerId);

                for (AddressEntity address : peopleEntity.getAddressList()) {
                    addressDAO.update(address);
                }

                for (PhoneEntity phone : peopleEntity.getPhoneList()) {
                    phoneDAO.update(phone);
                }

                posgreSQLService.commit(connection);

                customerUpdated.setAddressList(addressDAO.getByCustomerId(customerId));
                customerUpdated.setPhoneList(phoneDAO.getByCustomerId(customerId));

                if (logService != null) {
                    logService.info("Customer updated: " + customerUpdated.toString());
                }
                return customerUpdated;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public void delete(PeopleEntity peopleEntity) throws CustomerException {
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerDAO.delete(peopleEntity);
                posgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Customer deleted: " + peopleEntity.toString());
                }
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public PeopleEntity getById(int id) throws CustomerException {
        PeopleEntity customerEntity;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerEntity = customerDAO.getById(id);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntity != null ? customerEntity.toString() : "is null"));
                }
                return customerEntity;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public List<PeopleEntity> getByName(String name) throws CustomerException {
        List<PeopleEntity> customerEntityList;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerEntityList = customerDAO.getByName(name);
                posgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntityList != null ? customerEntityList.toString() : "is null"));
                }
                return customerEntityList;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public List<PeopleEntity> getAll() throws CustomerException {
        List<PeopleEntity> customerEntityList;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();

                customerDAO.setConnection(connection);
                customerEntityList = customerDAO.getAll();
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntityList != null ? customerEntityList.toString() : "is null"));
                }
                return customerEntityList;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }

    @Override
    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws CustomerException {
        List<PeopleEntity> customerEntityList;
        Connection connection = null;

        if (posgreSQLService != null) {
            try {
                connection = posgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerEntityList = customerDAO.getAll();
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntityList != null ? customerEntityList.toString() : "is null"));
                }
                return customerEntityList;
            } catch (DataSourceException e) {
                posgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER);
        }
    }
}
