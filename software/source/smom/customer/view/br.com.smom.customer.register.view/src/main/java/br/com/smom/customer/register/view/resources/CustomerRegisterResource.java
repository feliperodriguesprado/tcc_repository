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
package br.com.smom.customer.register.view.resources;

import br.com.smom.customer.api.enums.CustomerMessages;
import br.com.smom.customer.api.exceptions.CustomerException;
import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.customer.api.model.to.CustomerListTO;
import br.com.smom.customer.api.model.to.CustomerTO;
import br.com.smom.customer.api.services.Customer;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import br.com.smom.main.util.api.services.ServiceProvider;
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
 * Class that provides resources RESTful to user.
 */
@Path("rest")
public class CustomerRegisterResource {

    /**
     * RESTful POST
     * http://domain/modules/customer/register/resources/rest/create
     *
     * @param customer
     * @return {@link CustomerTO} converted to JSON
     */
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerTO postCustomerCreate(JAXBElement<CustomerTO> customer) {

        Customer customerService = (Customer) ServiceProvider.getBundleService(Customer.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        PeopleEntity people = new PeopleEntity();
        CustomerTO customerTO = customer.getValue();

        try {
            if (logService != null) {
                logService.info(CustomerMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/create");
            }

            if (customerService != null) {

                people.setType(1);
                people.setName(customerTO.getCustomer().getName());
                people.setCpfCnpj(customerTO.getCustomer().getCpfCnpj());
                people.setActive(true);
                people.setPhoneList(customerTO.getCustomer().getPhoneList());
                people.setAddressList(customerTO.getCustomer().getAddressList());

                customerTO.setCustomer(customerService.create(people));
                responseResource.setMessage(CustomerMessages.INFO_CREATE_CUSTOMER);
            } else {
                if (logService != null) {
                    logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("User Service is null"));
                }

                responseResource.setMessage(CustomerMessages.WARN_UNAVAILABLE_MODULE);
            }
        } catch (CustomerException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
        }

        if (logService != null) {
            logService.info(CustomerMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/create");
        }

        customerTO.setResponseResource(responseResource);
        return customerTO;
    }

    /**
     * RESTful POST
     * http://domain/modules/customer/register/resources/rest/update
     *
     * @param customer
     * @return {@link CustomerTO} converted to JSON
     */
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerTO postCustomerUpdate(JAXBElement<CustomerTO> customer) {

        Customer customerService = (Customer) ServiceProvider.getBundleService(Customer.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        PeopleEntity people = new PeopleEntity();
        CustomerTO customerTO = customer.getValue();

        try {
            if (logService != null) {
                logService.info(CustomerMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/update");
            }

            if (customerService != null) {

                people.setId(customerTO.getCustomer().getId());
                people.setType(1);
                people.setName(customerTO.getCustomer().getName());
                people.setCpfCnpj(customerTO.getCustomer().getCpfCnpj());
                people.setActive(customerTO.getCustomer().isActive());
                people.setPhoneList(customerTO.getCustomer().getPhoneList());
                people.setAddressList(customerTO.getCustomer().getAddressList());

                customerTO.setCustomer(customerService.update(people));
                responseResource.setMessage(CustomerMessages.INFO_UPDATE_CUSTOMER);
            } else {
                if (logService != null) {
                    logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("User Service is null"));
                }

                responseResource.setMessage(CustomerMessages.WARN_UNAVAILABLE_MODULE);
            }
        } catch (CustomerException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
        }

        if (logService != null) {
            logService.info(CustomerMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/update");
        }

        customerTO.setResponseResource(responseResource);
        return customerTO;
    }

    /**
     * RESTful GET
     * http://domain/modules/customer/register/resources/rest/list/{customerId}
     *
     * @param customerId
     * @return {@link CustomerTO} converted to JSON
     */
    @Path("/list/{customerId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerTO getCustomerListById(@PathParam("customerId") String customerId) {

        Customer customerService = (Customer) ServiceProvider.getBundleService(Customer.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        CustomerTO customerTO = new CustomerTO();

        try {
            if (logService != null) {
                logService.info(CustomerMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/list/{customerId}");
            }

            if (customerService != null) {
                customerTO.setCustomer(customerService.getById(Integer.parseInt(customerId)));
                responseResource.setMessage(CustomerMessages.INFO_GET_CUSTOMER);
            } else {
                if (logService != null) {
                    logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("User Service is null"));
                }

                responseResource.setMessage(CustomerMessages.WARN_UNAVAILABLE_MODULE);
            }
        } catch (CustomerException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
        }

        if (logService != null) {
            logService.info(CustomerMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/list/{customerId}");
        }

        customerTO.setResponseResource(responseResource);
        return customerTO;
    }

    /**
     * RESTful GET
     * http://domain/modules/customer/register/resources/rest/list/all
     *
     * @return {@link CustomerListTO} converted to JSON
     */
    @Path("/list/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerListTO getCustomerListAll() {

        Customer customerService = (Customer) ServiceProvider.getBundleService(Customer.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        CustomerListTO customerList = new CustomerListTO();

        try {
            if (logService != null) {
                logService.info(CustomerMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/list/all");
            }

            if (customerService != null) {
                customerList.setCustomerList(customerService.getAll());
                responseResource.setMessage(CustomerMessages.INFO_GET_CUSTOMER_LIST);
            } else {
                if (logService != null) {
                    logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("User Service is null"));
                }

                responseResource.setMessage(CustomerMessages.WARN_UNAVAILABLE_MODULE);
            }
        } catch (CustomerException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
        }

        if (logService != null) {
            logService.info(CustomerMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/list/all");
        }

        customerList.setResponseResource(responseResource);
        return customerList;
    }

    /**
     * RESTful GET
     * http://domain/modules/customer/register/resources/rest/list/created/ranking
     *
     * @param position
     * @return {@link CustomerListTO} converted to JSON
     */
    @Path("/list/created/ranking")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerListTO getCustomerListCreatedRanking(@DefaultValue("") @QueryParam("position") String position) {

        Customer customerService = (Customer) ServiceProvider.getBundleService(Customer.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        CustomerListTO customerList = new CustomerListTO();

        try {
            if (logService != null) {
                logService.info(CustomerMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/list/created/ranking");
            }

            if (customerService != null) {
                customerList.setCustomerList(customerService.getCreatedCustomersRanking(Integer.parseInt(position)));
                responseResource.setMessage(CustomerMessages.INFO_GET_CUSTOMER_LIST);
            } else {
                if (logService != null) {
                    logService.warn(CustomerMessages.WARN_UNAVAILABLE_MODULE.getMessage("User Service is null"));
                }

                responseResource.setMessage(CustomerMessages.WARN_UNAVAILABLE_MODULE);
            }
        } catch (CustomerException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
        }

        if (logService != null) {
            logService.info(CustomerMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/customer/register/resources/rest/list/created/ranking");
        }

        customerList.setResponseResource(responseResource);
        return customerList;
    }

}
