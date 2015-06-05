package tcc.prototypes.osgi.bundle.service.standard.module3.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import tcc.prototypes.osgi.bundle.service.standard.module3.api.GetMessage;
import tcc.prototypes.osgi.bundle.service.standard.module3.service.GetMessageImpl;

public class Activator implements BundleActivator {

    private ServiceRegistration registry;
    
    public void start(BundleContext context) throws Exception {
        
        System.out.println("Module 3 start...");
        registry = context.registerService(GetMessage.class.getName(), new GetMessageImpl(), null);
        System.out.println("Service \"GetMessage\" registered...");
        
    }

    public void stop(BundleContext context) throws Exception {
        
        System.out.println("Module 3 stop...");
        registry.unregister();
        System.out.println("Service \"GetMessage\" unregistered...");
        
    }

}
