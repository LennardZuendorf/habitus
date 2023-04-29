package tech.ignitr.habitus.service.habit;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import tech.ignitr.habitus.data.habit.Habit;
import tech.ignitr.habitus.data.habit.HabitRepository;
import tech.ignitr.habitus.data.user.UserRepository;
import tech.ignitr.habitus.web.habit.HabitRequestModel;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HabitWorkshop {

    private final HabitRepository repository;
    private final UserRepository userRepository;

    public HabitWorkshop(HabitRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    /**
     * @param requestBody
     * @return
     */
    public Habit createHabit(HabitRequestModel requestBody) {
        return new Habit(
                null,
                userRepository.getUserById(requestBody.getUserId()),
                requestBody.getTag(),
                requestBody.getMaxQuantity()
        );
    }

    /**
     * @param requestBody
     */
    public HttpStatus updateHabit(HabitRequestModel requestBody) {
        return HttpStatus.OK;
    }

    public void checkHabits(Long userId) {
        List<Habit> list = repository.findAllByUser(userRepository.getUserById(userId));
        for (Habit habit : list) {
            if(habit.getCurrentQuantity() >= habit.getMaxQuantity()){
                habit.setDone(true);
                habit.setCheck(LocalDateTime.now());
            }else{
                switch (habit.getFrequency()) {
                    case DAILY -> {
                        if (habit.getCheck().plusDays(1).isAfter(LocalDateTime.now())) {
                            habit.setDone(false);
                            habit.setCheck(null);
                        }
                    }
                    case WEEKLY -> {
                        if (habit.getCheck().plusWeeks(1).isAfter(LocalDateTime.now())) {
                            habit.setDone(false);
                            habit.setCheck(null);
                        }
                    }
                    case BIWEEKLY -> {
                        if (habit.getCheck().plusWeeks(2).isAfter(LocalDateTime.now())) {
                            habit.setDone(false);
                            habit.setCheck(null);
                        }
                    }
                    case TRIWEEKLY -> {
                        if (habit.getCheck().plusWeeks(3).isAfter(LocalDateTime.now())) {
                            habit.setDone(false);
                            habit.setCheck(null);
                        }
                    }
                    case MONTHLY -> {
                        if (habit.getCheck().plusMonths(1).isAfter(LocalDateTime.now())) {
                            habit.setDone(false);
                            habit.setCheck(null);
                        }
                    }
                    default -> {
                    }
                }
            }
        }
        repository.saveAllAndFlush(list);
    }
}
