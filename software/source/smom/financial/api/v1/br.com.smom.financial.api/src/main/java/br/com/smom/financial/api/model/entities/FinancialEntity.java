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
@XmlType(propOrder = {"id", "type", "accountId", "peopleId", "paymentTypeId", "createDate", "dueDate", "paymentDate", "isPaid", "description", "value", "account", "paymentType", "people"})
public class FinancialEntity {

    private int id;              //ColumnDB
    private int type;            //ColumnDB
    private int accountId;       //ColumnDB
    private int peopleId;        //ColumnDB
    private int paymentTypeId;   //ColumnDB
    private Date createDate;     //ColumnDB
    private Date dueDate;        //ColumnDB
    private Date paymentDate;    //ColumnDB
    private boolean isPaid;      //ColumnDB
    private String description;  //ColumnDB
    private double value;        //ColumnDB
    private AccountEntity account;
    private PaymentTypeEntity paymentType;
    private PeopleEntity people;

    public FinancialEntity(int id, int type, int accountId, int peopleId, int paymentTypeId, Date createDate, Date dueDate, Date paymentDate, boolean isPaid, String description, double value) {
        this.id = id;
        this.type = type;
        this.accountId = accountId;
        this.peopleId = peopleId;
        this.paymentTypeId = paymentTypeId;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
        this.description = description;
        this.value = value;
    }

    public FinancialEntity() {
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPeopleEntity() {
        return peopleId;
    }

    public void setPeopleEntity(int peopleEntity) {
        this.peopleId = peopleEntity;
    }

    public int getPaymentTypeEntity() {
        return paymentTypeId;
    }

    public void setPaymentTypeEntity(int paymentTypeEntity) {
        this.paymentTypeId = paymentTypeEntity;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public void setPaymentType(PaymentTypeEntity paymentType) {
        this.paymentType = paymentType;
    }

    public void setPeople(PeopleEntity people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "FinancialEntity{" + "id=" + id + ", type=" + type + ", accountId=" + accountId + ", peopleId=" + peopleId + ", paymentTypeId=" + paymentTypeId + ", createDate=" + createDate + ", dueDate=" + dueDate + ", paymentDate=" + paymentDate + ", isPaid=" + isPaid + ", description=" + description + ", value=" + value + '}';
    }

}
