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
package br.com.smom.customer.api.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "type", "name", "active", "cpfCnpj", "active", "dateCreate", "phoneList", "addressList"})
public class PeopleEntity {

    private int id;           //ColumnDB
    private int type;         //ColumnDB
    private String name;      //ColumnDB
    private String cpfCnpj;   //ColumnDB
    private boolean active;   //ColumnDB
    private Date dateCreate;  //ColumnDB

    private List<PhoneEntity> phoneList = new ArrayList<>();
    private List<AddressEntity> addressList = new ArrayList<>();

    public PeopleEntity() {
    }

    public PeopleEntity(int id, int type, String name, String cpf_cnpj, boolean active, Date date_create) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.cpfCnpj = cpf_cnpj;
        this.active = active;
        this.dateCreate = date_create;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date date) {
        this.dateCreate = date;
    }

    public List<PhoneEntity> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<PhoneEntity> phoneList) {
        this.phoneList = phoneList;
    }

    public void addPhoneList(PhoneEntity phoneEntity) {
        this.phoneList.add(phoneEntity);
    }

    public List<AddressEntity> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressEntity> addressList) {
        this.addressList = addressList;
    }

    public void addAddressList(AddressEntity addressEntity) {
        this.addressList.add(addressEntity);
    }

    @Override
    public String toString() {
        return "PeopleEntity{" + "id=" + id + ", type=" + type + ", name=" + name + ", cpf_cnpj=" + cpfCnpj + ", active=" + active + ", date_create=" + dateCreate + '}';
    }

}
