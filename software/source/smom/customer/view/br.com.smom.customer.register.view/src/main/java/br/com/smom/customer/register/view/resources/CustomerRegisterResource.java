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
import br.com.smom.customer.api.model.to.CustomerListTO;
import br.com.smom.customer.api.services.Customer;
import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import br.com.smom.main.util.api.services.ServiceProvider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * http://localhost:8080/modules/customer/register/resources/rest/
 *
 */
@Path("customer")
public class CustomerRegisterResource {

    @Path("/list/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerListTO viewModulesListAll() {

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

}
