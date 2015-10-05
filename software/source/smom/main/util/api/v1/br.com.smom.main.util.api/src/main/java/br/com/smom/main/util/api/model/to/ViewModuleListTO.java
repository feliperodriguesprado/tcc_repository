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
package br.com.smom.main.util.api.model.to;

import br.com.smom.main.util.api.model.entities.ViewModuleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ViewModuleListTO {

    public ViewModuleListTO() {
    }

    @XmlElement(name = "moduleList")
    private List<ViewModuleEntity> viewModuleEntityList = new ArrayList<>();
    private ResponseResourceTO responseResource;

    public List<ViewModuleEntity> getViewModuleEntityList() {
        return viewModuleEntityList;
    }

    public void addViewModuleEntityList(ViewModuleEntity viewModuleModel) {
        this.viewModuleEntityList.add(viewModuleModel);
    }

    public void setViewModuleEntityList(List<ViewModuleEntity> viewModuleEntityList) {
        this.viewModuleEntityList = viewModuleEntityList;
    }

    public ResponseResourceTO getResponseResource() {
        return responseResource;
    }

    public void setResponseResource(ResponseResourceTO responseResource) {
        this.responseResource = responseResource;
    }

}
