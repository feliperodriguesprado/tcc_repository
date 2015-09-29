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
package br.com.smom.financial.releases.view.initialize;

import br.com.smom.main.util.api.model.entities.ViewModuleEntity;
import br.com.smom.main.util.api.services.InternalLog;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.main.util.api.services.ViewModules;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        
        ViewModules viewModulesService = (ViewModules) ServiceProvider.getBundleService(ViewModules.class);
        
        if (viewModulesService != null) {
            ViewModuleEntity viewModule = new ViewModuleEntity();
            viewModule.setType(2);
            viewModule.setSymbolicName("br.com.smom.financial.releases.view");
            viewModule.setName("Lançamentos");
            viewModule.setContextPath("/modules/financial/releases/#/");
            viewModule.setIcon("fa fa-money");
            viewModule.setPosition(1);
            viewModulesService.startViewModule(viewModule, "br.com.smom.financial");
        }
        
        InternalLog.info(String.format("Start bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        
        ViewModules viewModulesService = (ViewModules) ServiceProvider.getBundleService(ViewModules.class);
        
        if (viewModulesService != null) {
            ViewModuleEntity viewModule = new ViewModuleEntity();
            viewModule.setType(2);
            viewModule.setSymbolicName("br.com.smom.financial.releases.view");
            viewModule.setName("Lançamentos");
            viewModule.setContextPath("/modules/financial/releases/#/");
            viewModule.setIcon("fa fa-money");
            viewModule.setPosition(1);
            viewModulesService.stopViewModule(viewModule);
        }
        
        InternalLog.info(String.format("Stop bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

}
