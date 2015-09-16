package br.com.smom.customer.api.initialize;

import br.com.smom.main.util.api.service.InternalLog;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        InternalLog.info(String.format("Start bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        InternalLog.info(String.format("Stop bundle %s %s", context.getBundle().getSymbolicName(), context.getBundle().getVersion()));
    }

}
