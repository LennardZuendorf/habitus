package tech.ignitr.habitus.data.user;

import jakarta.persistence.*;
import lombok.*;
import tech.ignitr.habitus.data.habit.Habit;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.PROTECTED)
    private UUID id;

    @OneToMany(mappedBy = "user")
    private List<Habit> habits;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

}
