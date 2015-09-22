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
package br.com.smom.main.util.core.dao;

import br.com.smom.log.api.services.Log;
import br.com.smom.main.datasource.api.dao.GenericDataBaseDAO;
import br.com.smom.main.util.api.enums.Messages;
import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.model.entities.ViewModuleEntity;
import br.com.smom.main.util.api.services.ServiceProvider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ViewModuleDAO extends GenericDataBaseDAO implements IViewModuleDAO {

    private final Log logService = (Log) ServiceProvider.getBundleService(Log.class);
    private String query;
    private ResultSet resultSet;

    @Override
    public List<ViewModuleEntity> getViewModuleListAll() throws UtilException {

        query = "select "
                + "* "
                + "from view_modules v "
                + "order by v.position";
        resultSet = executeQuery(query);
        return fillViewModuleModelList();
    }

    private List<ViewModuleEntity> fillViewModuleModelList() throws UtilException {

        try {
            List<ViewModuleEntity> viewModuleModelList = new ArrayList<>();

            while (resultSet.next()) {
                ViewModuleEntity viewModuleModel = new ViewModuleEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("type"),
                        resultSet.getString("symbolic_name"),
                        resultSet.getBoolean("active"),
                        resultSet.getString("name"),
                        resultSet.getString("context_path"),
                        resultSet.getString("icon"),
                        resultSet.getInt("position"),
                        resultSet.getInt("parent"));
                viewModuleModelList.add(viewModuleModel);
            }
            return viewModuleModelList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(Messages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(Messages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

}
