/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypes.web.bundle.module2.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 * @author Felipe
 */
public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Start Web Bundle Module 2...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stop Web Bundle Module 2...");
    }

}
