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

@Service
public class HabitServiceImpl implements HabitService{

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    private final HabitWorkshop workshop;

    @Autowired
    public HabitServiceImpl(HabitRepository habitRepository, UserRepository userRepository, HabitWorkshop workshop) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
        this.workshop = workshop;
    }

    //services for habit API endpoints
    /**
     * saving a new habit to database
     * @param requestBody - all of HabitEntity params
     * @return HabitStatusReturn - combination of new Entity and status code
     */
    @Override
    public HabitStatusReturn postHabit(HabitRequestModel requestBody) {
        Habit newHabit = workshop.createHabit(requestBody);
        habitRepository.saveAndFlush(newHabit);
        return new HabitStatusReturn(newHabit, HttpStatus.CREATED);
    }

    /**
     * getting a list of all habits by UserID
     * @param userId - the user ID to be selected by
     * @return HabitListStatusReturn - combination of new List with Entities and status code
     */
    @Override
    public HabitListStatusReturn getHabits(Long userId) {
        workshop.checkHabits(userId);
        if (userRepository.existsById(userId)){
            return new HabitListStatusReturn(habitRepository.findAllByUser(userRepository.getUserById(userId)), HttpStatus.OK);
        } else return new HabitListStatusReturn(null, HttpStatus.NO_CONTENT);
    }

    /**
     * updating a HabitEntity in the database
     * @param hid - id of the habit to update
     * @param requestBody - all of HabitEntity params
     * @return http status code
     */
    @Override
    public HttpStatus putHabit(Long hid, HabitRequestModel requestBody) {
        if(habitRepository.existsById(hid)){
            return workshop.updateHabit(requestBody);
        }else return HttpStatus.NO_CONTENT;
    }

    /**
     * delete a habit (HabitEntity)
     * @param id - id of the habit that should be deleted
     * @return http status code
     */
    @Override
    public HttpStatus deleteHabit(Long id) {
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
    public HttpStatus deleteAllHabits(Long userId) {
        User user = userRepository.getUserById(userId);
        if (!user.getHabits().isEmpty()){
            habitRepository.deleteAllByUser(user);
            return HttpStatus.OK;
        } else return HttpStatus.NO_CONTENT;
    }
}
