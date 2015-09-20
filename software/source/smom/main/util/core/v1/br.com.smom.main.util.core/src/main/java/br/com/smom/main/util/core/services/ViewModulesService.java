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
package br.com.smom.main.util.core.services;

import br.com.smom.main.util.api.exceptions.UtilException;
import br.com.smom.main.util.api.models.ViewModuleModel;
import br.com.smom.main.util.api.services.ViewModules;
import br.com.smom.main.util.core.repositories.IViewModuleRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ViewModulesService implements ViewModules {

    @Inject
    private IViewModuleRepository viewModuleRepository;

    @Override
    public List<ViewModuleModel> getViewModuleListActive() throws UtilException {
        return viewModuleRepository.getVielModuleListAll();
    }

}
