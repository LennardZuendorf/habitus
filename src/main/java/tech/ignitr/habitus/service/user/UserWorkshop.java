package tech.ignitr.habitus.service.user;

import org.springframework.stereotype.Component;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.data.user.UserRepository;

@Component
public class UserWorkshop {

    private final UserRepository repository;
    public UserWorkshop(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User updateUser(long id){
        return repository.getUserById(id);
    }

}
