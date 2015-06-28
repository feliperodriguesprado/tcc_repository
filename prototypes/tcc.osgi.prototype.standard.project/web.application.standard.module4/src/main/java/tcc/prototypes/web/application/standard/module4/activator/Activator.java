package tcc.prototypes.web.application.standard.module4.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {

        System.out.println("Module 4 web start...");

    }

    public void stop(BundleContext context) throws Exception {

        System.out.println("Module 4 web stop...");

    }

}
