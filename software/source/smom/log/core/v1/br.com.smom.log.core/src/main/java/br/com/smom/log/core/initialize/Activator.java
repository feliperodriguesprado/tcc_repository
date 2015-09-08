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
package br.com.smom.log.core.initialize;

import br.com.smom.log.api.service.Log;
import br.com.smom.log.core.service.LogService;
import br.com.smom.main.util.api.service.InternalLog;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration registry;

    @Override
    public void start(BundleContext context) throws Exception {
        InternalLog.info(String.format("Start bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
        registry = context.registerService(Log.class.getName(), new LogService(), null);
        InternalLog.info(String.format("Registered service %s", Log.class.getName()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        InternalLog.info(String.format("Stop bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
        registry.unregister();
        InternalLog.info(String.format("Unregistered service %s", Log.class.getName()));
    }

}
