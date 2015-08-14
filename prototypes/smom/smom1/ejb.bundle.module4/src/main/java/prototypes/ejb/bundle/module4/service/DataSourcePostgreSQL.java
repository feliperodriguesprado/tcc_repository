package prototypes.ejb.bundle.module4.service;

import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Singleton;
import org.apache.commons.dbcp2.BasicDataSource;
import prototypes.ejb.bundle.module4.api.IDataSourceDB;

@Singleton
public class DataSourcePostgreSQL extends BasicDataSource implements IDataSourceDB {
    
    
    public DataSourcePostgreSQL() {
        
        super.setDriverClassName("org.postgresql.Driver");
        super.setUrl("jdbc:postgresql://localhost:5432/postgres");
        super.setUsername("postgres");
        super.setPassword("Pa$$w0rd");
        super.setDefaultAutoCommit(Boolean.FALSE);
        super.setInitialSize(10);
    }

    @Override
    public Connection getConnectionPG() throws Exception {

        try {
            return super.getConnection();
        } catch (SQLException ex) {
            String msgError = "Error get connection PostgreSQL. Cause: " + ex.getMessage();
            System.out.println(msgError);
            throw new Exception(msgError);
        }
    }

}
