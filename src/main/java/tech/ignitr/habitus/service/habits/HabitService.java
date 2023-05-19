package tech.ignitr.habitus.service.habits;

import org.springframework.http.ResponseEntity;
import tech.ignitr.habitus.data.habits.Habit;
import tech.ignitr.habitus.web.habits.HabitRequest;

import java.util.List;
import java.util.UUID;


public interface HabitService {

        ResponseEntity<Habit> postHabit(HabitRequest requestBody);
        ResponseEntity<List<Habit>> getHabits(UUID userId);
        ResponseEntity<Habit> putHabit(HabitRequest requestBody);
        ResponseEntity<Void> deleteHabit(UUID id);
        ResponseEntity<Void> deleteAllHabits(UUID userId);

}
