package prototypes.smom.main.home.activator;

import java.util.Date;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println(String.format("[Info: %s] Module smom.main.home start...", new Date().toString()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println(String.format("[Info: %s] Module smom.main.home stop...", new Date().toString()));
    }

}
