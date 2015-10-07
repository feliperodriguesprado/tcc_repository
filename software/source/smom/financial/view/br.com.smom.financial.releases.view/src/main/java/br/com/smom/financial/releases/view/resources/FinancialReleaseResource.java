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
package br.com.smom.financial.releases.view.resources;

import br.com.smom.financial.api.enums.FinancialMessages;
import br.com.smom.financial.api.exceptions.FinancialException;
import br.com.smom.financial.api.model.to.FinancialReleaseListTO;
import br.com.smom.financial.api.services.Financial;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import br.com.smom.main.util.api.services.ServiceProvider;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Class that provides resources RESTful to financial releases.
 */
@Path("rest")
public class FinancialReleaseResource {

    /**
     * RESTful GET
     * http://domain/modules/financial/releases/resources/rest/list?createDateStart=2015-01-01&createDateEnd=2015-01-31
     *
     * @param createDateStart
     * @param createDateEnd
     * @return {@link CustomerListTO} converted to JSON
     */
    @Path("/list/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FinancialReleaseListTO getCustomerListCreatedRanking(
            @DefaultValue("") @QueryParam("createDateStart") String createDateStart,
            @DefaultValue("") @QueryParam("createDateEnd") String createDateEnd) {

        Financial financialService = (Financial) ServiceProvider.getBundleService(Financial.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        FinancialReleaseListTO releaseList = new FinancialReleaseListTO();

        try {
            if (logService != null) {
                logService.info(FinancialMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/list");
            }

            if (financialService != null) {
                releaseList.setReleaseList(financialService.getByCreateDate(createDateStart, createDateEnd));
                responseResource.setMessage(FinancialMessages.INFO_GET_FINANCIAL_RELEASE_LIST);
            } else {
                if (logService != null) {
                    logService.warn(FinancialMessages.WARN_UNAVAILABLE_MODULE.getMessage("Financial Service is null"));
                }

                responseResource.setMessage(FinancialMessages.WARN_UNAVAILABLE_MODULE);
            }
        } catch (FinancialException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
        }

        if (logService != null) {
            logService.info(FinancialMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/list");
        }

        releaseList.setResponseResource(responseResource);
        return releaseList;
    }

}
