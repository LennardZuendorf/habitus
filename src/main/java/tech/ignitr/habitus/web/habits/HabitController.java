package tech.ignitr.habitus.web.habits;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.habits.Habit;
import tech.ignitr.habitus.service.habits.HabitService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class HabitController {

    private final HabitService service;
    private final String baseURL = "/api";

    /**
     * API call for creating a new habit (HabitEntity)
     * @param requestBody - all of HabitEntity params
     * @return ResponseEntity containing the status code from service method and the created Habit
     */
    @PostMapping(baseURL+"/habits")
    public ResponseEntity <Habit> postHabit(@RequestBody HabitRequest requestBody){
        return service.postHabit(requestBody);
    }

    /**
     * API call for updating habit (HabitEntity)
     * @param requestBody - all Habit params
     * @return ResponseEntity containing the status code from service method
     */
    @PutMapping(baseURL+"/habits")
    public ResponseEntity <Habit> putHabit(@RequestBody HabitRequest requestBody){
        return service.putHabit(requestBody);
    }

    /**
     * API call for deleting a habit (HabitEntity)
     * @param id - id of the habit that should be deleted
     * @return ResponseEntity containing the status code from service method
     */
    @DeleteMapping(baseURL+"/habits{id}")
    public ResponseEntity <Void> deleteHabit (@PathVariable("id") UUID id){
        return service.deleteHabit(id);
    }

}
