package tech.ignitr.habitus.data.habits;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ignitr.habitus.data.users.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitRepository extends JpaRepository <Habit, UUID>{
    Optional<List<Habit>> findAllByUser (User user);
    Habit getHabitById(UUID id);
    void deleteAllByUser(User user);

}
