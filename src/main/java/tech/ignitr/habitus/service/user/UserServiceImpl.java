package tech.ignitr.habitus.service.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.user.User;

@Service
public class UserServiceImpl implements UserService{

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<User> getUser(Long id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ResponseEntity<Void> createUser() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<User> updateUser(Long id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        return null;
    }
}
