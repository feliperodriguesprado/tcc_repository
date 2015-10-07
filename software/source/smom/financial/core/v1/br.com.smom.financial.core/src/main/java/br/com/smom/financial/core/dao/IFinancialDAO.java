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

import br.com.smom.financial.api.exceptions.FinancialException;
import br.com.smom.financial.api.model.entities.FinancialEntity;
import java.sql.Connection;
import java.util.List;

public interface IFinancialDAO {
    
    public void setConnection(Connection connection);

    public int create(FinancialEntity financialEntity) throws FinancialException;

    public FinancialEntity update(FinancialEntity financialEntity) throws FinancialException;

    public void delete(FinancialEntity financialEntity) throws FinancialException;

    public FinancialEntity getById(int id) throws FinancialException;

    public List<FinancialEntity> getByPeople(int id) throws FinancialException;

    public List<FinancialEntity> getByCreateDate(String startDate, String endDate) throws FinancialException;

    public List<FinancialEntity> getByDueDate(String startDate, String endDate) throws FinancialException;

}
