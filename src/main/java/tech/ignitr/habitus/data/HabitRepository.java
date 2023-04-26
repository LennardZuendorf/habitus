package tech.ignitr.habitus.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitRepository extends JpaRepository <Habit, Long>{
    List<Habit> findAllByUid(Long uid);
    boolean existsById(Long id);
}
