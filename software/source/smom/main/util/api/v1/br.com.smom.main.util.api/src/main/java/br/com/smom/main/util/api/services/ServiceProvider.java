/*
 * Smom - Software Module Management.
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
package br.com.smom.main.util.api.services;

import br.com.smom.main.util.api.enums.UtilMessages;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Class responsible for getting services of modules.
 */
public class ServiceProvider {

    public static Object getBundleService(Class<?> classFromBundleService) {

        BundleContext bundleContext;
        ServiceReference serviceReference;
        Object bundleService;

        try {
            bundleContext = BundleProvider.getBundle(classFromBundleService).getBundleContext();
            serviceReference = bundleContext.getServiceReference(classFromBundleService.getName());
            bundleService = bundleContext.getService(serviceReference);
            InternalLog.info(UtilMessages.INFO_BUNDLE_SERVICE_FOUND.getDescription() + ": " + classFromBundleService.getName());
            return bundleService;
        } catch (Exception e) {
            InternalLog.warning(UtilMessages.WARN_BUNDLE_SERVICE_NOT_FOUND.getMessage(
                    "Class not found with service: " + classFromBundleService.getName() + ". " + e.getMessage()));
            return null;
        }

    }

}
