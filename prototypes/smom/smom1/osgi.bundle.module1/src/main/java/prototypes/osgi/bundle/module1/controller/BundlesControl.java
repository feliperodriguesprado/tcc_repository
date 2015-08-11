package prototypes.osgi.bundle.module1.controller;

import java.util.Date;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
/**
 * Classe responsável por controlar a obtenção de
 * {@link Bundle}, {@link BundleContext} e serviços dos módulos.
 *
 * @author Felipe Rodrigues do Prado
 */
public class BundlesControl {
    
    /**
     * Método que obtem uma referênca do {@link Bundle} para classe informada
     * como parâmetro. É retornado um {@link  Bundle} associado à classe
     * informada, caso o bundle obtido seja null, é lançado uma exceção do tipo
     * {@link Exception}.
     *
     * @param classFromBundle
     * @return {@link Bundle} referente ao módulo.
     * @throws Exception
     */
    public Bundle getBundle(Class<?> classFromBundle) throws Exception {

        Bundle bundle = FrameworkUtil.getBundle(classFromBundle);

        if (bundle != null) {

            // TODO: escrever em log
            System.out.println(String.format("[Info: %s] Bundle found: %s", new Date().toString(), bundle.getSymbolicName()));

            return bundle;

        } else {
            // TODO: escreve em log.
            System.out.println(String.format("[Warning: %s] Bundle not found. Cause: bundle is null.", new Date().toString()));
            throw new Exception();
        }
    }

    public BundleContext getBundleContext(Class<?> classFromBundle) throws Exception {

        try {

            BundleContext bundleContext = getBundle(classFromBundle).getBundleContext();

            // TODO: escrever em log
            System.out.println(String.format("[Info: %s] Bundle context found: %s", new Date().toString(), bundleContext.toString()));

            return bundleContext;

        } catch (Exception e) {
            // TODO: escrever em log
            System.out.println(String.format("[Warning: %s] Bundle context not found.", new Date().toString()));
            throw new Exception(String.format("Bundle context not found."));
        }
    }

    public Object getBundleService(Class<?> classFromBundleService) throws Exception {

        BundleContext bundleContext;
        ServiceReference serviceReference;
        Object bundleService;

        try {

            bundleContext = getBundleContext(classFromBundleService);
            serviceReference = bundleContext.getServiceReference(classFromBundleService.getName());
            bundleService = bundleContext.getService(serviceReference);

            // TODO: Log
            System.out.println(String.format("[Info: %s] Bundle service found: %s", new Date().toString(), bundleService.toString()));

            return bundleService;

        } catch (Exception e) {
            System.out.println(String.format("[Warning: %s] Service not found. Cause: %s", new Date().toString(), e.getMessage()));
            throw new Exception(String.format("Service not found. Cause: %s", e.getMessage()));
        }

    }

}
