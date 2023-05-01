package tech.ignitr.habitus.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.data.user.UserRepository;
import tech.ignitr.habitus.web.user.LoginRequest;
import tech.ignitr.habitus.web.user.RegisterRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    /**
     * @param id the id identifying the user whose data is required.
     * @return response containing the user's data
     */
    @Override
    public ResponseEntity<User> getUser(UUID id) {
        if (repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).orElseThrow(()-> new RuntimeException("User not found")));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * @param id the id identifying the user whose data shall be updated
     * @return response containing the newly created user.
     */
    @Override
    public ResponseEntity<User> putUser(UUID id) {
        if (repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(updateUser(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * @param id, the id identifying the user to be deleted.
     * @return response containing the status of the deletion.
     */
    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            repository.flush();
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * @param request, the request containing the username and password of the user.
     * @return response containing the JWT token of the user.
     */
    @Override
    public ResponseEntity<String> registerUser(RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body("Test");
    }

    /**
     * @param request, the request containing the username and password of the user.
     * @return response containing the JWT token of the user.
     */
    @Override
    public ResponseEntity<String> loginUser(LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body("Test");
    }


    //TODO:Implement update User

    /**
     *
     * @param id
     * @return
     */
    private User updateUser(UUID id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

}
