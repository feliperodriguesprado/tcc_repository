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

import br.com.smom.customer.api.exceptions.CustomerException;
import br.com.smom.customer.api.model.entities.AddressEntity;
import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.customer.api.model.entities.PhoneEntity;
import java.util.List;

public interface ICustomerRepository {
    
    public PeopleEntity create(PeopleEntity peopleEntity) throws CustomerException;

    public PeopleEntity update(PeopleEntity peopleEntity) throws CustomerException;

    public void delete(PeopleEntity peopleEntity) throws CustomerException;

    public PeopleEntity getById(int id) throws CustomerException;

    public List<PeopleEntity> getByName(String name) throws CustomerException;

    public List<PeopleEntity> getAll() throws CustomerException;

    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws CustomerException;
    
    public AddressEntity updateAddress(AddressEntity addressEntity) throws CustomerException;
    
    public PhoneEntity updatePhone(PhoneEntity addressEntity) throws CustomerException;
    
}
