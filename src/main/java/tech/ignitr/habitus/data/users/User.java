package tech.ignitr.habitus.data.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ignitr.habitus.data.habits.Habit;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private UUID id;

    @OneToMany(mappedBy = "user")
    private List<Habit> habits;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
}

