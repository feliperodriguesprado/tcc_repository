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
import br.com.smom.main.datasource.api.enums.DataSourceMessages;
import br.com.smom.main.datasource.api.exceptions.DataSourceException;
import br.com.smom.main.datasource.core.config.PostgreSQLConfig;
import br.com.smom.main.datasource.api.services.PostgreSQL;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PostgreSQLService implements PostgreSQL {

    @Inject
    private PostgreSQLConfig postgreSQLConfig;

    @Override
    public Connection getConnection() throws DataSourceException {

        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        Connection connection;

        try {
            connection = postgreSQLConfig.getConnection();
            if (logService != null) {
                logService.info(DataSourceMessages.INFO_GET_CONNECTION_POSTGRES.getMessage());
            }
            return connection;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(DataSourceMessages.ERROR_GET_CONNECTION_POSTGRES.getMessage(), e);
            }
            throw new DataSourceException(DataSourceMessages.ERROR_GET_CONNECTION_POSTGRES, e);
        }
    }

    @Override
    public void commit(Connection connection) throws DataSourceException {
        
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        try {
            if (connection != null && !connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(DataSourceMessages.ERROR_COMMIT_CLOSE_CONNECTION_POSTGRES.getMessage(), e);
            }
            throw new DataSourceException(DataSourceMessages.ERROR_COMMIT_CLOSE_CONNECTION_POSTGRES, e);
        }
    }

    @Override
    public void rollback(Connection connection) throws DataSourceException {
        
        Log logService = (Log) ServiceProvider.getBundleService(Log.class);
        
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
                connection.close();
            }
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(DataSourceMessages.ERROR_ROLLBACK_CLOSE_CONNECTION_POSTGRES.getMessage(), e);
            }
            throw new DataSourceException(DataSourceMessages.ERROR_ROLLBACK_CLOSE_CONNECTION_POSTGRES, e);
        }

    }

}
