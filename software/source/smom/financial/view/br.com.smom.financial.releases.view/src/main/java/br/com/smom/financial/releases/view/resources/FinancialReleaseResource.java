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

import br.com.smom.customer.api.exceptions.CustomerException;
import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.customer.api.model.to.CustomerTO;
import br.com.smom.customer.api.services.Customer;
import br.com.smom.financial.api.enums.FinancialMessages;
import br.com.smom.financial.api.exceptions.FinancialException;
import br.com.smom.financial.api.model.to.FinancialReleaseListTO;
import br.com.smom.financial.api.model.to.FinancialReleaseTO;
import br.com.smom.financial.api.services.Financial;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

/**
 * Class that provides resources RESTful to financial releases.
 */
@Path("rest")
public class FinancialReleaseResource {

    /**
     * RESTful GET
     * http://domain/modules/financial/releases/resources/rest/create
     *
     * @param release
     * @return {@link FinancialReleaseTO} converted to JSON
     */
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FinancialReleaseTO postReleaseCreate(JAXBElement<FinancialReleaseTO> release) {

        Financial financialService = (Financial) ServiceProvider.getBundleService(Financial.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        FinancialReleaseTO releaseTO = release.getValue();

        try {
            if (logService != null) {
                logService.info(FinancialMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/create");
            }

            if (financialService != null) {
                releaseTO.setRelease(financialService.create(releaseTO.getRelease()));
                responseResource.setMessage(FinancialMessages.INFO_CREATE_FINANCIAL_RELEASE);
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
            logService.info(FinancialMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/create");
        }

        releaseTO.setResponseResource(responseResource);
        return releaseTO;
    }

    /**
     * RESTful GET
     * http://domain/modules/financial/releases/resources/rest/update
     *
     * @param release
     * @return {@link FinancialReleaseTO} converted to JSON
     */
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FinancialReleaseTO postReleaseUpdate(JAXBElement<FinancialReleaseTO> release) {

        Financial financialService = (Financial) ServiceProvider.getBundleService(Financial.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        FinancialReleaseTO releaseTO = release.getValue();

        try {
            if (logService != null) {
                logService.info(FinancialMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/update");
            }

            if (financialService != null) {
                releaseTO.setRelease(financialService.update(releaseTO.getRelease()));
                responseResource.setMessage(FinancialMessages.INFO_UPDATE_FINANCIAL_RELEASE);
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
            logService.info(FinancialMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/update");
        }

        releaseTO.setResponseResource(responseResource);
        return releaseTO;
    }

    /**
     * RESTful GET
     * http://domain/modules/financial/releases/resources/rest/list{releaseId}
     *
     * @param releaseId
     * @return {@link FinancialReleaseTO} converted to JSON
     */
    @Path("/list/{releaseId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FinancialReleaseTO getReleaseListByCreateDate(@PathParam("releaseId") String releaseId) {

        Financial financialService = (Financial) ServiceProvider.getBundleService(Financial.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        FinancialReleaseTO release = new FinancialReleaseTO();

        try {
            if (logService != null) {
                logService.info(FinancialMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/list/{releaseId}");
            }

            if (financialService != null) {
                release.setRelease(financialService.getById(Integer.parseInt(releaseId)));
                responseResource.setMessage(FinancialMessages.INFO_GET_FINANCIAL_RELEASE);
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
            logService.info(FinancialMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/list/{releaseId}");
        }

        release.setResponseResource(responseResource);
        return release;
    }

    /**
     * RESTful GET
     * http://domain/modules/financial/releases/resources/rest/list?createDateStart=2015-01-01&createDateEnd=2015-01-31
     *
     * @param createDateStart
     * @param createDateEnd
     * @return {@link FinancialReleaseListTO} converted to JSON
     */
    @Path("/list/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FinancialReleaseListTO getReleaseListByCreateDate(
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

    /**
     * RESTful GET
     * http://domain/modules/financial/releases/resources/rest/customer/?customerName=cliente
     *
     * @param customerName
     * @return {@link CustomerTO} converted to JSON
     */
    @Path("/customer")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerTO getCustomerListById(@DefaultValue("") @QueryParam("customerName") String customerName) {

        Customer customerService = (Customer) ServiceProvider.getBundleService(Customer.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        List<PeopleEntity> customerList;
        CustomerTO customerTO = new CustomerTO();

        try {
            if (logService != null) {
                logService.info(FinancialMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/customer/{customerId}");
            }

            if (customerService != null) {

                customerList = customerService.getByName(customerName);

                if (!customerList.isEmpty()) {
                    customerTO.setCustomer(customerList.get(0));
                    responseResource.setMessage(FinancialMessages.INFO_GET_CUSTOMER);
                } else {
                    customerTO.setCustomer(null);
                    responseResource.setMessage(FinancialMessages.WARN_CUSTOMER_NOT_FOUND);
                }

            } else {
                if (logService != null) {
                    logService.warn(FinancialMessages.WARN_UNAVAILABLE_MODULE.getMessage("Customer Service is null"));
                }

                responseResource.setMessage(FinancialMessages.WARN_UNAVAILABLE_MODULE);
            }
        } catch (CustomerException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
        }

        if (logService != null) {
            logService.info(FinancialMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/financial/releases/resources/rest/customer/{customerId}");
        }

        customerTO.setResponseResource(responseResource);
        return customerTO;
    }

}
