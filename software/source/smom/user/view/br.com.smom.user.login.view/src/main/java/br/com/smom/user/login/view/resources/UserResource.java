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
package br.com.smom.user.login.view.resources;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.model.to.ResponseResourceTO;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.user.api.enums.UserMessages;
import br.com.smom.user.api.model.entities.UserEntity;
import br.com.smom.user.api.services.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author felipeprado
 */
@Path("user")
public class UserResource {

    /**
     * RESTful POST http://localhost:8080/resources/rest/user
     *
     * @param user JSON converted to {@link UserEntity}
     * @param request HttpServletRequest
     * @return {@link UserEntity} converted to JSON
     */
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity login(JAXBElement<UserEntity> user, @Context HttpServletRequest request) {

        User userService = (User) ServiceProvider.getBundleService(User.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        HttpSession session = request.getSession();
        ResponseResourceTO responseResource = new ResponseResourceTO();
        UserEntity userEntity = user.getValue();

        if (logService != null) {
            logService.info(UserMessages.INFO_INITIALIZED_REQUEST_REST.getMessage());
        }

        if (userService != null) {

            try {
                if (userService.authenticateLogin(userEntity)) {
                    responseResource.setMessage(UserMessages.INFO_SUCCESS_USER_LOGIN);
                } else {
                    responseResource.setMessage(UserMessages.WARN_INCORRECT_USER_LOGIN);
                }
            } catch (UtilException e) {
                responseResource.setMessage(e);
            }

        } else {

            if (logService != null) {
                logService.warn(UserMessages.WARN_UNAVAILABLE_MODULE.getMessage("User Service is null"));
            }

            responseResource.setMessage(UserMessages.WARN_UNAVAILABLE_MODULE);
        }

        userEntity.setResponseResource(responseResource);

        if (logService != null) {
            logService.info(UserMessages.INFO_FINISH_REQUEST_REST.getMessage());
        }

        return userEntity;
    }

}
