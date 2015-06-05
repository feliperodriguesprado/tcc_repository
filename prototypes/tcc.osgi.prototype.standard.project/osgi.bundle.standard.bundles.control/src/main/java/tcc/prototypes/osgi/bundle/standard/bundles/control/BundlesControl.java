/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.prototypes.osgi.bundle.standard.bundles.control;

import java.util.Date;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 *
 * @author felipeprado
 */
public class BundlesControl {

    private static final BundlesControl INSTANCE = new BundlesControl();

    public BundlesControl() {
    }

    public static BundlesControl getInstance() {
        return INSTANCE;
    }

    public Bundle getBundle(Class<?> classFromBundle) throws Exception {

        Bundle bundle = FrameworkUtil.getBundle(classFromBundle);

        if (bundle != null) {
            
            // TODO: Log
            System.out.println(String.format("[Sucess: %s] Bundle found: %s", new Date().toString(), bundle.getSymbolicName()));
            
            return bundle;
        
        } else {
            System.out.println(String.format("[Error: %s] Bundle not found. Cause: bundle is null.", new Date().toString()));
            throw new Exception();
        }
    }

    public BundleContext getBundleContext(Class<?> classFromBundle) throws Exception {

        try {
            
            BundleContext bundleContext = getBundle(classFromBundle).getBundleContext();
            
            // TODO: Log
            System.out.println(String.format("[Sucess: %s] Bundle context found: %s", new Date().toString(), bundleContext.toString()));
            
            return bundleContext;
            
        } catch (Exception e) {
            System.out.println(String.format("[Error: %s] Bundle context not found.", new Date().toString()));
            throw new Exception(String.format("Bundle context not found."));
        }
    }

    public Object getBundleService(Class<?> classFromBundleService) throws Exception {

        BundleContext bundleContext;
        ServiceReference serviceReference;
        Object bundleService;

        try {

            bundleContext = getBundleContext(classFromBundleService);
            serviceReference = bundleContext.getServiceReference(classFromBundleService.getName());
            bundleService = bundleContext.getService(serviceReference);

            // TODO: Log
            System.out.println(String.format("[Sucess: %s] Bundle service found: %s", new Date().toString(), bundleService.toString()));

            return bundleService;

        } catch (Exception e) {
            System.out.println(String.format("[Error: %s] Service not found. Cause: %s", new Date().toString(), e.getMessage()));
            throw new Exception(String.format("Service not found. Cause: %s", e.getMessage()));
        }

    }

}
