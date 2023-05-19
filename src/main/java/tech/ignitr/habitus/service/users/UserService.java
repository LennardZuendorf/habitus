package tech.ignitr.habitus.service.users;

import org.springframework.http.ResponseEntity;
import tech.ignitr.habitus.data.users.User;
import tech.ignitr.habitus.web.auth.LoginRequest;
import tech.ignitr.habitus.web.auth.RegisterRequest;
import tech.ignitr.habitus.web.users.UserRequest;

import java.util.UUID;

public interface UserService {

    ResponseEntity<User> getUser(UUID id);
    ResponseEntity<User> putUser(UserRequest requestBody);
    ResponseEntity<Void> deleteUser(UUID id);
    ResponseEntity<String> registerUser(RegisterRequest request);
    ResponseEntity<String> loginUser(LoginRequest request);

}
