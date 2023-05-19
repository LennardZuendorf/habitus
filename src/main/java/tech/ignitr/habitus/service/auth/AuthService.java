package tech.ignitr.habitus.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.configuration.DatabaseException;
import tech.ignitr.habitus.data.users.User;
import tech.ignitr.habitus.data.users.UserRepository;
import tech.ignitr.habitus.web.auth.AuthModel;
import tech.ignitr.habitus.web.auth.RegisterRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthenticationResponse> authenticateUser(AuthModel request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
            User user = repository.findByEmail(request.getEmail()).orElseThrow(()-> new DatabaseException("User not found", HttpStatus.NOT_FOUND));

            String jwtToken = tokenService.generateToken(user);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build());

        } catch (AuthenticationException ignored) {
            return ResponseEntity.badRequest().build();
        } catch (DatabaseException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<AuthenticationResponse> registerUser(RegisterRequest request) {
        try{
            if(repository.existsByEmail(request.getEmail())) {
                throw new DatabaseException("Email already in use", HttpStatus.CONFLICT);
            }

            User newUser = User.builder()
                            .id(UUID.randomUUID())
                            .email(request.getEmail())
                            .name(request.getName())
                            .password(encoder.encode(request.getPassword()))
                            .build();
            repository.saveAndFlush(newUser);

            String jwtToken = tokenService.generateToken(newUser);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(newUser)
                    .build());

        }catch (DatabaseException e) {
            return ResponseEntity.status(e.getHttpStatus()).build();
        }
    }

    public ResponseEntity<AuthenticationResponse> updateUserCredentials(AuthModel model, UUID id) {
        try{
            User updateUser = repository.findById(id)
                    .orElseThrow(()-> new DatabaseException("User not found", HttpStatus.NOT_FOUND));
            User newUser = User.builder()
                    .id(updateUser.getId())
                    .email(model.getEmail())
                    .name(updateUser.getName())
                    .password(encoder.encode(model.getPassword()))
                    .build();
            repository.saveAndFlush(newUser);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    model.getEmail(), model.getPassword()));

            String jwtToken = tokenService.generateToken(newUser);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(newUser)
                    .build());

        } catch (AuthenticationException ignored) {
            return ResponseEntity.badRequest().build();
        } catch (DatabaseException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
