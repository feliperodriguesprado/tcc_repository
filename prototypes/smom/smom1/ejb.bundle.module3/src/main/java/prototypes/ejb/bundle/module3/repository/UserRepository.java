package prototypes.ejb.bundle.module3.repository;

import java.sql.Connection;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import prototypes.ejb.bundle.module4.api.IDataSourceDB;
import prototypes.osgi.bundle.module1.controller.BundlesControl;

@ApplicationScoped
public class UserRepository {

    public long getIdUser() {
        
        Connection con;
        
        try {
            BundlesControl bundlesControl = new BundlesControl();
            IDataSourceDB dataSource = (IDataSourceDB) bundlesControl.getBundleService(IDataSourceDB.class);
            con = dataSource.getConnectionPG();
            System.out.println("Object data source: " + dataSource.toString());
            System.out.println("Object connection: " + con.toString());
            con.close();
        } catch (Exception e) {
            System.out.println("Error connection.");
        }
        
        return new Date().getTime();
    }

}
