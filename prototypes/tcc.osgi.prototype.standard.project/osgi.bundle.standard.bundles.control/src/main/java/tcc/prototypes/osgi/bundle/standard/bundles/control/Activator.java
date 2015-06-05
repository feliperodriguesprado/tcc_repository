package tcc.prototypes.osgi.bundle.standard.bundles.control;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("Module Bundles control start...");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Module Bundles control stop...");
    }

}
