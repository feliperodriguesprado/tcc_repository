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
package br.com.smom.main.datasource.core.service;

import br.com.smom.log.api.service.Log;
import br.com.smom.main.datasource.core.dao.PostgreSQLDataSource;
import br.com.smom.main.datasource.api.service.PostgreSQL;
import br.com.smom.main.util.api.service.ServiceProvider;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PostgreSQLService implements PostgreSQL {

    @Inject
    private PostgreSQLDataSource postgreSQLDataSource;
    private Log logService;

    @Override
    public Connection getConnection() throws Exception {
        try {
            System.out.println(postgreSQLDataSource.toString());
            return postgreSQLDataSource.getConnection();
        } catch (SQLException e) {
            logService = (Log) ServiceProvider.getBundleService(Log.class);
            if (logService != null) {
                logService.log4j(this.getClass()).error("Error get connection PostgreSQL", e);
            }
            throw e;
        }
    }

    @Override
    public void commit(Connection connection) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.commit();
            connection.close();
        }
    }

    @Override
    public void rollback(Connection connection) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
            connection.close();
        }
    }

}
