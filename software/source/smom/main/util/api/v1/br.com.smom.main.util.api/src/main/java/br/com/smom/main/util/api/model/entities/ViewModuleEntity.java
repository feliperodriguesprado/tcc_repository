/*
 * Copyright 2015 Smom - Software Module Management.
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
package br.com.smom.main.util.api.model.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "type", "symbolicName", "active", "name", "contextPath", "icon", "position", "parent", "viewModuleModelList"})
public class ViewModuleEntity {

    private int id;
    private int type;
    private String symbolicName;
    private boolean active;
    private String name;
    private String contextPath;
    private String icon;
    private int position;
    private int parent;
    @XmlElement(name = "childrenList")
    private List<ViewModuleEntity> viewModuleModelList = new ArrayList<>();

    public ViewModuleEntity() {
    }

    public ViewModuleEntity(int id, int type, String symbolicName, boolean active, String name, String contextPath, String icon, int position, int parent) {
        this.id = id;
        this.type = type;
        this.symbolicName = symbolicName;
        this.active = active;
        this.name = name;
        this.contextPath = contextPath;
        this.icon = icon;
        this.position = position;
        this.parent = parent;
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

    public String getSymbolicName() {
        return symbolicName;
    }

    public void setSymbolicName(String symbolicName) {
        this.symbolicName = symbolicName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public List<ViewModuleEntity> getViewModuleModelList() {
        return viewModuleModelList;
    }

    public void addViewModuleModelList(ViewModuleEntity viewModuleModel) {
        this.viewModuleModelList.add(viewModuleModel);
    }

    public void setViewModuleModelList(List<ViewModuleEntity> viewModuleModelList) {
        this.viewModuleModelList = viewModuleModelList;
    }

}
