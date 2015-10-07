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
package br.com.smom.financial.core.services;

import br.com.smom.financial.api.enums.FinancialMessages;
import br.com.smom.financial.api.exceptions.FinancialException;
import br.com.smom.financial.api.model.entities.AccountEntity;
import br.com.smom.financial.api.model.entities.FinancialEntity;
import br.com.smom.financial.api.model.entities.PaymentTypeEntity;
import br.com.smom.financial.api.services.Financial;
import br.com.smom.financial.core.repositories.IFinancialRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FinancialService implements Financial {

    @Inject
    private IFinancialRepository financialRepository;

    @Override
    public FinancialEntity create(FinancialEntity financialEntity) throws FinancialException {
        try {
            return financialRepository.create(financialEntity);

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public FinancialEntity update(FinancialEntity financialEntity) throws FinancialException {
        try {
            return financialRepository.update(financialEntity);

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public void delete(FinancialEntity financialEntity) throws FinancialException {
        try {
            financialRepository.delete(financialEntity);

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public FinancialEntity getById(int id) throws FinancialException {
        try {
            return financialRepository.getById(id);

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<FinancialEntity> getByPeople(int id) throws FinancialException {
        try {
            return financialRepository.getByPeople(id);

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<FinancialEntity> getByCreateDate(String startDate, String endDate) throws FinancialException {
        try {
            return financialRepository.getByCreateDate(startDate, endDate);

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<FinancialEntity> getByDueDate(String startDate, String endDate) throws FinancialException {
        try {
            return financialRepository.getByDueDate(startDate, endDate);

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<AccountEntity> getAllAccounts() throws FinancialException {
        try {
            return financialRepository.getAllAccounts();

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

    @Override
    public List<PaymentTypeEntity> getAllPaymentTypes() throws FinancialException {
        try {
            return financialRepository.getAllPaymentTypes();

        } catch (FinancialException e) {
            throw new FinancialException(FinancialMessages.ERROR_PERFORM_OPERATION_SERVER, e);
        }
    }

}
