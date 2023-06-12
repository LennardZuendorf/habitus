package tech.ignitr.habitus.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.configuration.DatabaseException;
import tech.ignitr.habitus.data.users.User;
import tech.ignitr.habitus.data.users.UserRepository;
import tech.ignitr.habitus.service.auth.AuthService;
import tech.ignitr.habitus.service.auth.AuthenticationResponse;
import tech.ignitr.habitus.web.auth.AuthModel;
import tech.ignitr.habitus.web.users.UserModel;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AuthService authService;

    /**
     * @param id the id identifying the user whose data is required.
     * @return response containing the user's data
     */
    @Override
    public ResponseEntity<User> getUser(UUID id) {
        try{
            return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(()-> new DatabaseException("User not found", HttpStatus.NOT_FOUND)));
        }catch(DatabaseException e){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param model, the request body containing the user's data.
     * @return response containing the newly created user.
     */
    @Override
    public ResponseEntity<User> putUser(UserModel model) {
        try{
            User updateUser = repository.findById(model.getId())
                    .orElseThrow(()-> new DatabaseException("user not found", HttpStatus.NOT_FOUND));
            User newUser = User.builder()
                    .id(model.getId())
                    .email(updateUser.getEmail())
                    .name(model.getName())
                    .password(updateUser.getPassword())
                    .build();
            repository.saveAndFlush(newUser);
            return ResponseEntity.ok(newUser);
        } catch(DatabaseException e){
            return ResponseEntity.status(e.getHttpStatus()).build();
        }
    }

    /**
     * @param id, the id identifying the user to be deleted.
     * @return response containing the status of the deletion.
     */
    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        try{
            repository.delete(repository.findById(id)
                    .orElseThrow(()-> new DatabaseException("user not found", HttpStatus.NOT_FOUND)));
            repository.flush();
            return ResponseEntity.ok().build();
        } catch(DatabaseException e){
            return ResponseEntity.status(e.getHttpStatus()).build();
        }
    }

    /**
     * @param model, the request body containing the user's data.
     * @return response containing the updated user.
     */
    @Override
    public ResponseEntity<AuthenticationResponse> updateUserCredentials(AuthModel model, UUID id) {
        return authService.updateUserCredentials(model, id);
    }
}
