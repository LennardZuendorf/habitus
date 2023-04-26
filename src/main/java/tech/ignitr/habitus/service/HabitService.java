package tech.ignitr.habitus.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.Habit;

import java.util.List;

@Service
public interface HabitService {

    ResponseEntity<List<Habit>> getHabits(String uid);
    ResponseEntity<Habit> postHabit(HabitRequestModel requestBody);
    ResponseEntity<Void> putHabit(Long id, HabitRequestModel requestBody);
    ResponseEntity<Void> deleteHabit(Long id);
}
