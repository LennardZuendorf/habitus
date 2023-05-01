package tech.ignitr.habitus.web.habit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.habit.Habit;
import tech.ignitr.habitus.service.habit.HabitService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/habits")
public class HabitController {

    private final HabitService service;

    @Autowired
    public HabitController( HabitService service) {
        this.service = service;
    }

    //Habit API Endpoints
    /**
     * API call for creating a new habit (HabitEntity)
     * @param requestBody - all of HabitEntity params
     * @return status code, json
     */
    @PostMapping("/")
    public ResponseEntity <Habit> postHabit(@RequestBody HabitRequestModel requestBody){
        var output = service.postHabit(requestBody);
        return ResponseEntity.status(output.getStatus()).body(output.getResponse());
    }

    /**
     * API call for getting all habits (HabitEntity) by userID
     * @param userId - the user ID to be selected by
     * @return status code, json
     */
    @GetMapping("/")
    public ResponseEntity <List<Habit>> getAllHabit(@RequestParam("userId") UUID userId){
        var output = service.getHabits(userId);
        return ResponseEntity.status(output.getStatus()).body(output.getResponse());
    }

    /**
     * API call for updating habit (HabitEntity)
     * @param id - id of the HabitEntry to add
     * @param requestBody - all of HabitEntity params
     * @return status code
     */
    @PutMapping("/")
    public ResponseEntity <Void> putHabit(@RequestParam("id") UUID id, @RequestBody HabitRequestModel requestBody){
        return ResponseEntity.status(service.putHabit(id, requestBody)).build();
    }

    /**
     * API call for deleting a habit (HabitEntity)
     * @param id - id of the habit that should be deleted
     * @return status code
     */
    @DeleteMapping("/")
    public ResponseEntity <Void> deleteHabit (@RequestParam("id") UUID id){
        return ResponseEntity.status(service.deleteHabit(id)).build();
    }

    /**
     * API call for deleting all habits (HabitEntity)
     * @param userId - id of the user all habits should be deleted from
     * @return status code
     */
    @DeleteMapping("/bulk/")
    public ResponseEntity <Void> deleteAllHabits (@RequestParam("userId") UUID userId){
        return ResponseEntity.status(service.deleteAllHabits(userId)).build();
    }
}
