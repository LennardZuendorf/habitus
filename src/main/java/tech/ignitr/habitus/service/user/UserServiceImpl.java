package tech.ignitr.habitus.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.data.user.UserRepository;
import tech.ignitr.habitus.web.user.UserRequestModel;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository UserRepository) {
        this.repository = UserRepository;
    }

    @Override
    public ResponseEntity<Void> postUser(UserRequestModel requestModel) {
        repository.saveAndFlush(new User(requestModel.getName(), requestModel.getEmail()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<User> getUser(UUID id) {
        if (repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repository.getUserById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<User> putUser(UUID id) {
        if (repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(updateUser(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            repository.flush();
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private User updateUser(UUID id){
        return repository.getUserById(id);
    }
}
