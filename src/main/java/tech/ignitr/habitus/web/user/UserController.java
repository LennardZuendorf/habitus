package tech.ignitr.habitus.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.service.user.UserService;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController( UserService service) {
        this.service = service;
    }

    @PostMapping(path="/user")
    public ResponseEntity<Void> postUser(UserRequestModel requestModel){
        return service.createUser(requestModel);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> getUser(Long id){
        return service.getUser(id);
    }

    @PutMapping(path="/user/{id}")
    public ResponseEntity<User> putUser(Long id){
        return service.updateUser(id);
    }

    @DeleteMapping(path="/user/{id}")
    public ResponseEntity<Void> deleteUser(Long id){
        return service.deleteUser(id);
    }
}
