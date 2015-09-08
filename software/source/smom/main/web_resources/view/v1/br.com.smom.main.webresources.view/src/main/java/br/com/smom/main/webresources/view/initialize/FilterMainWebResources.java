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
package br.com.smom.main.webresources.view.initialize;

import br.com.smom.log.api.service.Log;
import br.com.smom.main.datasource.api.service.PostgreSQL;
import br.com.smom.main.util.api.service.ServiceProvider;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class FilterMainWebResources implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PostgreSQL postgreSQL = (PostgreSQL) ServiceProvider.getBundleService(PostgreSQL.class);
        Log log = (Log) ServiceProvider.getBundleService(Log.class);
        Connection connection;

        if (postgreSQL != null) {
            try {
                connection = postgreSQL.getConnection();
                if (log != null) {
                    log.log4j(this.getClass()).info(connection.toString());
                }
            } catch (Exception ex) {
                if (log != null) {
                    log.log4j(this.getClass()).error(ex.getMessage());
                }
            }

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
