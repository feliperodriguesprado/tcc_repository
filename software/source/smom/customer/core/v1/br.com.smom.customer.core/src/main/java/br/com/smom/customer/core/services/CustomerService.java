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

import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.customer.api.services.Customer;
import br.com.smom.main.util.api.exceptions.UtilException;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class CustomerService implements Customer {

    @Override
    public PeopleEntity create(PeopleEntity peopleEntity) throws UtilException {
        return null;
    }

    @Override
    public PeopleEntity update(PeopleEntity peopleEntity) throws UtilException {
        return null;
    }

    @Override
    public void delete(PeopleEntity peopleEntity) throws UtilException {
        
    }

    @Override
    public PeopleEntity getById(int id) throws UtilException {
        return null;
    }

    @Override
    public List<PeopleEntity> getByName(String name) throws UtilException {
        return null;
    }

    @Override
    public List<PeopleEntity> getAll() throws UtilException {
        return null;
    }

    @Override
    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws UtilException {
        return null;
    }

   
}
