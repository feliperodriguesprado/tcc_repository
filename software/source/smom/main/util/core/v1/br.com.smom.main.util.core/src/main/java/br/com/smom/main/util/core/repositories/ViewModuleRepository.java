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

    @Inject
    private IViewModuleDAO viewModuleDAO;

    @Override
    public ViewModuleEntity create(ViewModuleEntity viewModuleEntity) throws UtilException {
        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        ViewModuleEntity viewModuleCreated;
        Connection connection;

        if (postgreSQLService != null) {
            connection = postgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                int generatedKey = viewModuleDAO.create(viewModuleEntity);
                viewModuleCreated = viewModuleDAO.get(generatedKey);
                postgreSQLService.commit(connection);

                if (logService != null) {
                    logService.info("View module created: " + (viewModuleCreated != null ? viewModuleCreated.toString() : "is null"));
                }
                return viewModuleCreated;
            } catch (UtilException e) {
                postgreSQLService.rollback(connection);
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
        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        ViewModuleEntity viewModuleUpdated;
        Connection connection;

        if (postgreSQLService != null) {
            connection = postgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleDAO.update(viewModuleEntity);
                viewModuleUpdated = viewModuleDAO.get(viewModuleEntity.getId());
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module updated: " + (viewModuleUpdated != null ? viewModuleUpdated.toString() : "is null"));
                }
                return viewModuleUpdated;
            } catch (UtilException e) {
                postgreSQLService.rollback(connection);
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
    public ViewModuleEntity get(int id) throws UtilException {
        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        ViewModuleEntity viewModuleEntity;
        Connection connection;

        if (postgreSQLService != null) {
            connection = postgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleEntity = viewModuleDAO.get(id);
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module getting: " + (viewModuleEntity != null ? viewModuleEntity.toString() : "is null"));
                }
                return viewModuleEntity;
            } catch (UtilException e) {
                postgreSQLService.rollback(connection);
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
        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        ViewModuleEntity viewModuleEntity;
        Connection connection;

        if (postgreSQLService != null) {
            connection = postgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleEntity = viewModuleDAO.getBySymbolicName(symbolicName);
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module getting: " + (viewModuleEntity != null ? viewModuleEntity.toString() : "is null"));
                }
                return viewModuleEntity;
            } catch (UtilException e) {
                postgreSQLService.rollback(connection);
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
    public List<ViewModuleEntity> getViewModuleListAll() throws UtilException {
        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        List<ViewModuleEntity> viewModuleList;
        Connection connection;

        if (postgreSQLService != null) {
            connection = postgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleList = viewModuleDAO.getViewModuleListAll();
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module list getting: " + (viewModuleList != null ? viewModuleList.toString() : "is null"));
                }
                return viewModuleList;
            } catch (UtilException e) {
                postgreSQLService.rollback(connection);
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
    public List<ViewModuleEntity> getViewModuleListByParent(int parentId) throws UtilException {
        PostgreSQL postgreSQLService = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);

        List<ViewModuleEntity> viewModuleList;
        Connection connection;

        if (postgreSQLService != null) {
            connection = postgreSQLService.getConnection();
            try {
                viewModuleDAO.setConnection(connection);
                viewModuleList = viewModuleDAO.getViewModuleListByParent(parentId);
                postgreSQLService.commit(connection);
                if (logService != null) {
                    logService.info("View module list getting: " + (viewModuleList != null ? viewModuleList.toString() : "is null"));
                }
                return viewModuleList;
            } catch (UtilException e) {
                postgreSQLService.rollback(connection);
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
