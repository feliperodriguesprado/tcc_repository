package tcc.prototype.prototypeosgi1.helloworld.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import tcc.prototype.prototypeosgi1.helloworld.core.service.Message;

public class Activator implements BundleActivator {

	public Message messageService = new Message();
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		context.registerService(Message.class.getName(), messageService, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		messageService = null;
	}

}
