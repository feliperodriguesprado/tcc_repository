package prototypes.smom.user.core.repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import prototypes.smom.user.core.model.dao.UserDAO;

@Dependent
public class UserRepository {
    
    @Inject
    private UserDAO userDAO;
    
    public String getUser() {
        return userDAO.getUser();
    };
    
}
