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
import br.com.smom.main.util.api.exceptions.UtilException;
import java.util.List;
//teste
public interface ICustomerRepository {
    
    public PeopleEntity create(PeopleEntity peopleEntity) throws UtilException;

    public PeopleEntity update(PeopleEntity peopleEntity) throws UtilException;

    public void delete(PeopleEntity peopleEntity) throws UtilException;

    public PeopleEntity getById(int id) throws UtilException;

    public List<PeopleEntity> getByName(String name) throws UtilException;

    public List<PeopleEntity> getAll() throws UtilException;

    public List<PeopleEntity> getCreatedCustomersRanking(int positions) throws UtilException;
    
}
