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
import br.com.smom.financial.api.model.entities.AccountEntity;
import java.sql.Connection;
import java.util.List;

public interface IAccountDAO {

    public void setConnection(Connection connection);

    public int create(AccountEntity accountEntity) throws FinancialException;

    public void update(AccountEntity accountEntity) throws FinancialException;

    public void delete(AccountEntity accountEntity) throws FinancialException;

    public AccountEntity getById(int id) throws FinancialException;

    public List<AccountEntity> getAll() throws FinancialException;

}
