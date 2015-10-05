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
package br.com.smom.customer.api.model.to;

import br.com.smom.customer.api.model.entities.PeopleEntity;
import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerListTO {

    @XmlElement
    private List<PeopleEntity> customerList = new ArrayList<>();
    private ResponseResourceTO responseResource;

    public List<PeopleEntity> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<PeopleEntity> customerList) {
        this.customerList = customerList;
    }

    public void addCustomerList(PeopleEntity customer) {
        this.customerList.add(customer);
    }

    public ResponseResourceTO getResponseResource() {
        return responseResource;
    }

    public void setResponseResource(ResponseResourceTO responseResource) {
        this.responseResource = responseResource;
    }

}
