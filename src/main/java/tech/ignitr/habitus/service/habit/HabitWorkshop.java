package tech.ignitr.habitus.service.habit;

import tech.ignitr.habitus.data.habit.Habit;
import tech.ignitr.habitus.data.habit.HabitRepository;
import tech.ignitr.habitus.data.user.UserRepository;
import tech.ignitr.habitus.web.habit.HabitRequestModel;

public class HabitWorkshop {

    private final HabitRepository repository;
    private final UserRepository userRepository;
    public HabitWorkshop(HabitRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    /**
     * updating all habits
     * @param uid - id of the user whos habits should be updated
     */
    public Habit createHabit(HabitRequestModel requestBody){
        return new Habit(
                null,
                userRepository.getUserById(requestBody.getUserId()),
                requestBody.getTag(),
                requestBody.getMaxQuantity()
        );
    };

    /**
     *
     * @param requestBody
     */
    public static void updateHabit(HabitRequestModel requestBody) {
        var habitEntry = repository.findFirstByHid(hid);

        if(!lastCheck.equals(placeholderDate)){
            if(!done)lastCheck = habitEntry.getLastCheck();
        }

        habitEntry.setTag(tag);
        habitEntry.setFrequency(frequency);
        habitEntry.setQuantity(quantity);
        habitEntry.setDone(done);
        habitEntry.setDoneAmount(doneAmount);
        habitEntry.setLastCheck(lastCheck);
        repository.saveAndFlush(habitEntry);

    }
}
