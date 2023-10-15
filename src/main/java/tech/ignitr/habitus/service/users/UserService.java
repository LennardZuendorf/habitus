package tech.ignitr.habitus.service.users;

import org.springframework.http.ResponseEntity;
import tech.ignitr.habitus.data.users.User;
import tech.ignitr.habitus.web.users.UserModel;

import java.util.UUID;

public interface UserService {

    ResponseEntity<User> getUser(UUID id);
    ResponseEntity<User> putUser(UserModel model);
    ResponseEntity<Void> deleteUser(UUID id);

}
