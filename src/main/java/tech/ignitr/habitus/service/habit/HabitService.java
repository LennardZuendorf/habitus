package tech.ignitr.habitus.service.habit;

import org.springframework.http.HttpStatus;
import tech.ignitr.habitus.web.habit.HabitListStatusReturn;
import tech.ignitr.habitus.web.habit.HabitRequestModel;
import tech.ignitr.habitus.web.habit.HabitStatusReturn;

import java.util.UUID;


public interface HabitService {

    HabitStatusReturn postHabit(HabitRequestModel requestBody);
    HabitListStatusReturn getHabits(UUID userId);
    HttpStatus putHabit(UUID id, HabitRequestModel requestBody);
    HttpStatus deleteHabit(UUID id);
    HttpStatus deleteAllHabits(UUID userId);
}
