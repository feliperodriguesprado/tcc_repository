package br.com.smom.control.serviceprovided.activator;

import java.util.Date;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println(String.format("[Info: %s] Bundle Control Service Provider (br.com.smom.control.serviceprovided) start.", new Date().toString()));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println(String.format("[Info: %s] Bundle Control Service Provider (br.com.smom.control.serviceprovided) stop.", new Date().toString()));
    }

}
