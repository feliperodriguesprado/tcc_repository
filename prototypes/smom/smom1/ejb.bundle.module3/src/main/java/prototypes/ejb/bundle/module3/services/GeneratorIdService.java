package prototypes.ejb.bundle.module3.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import prototypes.ejb.bundle.module3.api.GeneratorId;
import prototypes.ejb.bundle.module3.repository.UserRepository;

@Stateless
public class GeneratorIdService implements GeneratorId {

    @Inject
    private UserRepository userRepository;

    @Override
    public long getIdUser() {
        System.out.println(userRepository.toString());
        return userRepository.getIdUser();
    }

}
