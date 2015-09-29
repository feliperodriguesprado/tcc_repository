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
 * distributed under the Li,cense is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.smom.main.util.core.services;

import br.com.smom.main.util.api.enums.UtilMessages;
import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.model.entities.ViewModuleEntity;
import br.com.smom.main.util.api.services.ViewModules;
import br.com.smom.main.util.core.repositories.IViewModuleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ViewModulesService implements ViewModules {

    @Inject
    private IViewModuleRepository viewModuleRepository;

    @Override
    public void startViewModule(ViewModuleEntity viewModuleEntity, String symbolicNameParent) throws UtilException {
        try {
            if (symbolicNameParent != null) {
                ViewModuleEntity viewModuleParent = viewModuleRepository.getBySymbolicName(symbolicNameParent);

                if (viewModuleParent != null) {
                    if (!viewModuleParent.isActive()) {
                        viewModuleParent.setActive(true);
                        viewModuleRepository.update(viewModuleParent);
                    }
                    viewModuleEntity.setParent(viewModuleParent.getId());
                }
            } else {
                viewModuleEntity.setParent(0);
            }

            if (viewModuleRepository.getBySymbolicName(viewModuleEntity.getSymbolicName()) == null) {
                viewModuleRepository.create(viewModuleEntity);
            } else {
                viewModuleEntity = viewModuleRepository.getBySymbolicName(viewModuleEntity.getSymbolicName());
                viewModuleEntity.setActive(true);
                viewModuleRepository.update(viewModuleEntity);
            }
        } catch (UtilException e) {
            throw new UtilException(UtilMessages.FATAL_FAILURE_SYSTEM, e);
        }
    }

    @Override
    public void stopViewModule(ViewModuleEntity viewModuleEntity) throws UtilException {

        List<ViewModuleEntity> viewModuleList;
        int countModuleChildren = 0;
        ViewModuleEntity viewModuleParent;

        try {
            viewModuleEntity = viewModuleRepository.getBySymbolicName(viewModuleEntity.getSymbolicName());

            if (viewModuleEntity != null) {
                viewModuleEntity.setActive(false);
                viewModuleRepository.update(viewModuleEntity);
            }

            viewModuleList = viewModuleRepository.getVielModuleListAll();

            if (viewModuleList.size() > 0) {
                for (ViewModuleEntity viewModule : viewModuleList) {
                    if (!viewModule.isActive()) {
                        countModuleChildren++;
                    }
                }
            }

            if (countModuleChildren == viewModuleList.size()) {
                viewModuleParent = viewModuleRepository.get(viewModuleEntity.getParent());
                viewModuleParent.setActive(false);
                viewModuleRepository.update(viewModuleParent);
            }

        } catch (UtilException e) {
            throw new UtilException(UtilMessages.FATAL_FAILURE_SYSTEM, e);
        }
    }

    @Override
    public List<ViewModuleEntity> getViewModuleListAll() throws UtilException {

        try {
            List<ViewModuleEntity> viewModuleListParent = new ArrayList<>();
            List<ViewModuleEntity> viewModuleListChildren = new ArrayList<>();

            List<ViewModuleEntity> viewModuleListAll = viewModuleRepository.getVielModuleListAll();

            for (ViewModuleEntity viewModuleModel : viewModuleListAll) {

                if (viewModuleModel.getParent() == 0) {
                    viewModuleListParent.add(viewModuleModel);
                } else {
                    viewModuleListChildren.add(viewModuleModel);
                }
            }

            for (ViewModuleEntity viewModuleModelParent : viewModuleListParent) {
                for (ViewModuleEntity viewModuleModelChildren : viewModuleListChildren) {
                    if (viewModuleModelParent.getId() == viewModuleModelChildren.getParent()) {
                        viewModuleModelParent.addViewModuleModelList(viewModuleModelChildren);
                    }
                }
            }

            return viewModuleListParent;
        } catch (UtilException e) {
            throw new UtilException(UtilMessages.FATAL_FAILURE_SYSTEM, e);
        }

    }

}
