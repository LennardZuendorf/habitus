package tech.ignitr.habitus.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.service.user.UserService;

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
        return service.createUser(requestModel);
    }

    @GetMapping(path = "/")
    public ResponseEntity<User> getUser(@RequestParam("id") Long id){
        return service.getUser(id);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<User> putUser(@RequestParam("id") Long id){
        return service.updateUser(id);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Long id){
        return service.deleteUser(id);
    }
}
