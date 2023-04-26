package tech.ignitr.habitus.service;

import org.springframework.http.ResponseEntity;
import tech.ignitr.habitus.data.Habit;

import java.util.List;

public class HabitServiceImpl implements HabitService{

    /**
     * @param uid
     * @return
     */
    @Override
    public ResponseEntity<List<Habit>> getHabits(String uid) {
        return null;
    }

    /**
     * @param requestBody
     * @return
     */
    @Override
    public ResponseEntity<Habit> postHabit(HabitRequestModel requestBody) {
        return null;
    }

    /**
     * @param id
     * @param requestBody
     * @return
     */
    @Override
    public ResponseEntity<Void> putHabit(Long id, HabitRequestModel requestBody) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Void> deleteHabit(Long id) {
        return null;
    }
}
