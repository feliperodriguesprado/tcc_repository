package prototypes.smom.main.home.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("smom.main.home start...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("smom.main.home stop...");
    }

}
