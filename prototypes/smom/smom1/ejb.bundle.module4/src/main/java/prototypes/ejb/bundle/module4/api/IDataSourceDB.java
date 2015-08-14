package prototypes.ejb.bundle.module4.api;

import java.sql.Connection;

public interface IDataSourceDB {
    
    public Connection getConnectionPG() throws Exception;
    
}
