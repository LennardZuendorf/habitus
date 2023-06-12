package tech.ignitr.habitus.web.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.service.auth.AuthService;
import tech.ignitr.habitus.service.auth.AuthenticationResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final String baseURL = "/api";
    private final AuthService authService;

    @GetMapping(path=baseURL+"/auth/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthModel request){
        return authService.authenticateUser(request);
    }

    @PostMapping(path=baseURL+"/auth/register")
    public ResponseEntity<AuthenticationResponse> putUser(@RequestBody RegisterRequest request){
        return authService.registerUser(request);
    }
}
