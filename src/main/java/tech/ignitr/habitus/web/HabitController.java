package tech.ignitr.habitus.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.Habit;
import tech.ignitr.habitus.service.HabitRequestModel;
import tech.ignitr.habitus.service.HabitService;

import java.util.List;

@RestController
public class HabitController {

    private final HabitService service;
    public HabitController( HabitService service) {
        this.service = service;
    }

    //Habit API Endpoints
    /**
     * API call for creating a new habit (HabitEntity)
     * @param requestBody - all of HabitEntity params
     * @return status code, json
     */
    @PostMapping("/habits")
    public ResponseEntity <Habit> postHabit(@RequestBody HabitRequestModel requestBody){
        return service.postHabit(requestBody);
    }

    /**
     * API call for getting all habits (HabitEntity) by userID
     * @param uid - the user ID to be selected by
     * @return status code, json
     */
    @GetMapping("/habits/{id}")
    public ResponseEntity <List<Habit>> getAllHabit(@PathVariable String id){
        return service.getHabits(id);
    }

    /**
     * API call for updating habit (HabitEntity)
     * @param hid - id of the HabitEntry to add
     * @param requestBody - all of HabitEntity params
     * @return status code
     */
    @PutMapping("/habits/{id}")
    public ResponseEntity <Void> putHabit(@PathVariable Long id, @RequestBody HabitRequestModel requestBody){
        return service.putHabit(id, requestBody);
    }

    /**
     * API call for deleting a habit (HabitEntity)
     * @param hid - id of the habit that should be deleted
     * @return status code
     */
    @DeleteMapping("/habits/{id}")
    public ResponseEntity <Void> deleteHabit (@PathVariable Long id){
        return service.deleteHabit(id);
    }
}
