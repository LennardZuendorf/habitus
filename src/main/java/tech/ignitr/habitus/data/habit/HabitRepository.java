package tech.ignitr.habitus.data.habit;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ignitr.habitus.data.user.User;

import java.util.List;

public interface HabitRepository extends JpaRepository <Habit, Long>{
    List<Habit> findAllByUser(User user);
    boolean existsByUser(User user);
    Habit getHabitById(Long id);
    void deleteAllByUser(User user);
}
