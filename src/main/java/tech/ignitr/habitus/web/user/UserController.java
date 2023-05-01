package tech.ignitr.habitus.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.service.user.UserService;

import java.util.UUID;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(path="/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request){
        return service.registerUser(request);
    }

    @GetMapping(path="/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request){
        return service.loginUser(request);
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
