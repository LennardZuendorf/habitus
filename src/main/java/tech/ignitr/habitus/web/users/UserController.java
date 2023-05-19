package tech.ignitr.habitus.web.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.habits.Habit;
import tech.ignitr.habitus.data.users.User;
import tech.ignitr.habitus.service.auth.AuthService;
import tech.ignitr.habitus.service.habits.HabitService;
import tech.ignitr.habitus.service.users.UserService;

import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class UserController {

    private final String baseURL = "/api";
    private final UserService userService;
    private final HabitService service;
    private final AuthService authService;

    @GetMapping(path = baseURL+"/users{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id){
        return userService.getUser(id);
    }

    @PutMapping(path=baseURL+"/users")
    public ResponseEntity<User> putUser(@RequestBody UserRequest request){
        return userService.putUser(request);
    }

    @DeleteMapping(path=baseURL+"/users{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id){
        return userService.deleteUser(id);
    }

    /**
     * API call for deleting all habits (HabitEntity)
     * @param id - id of the user all habits should be deleted from
     * @return ResponseEntity containing the status code from service method
     */
    @DeleteMapping(baseURL+"/users{id}/habits")
    public ResponseEntity <Void> deleteAllHabits (@PathVariable("id") UUID id){
        return service.deleteAllHabits(id);
    }

    /**
     * API call for getting all habits (HabitEntity) by userID
     * @param id - the user ID to be selected by
     * @return ResponseEntity containing the status code from service method and a list of Habits
     */
    @GetMapping(baseURL+"/users{id}/habits")
    public ResponseEntity <List<Habit>> getAllHabit(@PathVariable("id") UUID id){
        return service.getHabits(id);
    }
}
