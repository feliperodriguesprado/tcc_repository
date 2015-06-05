package tcc.prototypes.web.application.standard.module1.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    
    public void start(BundleContext context) throws Exception {
        
        System.out.println("Module 1 web start...");

    }

    public void stop(BundleContext context) throws Exception {
        
        System.out.println("Module 1 web stop...");
        
    }

}
