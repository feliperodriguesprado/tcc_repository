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
package br.com.smom.main.util.core.repositories;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.services.PostgreSQL;
import br.com.smom.main.util.api.enums.UtilMessages;
import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.model.entities.ViewModuleEntity;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.main.util.core.dao.IViewModuleDAO;
import java.sql.Connection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ViewModuleRepository implements IViewModuleRepository {

    private final PostgreSQL posgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);
    @Inject
    private IViewModuleDAO viewModuleDAO;

    @Override
    public ViewModuleEntity create(ViewModuleEntity viewModuleEntity) throws UtilException {

        ViewModuleEntity viewModuleCreated = null;
        Connection connection;

        if (posgreSQLService != null) {
            connection = posgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                int generatedKey = viewModuleDAO.create(viewModuleEntity);

                if (generatedKey > 0) {
                    viewModuleCreated = viewModuleDAO.get(generatedKey);
                } else {
                    if (logService != null) {
                        logService.error(UtilMessages.ERROR_CREATE_ENTITY.getMessage("Generate key view module entity diferent of 1."));
                    }
                    throw new UtilException(UtilMessages.ERROR_CREATE_ENTITY.getMessage("Generate key view module entity diferent of 1."));
                }

                posgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("View module created: " + (viewModuleCreated != null ? viewModuleCreated.toString() : "is null"));
                }
                return viewModuleCreated;
            } catch (UtilException e) {
                posgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(UtilMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UtilException(UtilMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public ViewModuleEntity update(ViewModuleEntity viewModuleEntity) throws UtilException {
        return null;
    }

    @Override
    public ViewModuleEntity get(int id) throws UtilException {

        ViewModuleEntity viewModuleEntity;
        Connection connection;

        if (posgreSQLService != null) {
            connection = posgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleEntity = viewModuleDAO.get(id);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module getting: " + (viewModuleEntity != null ? viewModuleEntity.toString() : "is null"));
                }
                return viewModuleEntity;
            } catch (UtilException e) {
                posgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(UtilMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UtilException(UtilMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public ViewModuleEntity getBySymbolicName(String symbolicName) throws UtilException {
        ViewModuleEntity viewModuleEntity;
        Connection connection;

        if (posgreSQLService != null) {
            connection = posgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleEntity = viewModuleDAO.getBySymbolicName(symbolicName);
                posgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module getting: " + (viewModuleEntity != null ? viewModuleEntity.toString() : "is null"));
                }
                return viewModuleEntity;
            } catch (UtilException e) {
                posgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(UtilMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UtilException(UtilMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

    @Override
    public List<ViewModuleEntity> getVielModuleListAll() throws UtilException {

        List<ViewModuleEntity> viewModuleList;
        Connection connection;

        if (posgreSQLService != null) {
            connection = posgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleList = viewModuleDAO.getViewModuleListAll();
                posgreSQLService.commit(connection);
                return viewModuleList;
            } catch (UtilException e) {
                posgreSQLService.rollback(connection);
                throw e;
            }
        } else {
            if (logService != null) {
                logService.warn(UtilMessages.WARN_UNAVAILABLE_MODULE.getMessage("PostgreSQL Service is null"));
            }
            throw new UtilException(UtilMessages.WARN_UNAVAILABLE_MODULE);
        }
    }

}
