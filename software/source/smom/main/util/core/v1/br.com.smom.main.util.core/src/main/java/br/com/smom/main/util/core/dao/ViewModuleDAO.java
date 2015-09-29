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
import br.com.smom.main.util.api.enums.UtilMessages;
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

    @Override
    public int create(ViewModuleEntity viewModuleEntity) throws UtilException {

        String query = "insert into view_modules (type, symbolic_name, active, name, context_path, icon, position, parent) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        return executeInsert(query,
                viewModuleEntity.getType(),
                viewModuleEntity.getSymbolicName(),
                viewModuleEntity.isActive(),
                viewModuleEntity.getName(),
                viewModuleEntity.getContextPath(),
                viewModuleEntity.getIcon(),
                viewModuleEntity.getPosition(),
                viewModuleEntity.getParent() == 0 ? null : viewModuleEntity.getParent());
    }

    @Override
    public void update(ViewModuleEntity viewModuleEntity) throws UtilException {

        String query = "update view_modules set type = ?, symbolic_name = ?, active = ?, "
                + "name = ?, context_path = ?, icon = ?, position = ?, parent = ? where id = ?";
        executeUpdate(query,
                viewModuleEntity.getType(),
                viewModuleEntity.getSymbolicName(),
                viewModuleEntity.isActive(),
                viewModuleEntity.getName(),
                viewModuleEntity.getContextPath(),
                viewModuleEntity.getIcon(),
                viewModuleEntity.getPosition(),
                viewModuleEntity.getParent() == 0 ? null : viewModuleEntity.getParent(),
                viewModuleEntity.getId());
    }

    @Override
    public ViewModuleEntity get(int id) throws UtilException {
        String query = "select "
                + "* "
                + "from view_modules v "
                + "where v.id = ?";
        ResultSet resultSet = executeQuery(query, id);
        return fillViewModuleEntity(resultSet);
    }

    @Override
    public ViewModuleEntity getBySymbolicName(String symbolicName) throws UtilException {
        String query = "select "
                + "* "
                + "from view_modules v "
                + "where v.symbolic_name = ?";
        ResultSet resultSet = executeQuery(query, symbolicName);
        return fillViewModuleEntity(resultSet);
    }

    @Override
    public List<ViewModuleEntity> getViewModuleListAll() throws UtilException {

        String query = "select "
                + "* "
                + "from view_modules v "
                + "order by v.position";
        ResultSet resultSet = executeQuery(query);
        return fillViewModuleModelList(resultSet);
    }

    private ViewModuleEntity fillViewModuleEntity(ResultSet resultSet) throws UtilException {
        try {
            ViewModuleEntity viewModuleEntity = null;
            while (resultSet.next()) {
                viewModuleEntity = setViewModuleEntity(resultSet);
            }
            return viewModuleEntity;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private List<ViewModuleEntity> fillViewModuleModelList(ResultSet resultSet) throws UtilException {
        try {
            List<ViewModuleEntity> viewModuleModelList = new ArrayList<>();
            while (resultSet.next()) {
                viewModuleModelList.add(setViewModuleEntity(resultSet));
            }
            return viewModuleModelList;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

    private ViewModuleEntity setViewModuleEntity(ResultSet resultSet) throws UtilException {
        try {
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
            return viewModuleModel;
        } catch (SQLException e) {
            if (logService != null) {
                logService.error(UtilMessages.ERROR_FILL_ENTITY_RESULTSET.toString(), e);
            }
            throw new UtilException(UtilMessages.ERROR_FILL_ENTITY_RESULTSET, e);
        }
    }

}
