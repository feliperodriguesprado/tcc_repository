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
package br.com.smom.main.datasource.core.services;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.datasource.core.dao.PostgreSQLDataSource;
import br.com.smom.main.datasource.api.services.PostgreSQL;
import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PostgreSQLService implements PostgreSQL {

    @Inject
    private PostgreSQLDataSource postgreSQLDataSource;
    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);

    @Override
    public Connection getConnection() throws DataSourceException {
        try {
            return postgreSQLDataSource.getConnection();
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(Messages.ERROR_GET_CONNECTION_POSTGRES.toString(), e);
            }
            throw new DataSourceException(Messages.ERROR_GET_CONNECTION_POSTGRES, e);
        }
    }

    @Override
    public void commit(Connection connection) throws DataSourceException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(Messages.ERROR_COMMIT_CLOSE_CONNECTION_POSTGRES.toString(), e);
            }
            throw new DataSourceException(Messages.ERROR_COMMIT_CLOSE_CONNECTION_POSTGRES, e);
        }
    }

    @Override
    public void rollback(Connection connection) throws DataSourceException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
                connection.close();
            }
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(Messages.ERROR_ROLLBACK_CLOSE_CONNECTION_POSTGRES.toString(), e);
            }
            throw new DataSourceException(Messages.ERROR_ROLLBACK_CLOSE_CONNECTION_POSTGRES, e);
        }

    }

}
