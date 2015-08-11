package prototypes.ejb.bundle.module3.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("EJB Bundle Module 3 start...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("EJB Bundle Module 3 stop...");
    }
    
}
