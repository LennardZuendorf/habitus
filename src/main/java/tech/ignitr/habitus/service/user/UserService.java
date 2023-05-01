package tech.ignitr.habitus.service.user;

import org.springframework.http.ResponseEntity;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.web.user.UserRequestModel;

public interface UserService {

    ResponseEntity<Void> createUser(UserRequestModel requestModel);
    ResponseEntity<User> getUser(Long id);
    ResponseEntity<User> updateUser(Long id);
    ResponseEntity<Void> deleteUser(Long id);
}
