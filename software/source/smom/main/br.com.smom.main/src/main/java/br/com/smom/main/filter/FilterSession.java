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
package br.com.smom.main.filter;

import br.com.smom.control.serviceprovided.ServiceProvided;
import br.com.smom.datasource.exported.api.PostgreSQL;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class FilterSession implements Filter {

    @Inject
    private SessionSingleton sessionSingleton;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        try {
            /*if (session == null || session.getAttribute("user") == null) {
            httpServletResponse.sendRedirect("/modules/login");
            } else {
            chain.doFilter(request, response);
            }*/
            
            //System.out.println(httpServletRequest.getParameter("teste"));
            //System.out.println(session.getId());
            //PostgreSQL postgreSQL = (PostgreSQL) ServiceProvided.getBundleService(PostgreSQL.class);
            //Connection connection = postgreSQL.getConnection();
            //System.out.println(connection.toString());
            //postgreSQL.commit(connection);
            
            HttpSession sessionArmazenada = null;
            
            if (session.isNew()) {
                
                sessionArmazenada = sessionSingleton.getSession1();
                
                session.setAttribute("session", new Date().getTime());
                
                if (sessionArmazenada == null) {
                    sessionSingleton.setSession1(session);
                    sessionArmazenada = sessionSingleton.getSession1();
                } else {
                    System.out.println("Session do singleton não é null...");
                    System.out.println("Object: " + sessionArmazenada.toString() + 
                    "\nID: " + sessionArmazenada.getId() +
                    "\nContent " + sessionArmazenada.getAttribute("session"));
                }
            }
            
            System.out.println("\n\nObject:");
            System.out.println("Object: " + session.toString() + 
                    "\nID: " + session.getId() +
                    "\nContent " + session.getAttribute("session"));
            
            if (sessionArmazenada != null) {
                System.out.println("\n\nObject singleton:");
                System.out.println("Object: " + sessionArmazenada.toString() + 
                        "\nID: " + sessionArmazenada.getId() +
                        "\nContent " + sessionArmazenada.getAttribute("session"));
            }
            
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}