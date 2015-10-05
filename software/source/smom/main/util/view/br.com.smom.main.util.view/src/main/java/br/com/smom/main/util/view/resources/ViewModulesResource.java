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
package br.com.smom.main.util.view.resources;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.enums.UtilMessages;
import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import br.com.smom.main.util.api.model.to.ViewModuleListTO;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.main.util.api.services.ViewModules;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * http://localhost:8080/modules/main/util/resources/rest/viewmodule
 *
 */
@Path("viewmodule")
public class ViewModulesResource {

    @Path("/list/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ViewModuleListTO viewModulesListAll() {

        ViewModules viewModulesService = (ViewModules) ServiceProvider.getBundleService(ViewModules.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        ResponseResourceTO responseResource = new ResponseResourceTO();
        ViewModuleListTO viewModuleListTO = new ViewModuleListTO();

        try {
            if (logService != null) {
                logService.info(UtilMessages.INFO_INITIALIZED_REQUEST_REST.getDescription() + " /modules/main/util/resources/viewmodule/list/all");
            }

            if (viewModulesService != null) {
                viewModuleListTO.setViewModuleEntityList(viewModulesService.getViewModuleListAll());
                responseResource.setMessage(UtilMessages.INFO_GET_VIEW_MODULES);
                viewModuleListTO.setResponseResource(responseResource);
            }
        } catch (UtilException e) {
            responseResource.setCode(e.getCode());
            responseResource.setDescription(e.getDescription());
            viewModuleListTO.setResponseResource(responseResource);
        }

        if (logService != null) {
            logService.info(UtilMessages.INFO_FINISH_REQUEST_REST.getDescription() + " /modules/main/util/resources/viewmodule/list/all");
        }
        return viewModuleListTO;
    }

}
