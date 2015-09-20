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
package br.com.smom.main.util.view.resources;

import br.com.smom.main.util.api.models.ViewModuleModel;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "viewModuleList")
public class ViewModuleList {

    private List<ViewModuleModel> viewModuleModelList = new ArrayList<>();

    public List<ViewModuleModel> getViewModuleModelList() {
        return viewModuleModelList;
    }

    public void addViewModuleModelList(ViewModuleModel viewModuleModel) {
        this.viewModuleModelList.add(viewModuleModel);
    }

    public void setViewModuleModelList(List<ViewModuleModel> viewModuleModelList) {
        this.viewModuleModelList = viewModuleModelList;
    }

}
