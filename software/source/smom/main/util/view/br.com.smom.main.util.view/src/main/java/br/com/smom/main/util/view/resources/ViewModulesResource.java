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

import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.models.ViewModuleModel;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.main.util.api.services.ViewModules;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * http://localhost:8080/modules/main/util/resources/viewmodules
 *
 */
@Path("viewmodules")
public class ViewModulesResource {

    private final ViewModules viewModulesService = (ViewModules) ServiceProvider.getBundleService(ViewModules.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ViewModuleList viewModulesList() {
        
        ViewModuleList viewModuleList = new ViewModuleList();

        try {
            if (viewModulesService != null) {
                viewModuleList.setViewModuleModelList(viewModulesService.getViewModuleListActive());
            }
        } catch (UtilException e) {
            System.out.println(e.getMessage());
        }
        
        return viewModuleList;
    }

}
