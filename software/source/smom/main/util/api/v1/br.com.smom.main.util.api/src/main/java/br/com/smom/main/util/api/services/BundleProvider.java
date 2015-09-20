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
package br.com.smom.main.util.api.services;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * Class responsible for getting bundles.
 */
public class BundleProvider {

    public static Bundle getBundle(Class<?> classFromBundle) {
        Bundle bundle = FrameworkUtil.getBundle(classFromBundle);

        if (bundle != null) {
            InternalLog.info(String.format("Bundle found: %s.", bundle.getSymbolicName()));
            return bundle;
        } else {
            InternalLog.warning(String.format("Code=4001 Description=Bundle not found. Cause: class %s was not found as a bundle.",
                    classFromBundle.getName()));
            return null;
        }
    }

    public static Bundle[] getBundleList() {

        BundleContext bundleContext;

        try {
            bundleContext = getBundle(BundleProvider.class).getBundleContext();
            return bundleContext.getBundles();
        } catch (Exception e) {
            InternalLog.warning(String.format("Code=4002 Description=Error get bundle list. Cause: %s", e.getMessage()));
            return null;
        }
    }

}
