package tech.ignitr.habitus.service.user;

import org.springframework.http.ResponseEntity;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.web.user.LoginRequest;
import tech.ignitr.habitus.web.user.RegisterRequest;

import java.util.UUID;

public interface UserService {

    ResponseEntity<User> getUser(UUID id);
    ResponseEntity<User> putUser(UUID id);
    ResponseEntity<Void> deleteUser(UUID id);
    ResponseEntity<String> registerUser(RegisterRequest request);
    ResponseEntity<String> loginUser(LoginRequest request);

}
