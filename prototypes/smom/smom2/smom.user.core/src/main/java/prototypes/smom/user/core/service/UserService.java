package prototypes.smom.user.core.service;

import javax.ejb.Stateless;
import prototypes.smom.user.core.api.User;

@Stateless
public class UserService implements User {
    
    @Override
    public String getUser() {
        return "Felipe Rodrigues do Prado";
    }

}
