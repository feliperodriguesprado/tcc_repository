/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smom.home.view.initialize;

import br.com.smom.main.util.api.model.entities.ViewModuleEntity;
import br.com.smom.main.util.api.services.InternalLog;
import br.com.smom.main.util.api.services.ServiceProvider;
import br.com.smom.main.util.api.services.ViewModules;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private final ViewModules viewModulesService = (ViewModules) ServiceProvider.getBundleService(ViewModules.class);
    private ViewModuleEntity viewModule;

    @Override
    public void start(BundleContext context) throws Exception {
        if (viewModulesService != null) {
            viewModule = new ViewModuleEntity();
            viewModule.setType(2);
            viewModule.setSymbolicName("br.com.smom.home.view");
            viewModule.setName("Início");
            viewModule.setContextPath("/modules/home/#/");
            viewModule.setIcon("fa fa-home");
            viewModule.setPosition(1);
            viewModulesService.startViewModule(viewModule, null);
        }
        InternalLog.info(String.format("Start bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (viewModulesService != null) {
            viewModulesService.stopViewModule(viewModule);
        }
        InternalLog.info(String.format("Stop bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

}
