package tech.ignitr.habitus.service.habit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.habit.Habit;
import tech.ignitr.habitus.data.habit.HabitRepository;
import tech.ignitr.habitus.data.user.User;
import tech.ignitr.habitus.data.user.UserRepository;
import tech.ignitr.habitus.web.habit.HabitListStatusReturn;
import tech.ignitr.habitus.web.habit.HabitRequestModel;
import tech.ignitr.habitus.web.habit.HabitStatusReturn;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class HabitServiceImpl implements HabitService{

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    @Autowired
    public HabitServiceImpl(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    //services for habit API endpoints
    /**
     * saving a new habit to database
     * @param requestBody - all of HabitEntity params
     * @return HabitStatusReturn - combination of new Entity and status code
     */
    @Override
    public HabitStatusReturn postHabit(HabitRequestModel requestBody) {
        Habit newHabit = Habit.builder()
                .user(userRepository.getUserById(requestBody.getUserId()))
                .tag(requestBody.getTag())
                .frequency(requestBody.getFrequency())
                .currentQuantity(0)
                .maxQuantity(requestBody.getMaxQuantity())
                .done(false)
                .build();
        habitRepository.saveAndFlush(newHabit);
        return new HabitStatusReturn(newHabit, HttpStatus.CREATED);
    }

    /**
     * getting a list of all habits by UserID
     * @param userId - the user ID to be selected by
     * @return HabitListStatusReturn - combination of new List with Entities and status code
     */
    @Override
    public HabitListStatusReturn getHabits(UUID userId) {
        checkHabits(userId);
        if (userRepository.existsById(userId)){
            return new HabitListStatusReturn(habitRepository.findAllByUser(userRepository.getUserById(userId)), HttpStatus.OK);
        } else return new HabitListStatusReturn(null, HttpStatus.NO_CONTENT);
    }

    /**
     * updating a HabitEntity in the database
     * @param id - id of the habit to update
     * @param requestBody - all of HabitEntity params
     * @return http status code
     */
    @Override
    public HttpStatus putHabit(UUID id, HabitRequestModel requestBody) {
        if(habitRepository.existsById(id)){
            return updateHabit(requestBody);
        }else return HttpStatus.NO_CONTENT;
    }

    /**
     * delete a habit (HabitEntity)
     * @param id - id of the habit that should be deleted
     * @return http status code
     */
    @Override
    public HttpStatus deleteHabit(UUID id) {
        if (habitRepository.existsById(id)) {
            habitRepository.delete(habitRepository.getHabitById(id));
            habitRepository.flush();
            return HttpStatus.OK;
        } else return HttpStatus.NO_CONTENT;
    }

    /**
     * deleting all habit (HabitEntity)
     * @param userId - id of the habit that should be deleted
     * @return http status code
     */
    @Override
    public HttpStatus deleteAllHabits(UUID userId) {
        User user = userRepository.getUserById(userId);
        if (!user.getHabits().isEmpty()){
            habitRepository.deleteAllByUser(user);
            return HttpStatus.OK;
        } else return HttpStatus.NO_CONTENT;
    }

    /**
     *
     * @param requestBody
     * @return
     */
    private HttpStatus updateHabit(HabitRequestModel requestBody) {
        return HttpStatus.OK;
    }

    /**
     *
     * @param userId
     */
    private void checkHabits(UUID userId) {
        List<Habit> list = habitRepository.findAllByUser(userRepository.getUserById(userId));
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
        habitRepository.saveAllAndFlush(list);
    }
}
