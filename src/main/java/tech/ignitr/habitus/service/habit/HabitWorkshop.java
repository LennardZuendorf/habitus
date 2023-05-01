package tech.ignitr.habitus.service.habit;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import tech.ignitr.habitus.data.habit.Habit;
import tech.ignitr.habitus.data.habit.HabitRepository;
import tech.ignitr.habitus.data.user.UserRepository;
import tech.ignitr.habitus.web.habit.HabitRequestModel;

import java.time.LocalDate;
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
                habit.setDate_done(
                        LocalDate.now());
            }else{
                switch (habit.getFrequency()) {
                    case DAILY -> {
                        if (habit.getDate_done().plusDays(1).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDate_done(null);
                        }
                    }
                    case WEEKLY -> {
                        if (habit.getDate_done().plusWeeks(1).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDate_done(null);
                        }
                    }
                    case BIWEEKLY -> {
                        if (habit.getDate_done().plusWeeks(2).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDate_done(null);
                        }
                    }
                    case TRIWEEKLY -> {
                        if (habit.getDate_done().plusWeeks(3).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDate_done(null);
                        }
                    }
                    case MONTHLY -> {
                        if (habit.getDate_done().plusMonths(1).isAfter(LocalDate.now())) {
                            habit.setDone(false);
                            habit.setCurrentQuantity(0);
                            habit.setDate_done(null);
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
