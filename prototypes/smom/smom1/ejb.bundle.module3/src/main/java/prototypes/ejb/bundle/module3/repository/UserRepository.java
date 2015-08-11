package prototypes.ejb.bundle.module3.repository;

import java.util.Date;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository {
    
    public long getIdUser() {
        return new Date().getTime();
    }
    
}
