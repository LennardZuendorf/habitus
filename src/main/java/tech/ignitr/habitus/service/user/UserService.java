package tech.ignitr.habitus.service.user;

import org.springframework.http.ResponseEntity;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.web.user.UserRequestModel;

import java.util.UUID;

public interface UserService {

    ResponseEntity<Void> postUser(UserRequestModel requestModel);
    ResponseEntity<User> getUser(UUID id);
    ResponseEntity<User> putUser(UUID id);
    ResponseEntity<Void> deleteUser(UUID id);
}
