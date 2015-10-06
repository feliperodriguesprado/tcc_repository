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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "peopleId", "cep", "city", "uf", "district", "street"})
public class AddressEntity {

    private int id;           //ColumnDB
    private int peopleId;    //ColumnDB
    private String cep;       //ColumnDB
    private String city;      //ColumnDB
    private String uf;        //ColumnDB
    private String district;  //ColumnDB
    private String street;    //ColumnDB

    public AddressEntity() {
    }
    
    public AddressEntity(int id, int people_id, String cep, String city, String uf, String district, String street) {
        this.id = id;
        this.peopleId = people_id;
        this.cep = cep;
        this.city = city;
        this.uf = uf;
        this.district = district;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressEntity{" + "id=" + id + ", people_id=" + peopleId + ", cep=" + cep + ", city=" + city + ", uf=" + uf + ", district=" + district + ", street=" + street + '}';
    }

}
