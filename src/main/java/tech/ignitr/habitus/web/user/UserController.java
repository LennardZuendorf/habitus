package tech.ignitr.habitus.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.service.user.UserService;

import java.util.UUID;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController( UserService service) {
        this.service = service;
    }

    @PostMapping(path="/")
    public ResponseEntity<Void> postUser(UserRequestModel requestModel){
        return service.postUser(requestModel);
    }

    @GetMapping(path = "/")
    public ResponseEntity<User> getUser(@RequestParam("id") UUID id){
        return service.getUser(id);
    }

    @PutMapping(path="/")
    public ResponseEntity<User> putUser(@RequestParam("id") UUID id){
        return service.putUser(id);
    }

    @DeleteMapping(path="/")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") UUID id){
        return service.deleteUser(id);
    }
}
