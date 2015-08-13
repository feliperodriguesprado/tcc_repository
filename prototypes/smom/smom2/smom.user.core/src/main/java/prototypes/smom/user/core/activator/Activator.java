package prototypes.smom.user.core.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("smom.user.core start...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("smom.user.core stop...");
    }

}
