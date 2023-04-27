package tech.ignitr.habitus.service.user;

import org.springframework.http.HttpStatus;
import tech.ignitr.habitus.web.habit.HabitListStatusReturn;
import tech.ignitr.habitus.web.habit.HabitRequestModel;
import tech.ignitr.habitus.web.habit.HabitStatusReturn;

public interface UserService {

    //Habit services
    HabitStatusReturn postHabit(HabitRequestModel requestBody);
    HabitListStatusReturn getHabits(String uid);
    HttpStatus putHabit(Long hid, HabitRequestModel requestBody);
    HttpStatus deleteHabit(Long hid);
    HttpStatus deleteAllHabits(String uid);
}
