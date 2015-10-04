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
package br.com.smom.financial.api.model.entities;

import br.com.smom.customer.api.model.entities.PeopleEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "type", "createDate", "dueDate", "paymentDate", "isActive", "description", "value", "peopleEntity", "accountEntity", "paymentTypeEntity"})
public class FinancialEntity {

    private int id;
    private int type;
    private int accountEntity;
    private int peopleEntity;    
    private int paymentTypeEntity;
    private Date createDate;
    private Date dueDate;
    private Date paymentDate;
    private boolean isPaid;
    private String description;
    private double value;

    public FinancialEntity(int id, int type, int accountEntity, int peopleEntity, int paymentTypeEntity, Date createDate, Date dueDate, Date paymentDate, boolean isPaid, String description, double value) {
        this.id = id;
        this.type = type;
        this.accountEntity = accountEntity;
        this.peopleEntity = peopleEntity;
        this.paymentTypeEntity = paymentTypeEntity;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
        this.description = description;
        this.value = value;
    }
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(int accountEntity) {
        this.accountEntity = accountEntity;
    }

    public int getPeopleEntity() {
        return peopleEntity;
    }

    public void setPeopleEntity(int peopleEntity) {
        this.peopleEntity = peopleEntity;
    }

    public int getPaymentTypeEntity() {
        return paymentTypeEntity;
    }

    public void setPaymentTypeEntity(int paymentTypeEntity) {
        this.paymentTypeEntity = paymentTypeEntity;
    }

    @Override
    public String toString() {
        return "FinancialEntity{" + "id=" + id + ", type=" + type + ", accountEntity=" + accountEntity + ", peopleEntity=" + peopleEntity + ", paymentTypeEntity=" + paymentTypeEntity + ", createDate=" + createDate + ", dueDate=" + dueDate + ", paymentDate=" + paymentDate + ", isPaid=" + isPaid + ", description=" + description + ", value=" + value + '}';
    }

   

}
