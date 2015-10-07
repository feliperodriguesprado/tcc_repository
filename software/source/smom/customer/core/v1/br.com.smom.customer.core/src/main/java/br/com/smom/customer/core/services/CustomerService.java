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
package br.com.smom.customer.core.services;

import br.com.smom.customer.api.enums.CustomerMessages;
import br.com.smom.customer.api.exceptions.CustomerException;
import br.com.smom.customer.api.model.entities.AddressEntity;
import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.customer.api.model.entities.PhoneEntity;
import br.com.smom.customer.api.services.Customer;
import br.com.smom.customer.core.repositories.ICustomerRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CustomerService implements Customer {

    @Inject
    private ICustomerRepository customerRepository;

    @Override
    public PeopleEntity create(PeopleEntity peopleEntity) throws CustomerException {
        try {
            return customerRepository.create(peopleEntity);

        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public PeopleEntity update(PeopleEntity peopleEntity) throws CustomerException {
        try {
            return customerRepository.update(peopleEntity);
        } catch (Exception e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }

    }

    @Override
    public void delete(PeopleEntity peopleEntity) throws CustomerException {

        try {
            customerRepository.delete(peopleEntity);
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public PeopleEntity getById(int id) throws CustomerException {
        try {
            return customerRepository.getById(id);
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<PeopleEntity> getByName(String name) throws CustomerException {
        try {
            return customerRepository.getByName(name);
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<PeopleEntity> getByCpfCnpj(String cpfCnpj) throws CustomerException {
        try {
            return customerRepository.getByCpfCnpj(cpfCnpj);
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<PeopleEntity> getAll() throws CustomerException {
        try {
            return customerRepository.getAll();
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws CustomerException {
        try {
            return customerRepository.getCreatedCustomersRanking(positions);
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public AddressEntity updateAddress(AddressEntity addressEntity) throws CustomerException {
        try {
            return customerRepository.updateAddress(addressEntity);
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public PhoneEntity updatePhone(PhoneEntity phoneEntity) throws CustomerException {
        try {
            return customerRepository.updatePhone(phoneEntity);
        } catch (CustomerException e) {
            throw new CustomerException(CustomerMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

}
