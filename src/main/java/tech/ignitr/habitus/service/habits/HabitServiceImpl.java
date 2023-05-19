package tech.ignitr.habitus.service.habits;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ignitr.habitus.data.configuration.DatabaseException;
import tech.ignitr.habitus.data.habits.Habit;
import tech.ignitr.habitus.data.habits.HabitRepository;
import tech.ignitr.habitus.data.users.User;
import tech.ignitr.habitus.data.users.UserRepository;
import tech.ignitr.habitus.web.habits.HabitModel;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService{

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    //services for habit API endpoints
    /**
     * saving a new habit to database
     * @param model - all of HabitEntity params
     * @return HabitStatusReturn - combination of new Entity and status code
     */
    @Override
    public ResponseEntity<Habit> postHabit(HabitModel model){
        try {
            User user = userRepository.findById(model.getUserId())
                    .orElseThrow(()-> new DatabaseException("user not found", HttpStatus.NOT_FOUND));
            Habit newHabit = Habit.builder()
                    .id(UUID.randomUUID())
                    .user(user)
                    .tag(model.getTag())
                    .frequency(model.getFrequency())
                    .currentQuantity(0)
                    .maxQuantity(model.getMaxQuantity())
                    .done(false)
                    .build();
            habitRepository.saveAndFlush(newHabit);
            return ResponseEntity.ok(newHabit);
        }catch (DatabaseException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * getting a list of all habits by UserID
     * @param userId - the user ID to be selected by
     * @return ResponseEntity with either list of habits and ok code or empty list and not found code
     */
    @Override
    public ResponseEntity<List<Habit>> getHabits(UUID userId) {
        try{
            checkHabits(userId);
            return ResponseEntity.ok(habitRepository.findAllByUser(userRepository.findById(userId)
                    .orElseThrow(()-> new DatabaseException("data not found", HttpStatus.NO_CONTENT))
                ).orElseThrow(()-> new DatabaseException("data not found", HttpStatus.NO_CONTENT)));
        }catch (DatabaseException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * updating a HabitEntity in the database
     * @param model - all of HabitEntity params
     * @return empty response with http status code
     */
    @Override
    public ResponseEntity<Habit> putHabit(HabitModel model) {
        try {
            habitRepository.findById(model.getId())
                    .orElseThrow(()-> new DatabaseException("user not found", HttpStatus.NOT_FOUND));
            Habit newHabit = Habit.builder()
                    .id(UUID.randomUUID())
                    .user(userRepository.getReferenceById(model.getUserId()))
                    .tag(model.getTag())
                    .frequency(model.getFrequency())
                    .currentQuantity(0)
                    .maxQuantity(model.getMaxQuantity())
                    .done(false)
                    .build();
            habitRepository.saveAndFlush(newHabit);
            return ResponseEntity.ok(newHabit);
        }catch (DatabaseException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * delete a habit (HabitEntity)
     * @param id - id of the habit that should be deleted
     * @return hempty response with http status code
     */
    @Override
    public ResponseEntity<Void> deleteHabit(UUID id) {
        try{
            habitRepository.delete(habitRepository.findById(id)
                    .orElseThrow(()-> new DatabaseException("user not found", HttpStatus.NOT_FOUND)));
            habitRepository.flush();
            return ResponseEntity.ok().build();
        }catch(DatabaseException e){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * deleting all habit (HabitEntity)
     * @param userId - id of the habit that should be deleted
     * @return empty response with http status code
     */
    @Override
    public ResponseEntity<Void> deleteAllHabits(UUID userId) {
        try{
            habitRepository.deleteAllByUser(userRepository.findById(userId)
                    .orElseThrow(()-> new DatabaseException("user not found", HttpStatus.NOT_FOUND)));
            habitRepository.flush();
            return ResponseEntity.ok().build();
        }catch(DatabaseException e){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param userId to search for all habits to check/update
     * @throws DatabaseException if user or data was not found
     */
    private void checkHabits(UUID userId) throws DatabaseException {
        List<Habit> list = habitRepository.findAllByUser(userRepository.findById(userId)
                        .orElseThrow(() -> new DatabaseException("user not found", HttpStatus.NOT_FOUND)))
                .orElseThrow(() -> new DatabaseException("data not found", HttpStatus.NO_CONTENT));

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
