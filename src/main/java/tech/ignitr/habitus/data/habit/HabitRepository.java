package tech.ignitr.habitus.data.habit;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ignitr.habitus.data.user.User;

import java.util.List;
import java.util.UUID;

public interface HabitRepository extends JpaRepository <Habit, UUID>{
    List<Habit> findAllByUser(User user);
    boolean existsByUser(User user);
    Habit getHabitById(UUID id);
    void deleteAllByUser(User user);
}
