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

    @Inject
    private ICustomerDAO customerDAO;
    @Inject
    private IPhoneDAO phoneDAO;
    @Inject
    private IAddressDAO addressDAO;

    @Override
    public PeopleEntity create(PeopleEntity peopleEntity) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        PeopleEntity customerCreated;
        Connection connection = null;
        int generatedKey;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                phoneDAO.setConnection(connection);
                addressDAO.setConnection(connection);
                generatedKey = customerDAO.create(peopleEntity);
                customerCreated = customerDAO.getById(generatedKey);

                for (AddressEntity address : peopleEntity.getAddressList()) {
                    address.setPeopleId(generatedKey);
                    addressDAO.create(address);
                }

                for (PhoneEntity phone : peopleEntity.getPhoneList()) {
                    phone.setPeopleId(generatedKey);
                    phoneDAO.create(phone);
                }

                customerCreated.setPhoneList(phoneDAO.getByCustomerId(generatedKey));
                customerCreated.setAddressList(addressDAO.getByCustomerId(generatedKey));

                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Customer created: " + customerCreated.toString());
                }
                return customerCreated;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                if (e.getMessage().contains("uk_phones_people_id_number")) {
                    throw new CustomerException(CustomerMessages.WARN_PHONE_EXISTS);
                }
                throw new CustomerException(e);
            } catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public PeopleEntity update(PeopleEntity peopleEntity) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        PeopleEntity customerUpdated;
        Connection connection = null;
        int customerId;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerId = peopleEntity.getId();
                customerDAO.setConnection(connection);
                phoneDAO.setConnection(connection);
                addressDAO.setConnection(connection);
                customerDAO.update(peopleEntity);
                customerUpdated = customerDAO.getById(customerId);

                for (AddressEntity address : peopleEntity.getAddressList()) {
                    if (address.getId() == 0) {
                        address.setPeopleId(peopleEntity.getId());
                        address.setId(addressDAO.create(address));
                    }
                }
                for (AddressEntity addressDB : addressDAO.getByCustomerId(customerId)) {
                    boolean addressExists = false;
                    for (AddressEntity address : peopleEntity.getAddressList()) {
                        if (address.getId() == addressDB.getId()) {
                            addressExists = true;
                        }
                    }
                    if (!addressExists) {
                        addressDAO.delete(addressDB);
                    }
                }
                for (AddressEntity address : peopleEntity.getAddressList()) {
                    addressDAO.update(address);
                }

                for (PhoneEntity phone : peopleEntity.getPhoneList()) {
                    if (phone.getId() == 0) {
                        phone.setPeopleId(peopleEntity.getId());
                        phone.setId(phoneDAO.create(phone));
                    }
                }
                for (PhoneEntity phoneDB : phoneDAO.getByCustomerId(customerId)) {
                    boolean phoneExists = false;
                    for (PhoneEntity phone : peopleEntity.getPhoneList()) {
                        if (phone.getId() == phoneDB.getId()) {
                            phoneExists = true;
                        }
                    }
                    if (!phoneExists) {
                        phoneDAO.delete(phoneDB);
                    }
                }
                for (PhoneEntity phone : peopleEntity.getPhoneList()) {
                    phoneDAO.update(phone);
                }

                customerUpdated.setAddressList(addressDAO.getByCustomerId(customerId));
                customerUpdated.setPhoneList(phoneDAO.getByCustomerId(customerId));

                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Customer updated: " + customerUpdated.toString());
                }
                return customerUpdated;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                if (e.getMessage().contains("uk_phones_people_id_number")) {
                    throw new CustomerException(CustomerMessages.WARN_PHONE_EXISTS);
                }
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public void delete(PeopleEntity peopleEntity) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerDAO.delete(peopleEntity);
                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Customer deleted: " + peopleEntity.toString());
                }
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public PeopleEntity getById(int id) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        PeopleEntity customerEntity;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerEntity = customerDAO.getById(id);

                phoneDAO.setConnection(connection);
                customerEntity.setPhoneList(phoneDAO.getByCustomerId(id));

                addressDAO.setConnection(connection);
                customerEntity.setAddressList(addressDAO.getByCustomerId(id));

                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntity != null ? customerEntity.toString() : "is null"));
                }
                return customerEntity;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public List<PeopleEntity> getByName(String name) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        List<PeopleEntity> customerEntityList;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerEntityList = customerDAO.getByName(name);
                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntityList != null ? customerEntityList.toString() : "is null"));
                }
                return customerEntityList;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }
    
    @Override
    public List<PeopleEntity> getByCpfCnpj(String cpfCnpj) throws CustomerException {
         PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        List<PeopleEntity> customerEntityList;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerEntityList = customerDAO.getByCpfCnpj(cpfCnpj);
                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntityList != null ? customerEntityList.toString() : "is null"));
                }
                return customerEntityList;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public List<PeopleEntity> getAll() throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        List<PeopleEntity> customerEntityList;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();

                customerDAO.setConnection(connection);
                customerEntityList = customerDAO.getAll();
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntityList != null ? customerEntityList.toString() : "is null"));
                }
                return customerEntityList;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        List<PeopleEntity> customerEntityList;
        Connection connection = null;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                customerDAO.setConnection(connection);
                customerEntityList = customerDAO.getCreatedCustomersRanking(positions);
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("Customer getting: " + (customerEntityList != null ? customerEntityList.toString() : "is null"));
                }
                return customerEntityList;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public AddressEntity updateAddress(AddressEntity addressEntity) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        Connection connection = null;
        AddressEntity entity;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                addressDAO.setConnection(connection);
                addressDAO.update(addressEntity);
                postgreSQLService.commit(connection);

                entity = addressDAO.getById(addressEntity.getId());
                if (logService != null) {
                    logService.info("Customer getting: " + addressEntity.toString());
                }
                return entity;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public PhoneEntity updatePhone(PhoneEntity phoneEntity) throws CustomerException {

        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        Connection connection = null;
        PhoneEntity entity;

        if (postgreSQLService != null) {
            try {
                connection = postgreSQLService.getConnection();
                phoneDAO.setConnection(connection);
                phoneDAO.update(phoneEntity);
                postgreSQLService.commit(connection);

                entity = phoneDAO.getById(phoneEntity.getId());
                if (logService != null) {
                    logService.info("Customer getting: " + phoneEntity.toString());
                }
                return entity;
            } catch (DataSourceException e) {
                postgreSQLService.rollback(connection);
                throw new CustomerException(e);
            }  catch (CustomerException e) {
                postgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new CustomerException(CustomerMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

}
