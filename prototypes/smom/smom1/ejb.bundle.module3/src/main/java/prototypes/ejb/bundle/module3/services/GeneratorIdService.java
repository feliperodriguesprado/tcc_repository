package prototypes.ejb.bundle.module3.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import prototypes.ejb.bundle.module3.repository.UserRepository;
import prototypes.osgi.bundle.module3.base.api.IGeneratorId;

@Stateless
public class GeneratorIdService implements IGeneratorId {

    @Inject
    private UserRepository userRepository;

    @Override
    public long getIdUser() {
        System.out.println(userRepository.toString());
        return userRepository.getIdUser();
    }

}
