package tech.ignitr.habitus.service.habit;

import org.springframework.http.HttpStatus;
import tech.ignitr.habitus.web.habit.HabitListStatusReturn;
import tech.ignitr.habitus.web.habit.HabitRequestModel;
import tech.ignitr.habitus.web.habit.HabitStatusReturn;


public interface HabitService {

    HabitStatusReturn postHabit(HabitRequestModel requestBody);
    HabitListStatusReturn getHabits(Long userId);
    HttpStatus putHabit(Long id, HabitRequestModel requestBody);
    HttpStatus deleteHabit(Long id);
    HttpStatus deleteAllHabits(Long userId);
}
